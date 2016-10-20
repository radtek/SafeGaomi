package com.hisign.publicsafety.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.SpringHolder;
import com.hisign.publicsafety.entity.Account;
import com.hisign.publicsafety.entity.Dic;
import com.hisign.publicsafety.entity.Resources;
import com.hisign.publicsafety.entity.RoleAccount;
import com.hisign.publicsafety.entity.Roles;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataRequest;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.AccountService;
import com.hisign.publicsafety.service.DicService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.service.RolesService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.util.Md5Tool;
import com.hisign.publicsafety.util.POIUtils;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;

/**
 * 
 * @author zhaoqian
 * @time：2015-08-10
 * 用户管理controller
 */
@Controller
@RequestMapping("/background/account/")
public class AccountController extends BaseController{
	@Inject
	private AccountService accountService;
	@Inject
	private IdService idService;
	@Inject
	private RolesService roleService;
	@Inject
	private DicService dicService;
	
	/**
	 * 跳转至用户管理页面
	 * @param model
	 * @param menu
	 * @param pageNow
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model, Resources menu, String pageNow) {
		
		JSONArray ja = new JSONArray(); 
		Roles role = new Roles();
		role.setEnable("1");
		List<Roles> roleList = roleService.queryAll(role);
		if (roleList != null ) {
			for (Roles r : roleList) {
				JSONObject jobj = new JSONObject();
				jobj.put("id", r.getId());
				jobj.put("name", r.getName());
				ja.add(jobj);
			}
		}
		
		String unitJson;
		try {
			unitJson = dicService.getUnitJson();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		/**
		 * 添加token验证
		 */
		model.addAttribute("csrf", CsrfTokenManager.getToken());
		
		model.addAttribute("ja", ja.toString());
		model.addAttribute("dicJsonArr", unitJson);
		return Common.BACKGROUND_PATH+"/account/accountList";
	}

	/**
	 * jgrid表格查询
	 * @param account
	 * @param dataRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query")
	public DataResponse<Account> query(Account account, DataRequest dataRequest) {
		
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		pageView = accountService.query(getPageView(String.valueOf(page),String.valueOf(rows)), account);
		return pageView.getDataResponse();
	}
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletResponse response,Account account) {
		 List<Account> acs =accountService.queryAll(account);
		 POIUtils.exportToExcel(response, "账号报表", acs, Account.class, "账号", acs.size());
	}
	
	/**
	 * 保存数据
	 * 
	 * @param model
	 * @param videoType
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(@RequestParam String CSRFToken, Account account) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//判断是否有相同用户名
			Account account1 = accountService.isExist(account.getAccountName());
			if(account1 != null){
				map.put("flag", "false");
				map.put("content", "此用户名已存在，新增失败");
				return map;
			}
			
			String policeId = roleService.isExistByRoleKey(Constant.ROLE_KEY_POLICE).getId().toString();
			//非公安用户不添加警员编号
			if(!account.getRoleIds().equals(policeId)){
				account.setPoliceNumber(null);
			} else {
				//如果为公安用户，则一个警员编号只能注册一个账户
				if (!Common.isEmpty(account.getPoliceNumber())) {
					Account accountTemp = accountService.isExistByPoliceNumber(account.getPoliceNumber());
					if(accountTemp != null){
						map.put("flag", "false");
						map.put("content", "此警员编号已注册，新增失败");
						return map;
					}
				}
			}
			
			account.setPassword(Md5Tool.getMd5(account.getPassword()));
			account.setId(idService.getId());
			account.setCreateTime(new Date());
			if(account.getState() == null){
				account.setState("0");
			}
			
			accountService.add(account);
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "新增失败");
		}
		return map;
	}
	
	/**
	 * 用户批量注册
	 * @param filePath
	 * @param account
	 * @return
	 */
	@RequestMapping("batchAdd")
	@ResponseBody
	public Map<String, Object> batchAdd(@RequestParam String CSRFToken, @RequestParam("file") MultipartFile file, Account account) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (!file.isEmpty()) { 
			
			if(account.getState() == null){
				account.setState("0");  //设置启用状态
			}
            	StringBuffer errorBuffer = new StringBuffer();
            	
            	CommonsMultipartFile cf= (CommonsMultipartFile)file;
            	DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
            	String fileName = fi.getName();
            	//获取excel数据
				List<List<String>> dataList = null;
				try {
					dataList = POIUtils.getExcelData(file, fileName, 0);
				} catch (Exception e1) {
					LogUtil.errStack2Log4j(e1);
					map.put("flag", "false");
					map.put("content", "文件解析失败");
				}
				if (dataList != null && dataList.size() > 0) {
					dataList.remove(0);
					for (List<String> list : dataList) {
						  try {
							//判断是否有相同用户名
								Account account1 = accountService.isExist(list.get(1));
								if(account1 != null){
									errorBuffer.append("用户：" + list.get(1) + "已存在，此记录新增失败\r\n");
									continue;
								} else {
									String policeId = roleService.isExistByRoleKey(Constant.ROLE_KEY_POLICE).getId().toString();
									
									//公安用户增加警员编号，非公安用户忽略
									if(account.getRoleIds().equals(policeId)) {
										
										//如果为公安用户，则一个警员编号只能注册一个账户
										if (!Common.isEmpty(list.get(4))) {
											//校验是否为六位数字，若不是添加失败
											String policeNumberTemp = list.get(4).toString().trim();
											if (policeNumberTemp.length() != 6 || !Common.isNumber(policeNumberTemp)) {
												errorBuffer.append("用户：" + list.get(1) + "的警员编号不符合规范，应为六位数字，此记录新增失败\r\n");
												continue;
											} 
											
											Account accountTemp = accountService.isExistByPoliceNumber(list.get(4));
											if(accountTemp != null){
												errorBuffer.append("用户：" + list.get(1) + "的警员编号" + list.get(4) + "已注册，此记录新增失败\r\n");
												continue;
											}
										}
										account.setPoliceNumber(list.get(4));
									}
									
									account.setTrueName(list.get(0));  
									account.setAccountName(list.get(1));
									account.setPassword(Md5Tool.getMd5(list.get(2)));
									account.setPhoneNumber(list.get(3));
									account.setDuty(list.get(5));
									account.setId(idService.getId());
									account.setCreateTime(new Date());
									
									accountService.add(account);
								}
						  } catch (Exception e) {
							  LogUtil.errStack2Log4j(e);
							  errorBuffer.append("用户：" + list.get(1) + "新增失败\r\n");
							  continue;
						  }
						
					}
					map.put("flag", "true");
					map.put("content", errorBuffer.toString());
				}
       } else {  
    	   map.put("flag", "false");
			map.put("content", "文件为空");
       }  
		 return map; 
	}
	
	/**
	 * 用户编辑
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("findDetailById")
	@ResponseBody
	public Map findDetailById(String accountId, HttpServletRequest request) {
		
		try {
			Map map = new HashMap();
			Account account = accountService.getById(accountId);
			map.put("account", account);
			
			Roles role = roleService.findbyAccountRole(accountId);
			account.setRoleIds(String.valueOf(role.getId()));
			
			return map;
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			return null;
		}
	}
	
	/**
	 * 删除
	 * 
	 * @param model
	 * @param videoTypeId
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("deleteById")
	public Map deleteById(Model model, String ids) {
		Map map = new HashMap();
		try {
			String id[] = ids.split(",");
			for (String string : id) {
				if(!Common.isEmpty(string)){
					
				accountService.delete(string);
				}
			}
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "删除失败");
		}
		return map;
	}
	
	/**
	 * 更新
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("update")
	public Map<String, Object> update(Model model, @RequestParam String CSRFToken, Account account, HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			//获取修改之前的用户信息
			Account accountOld = accountService.getById(account.getId()+"");
			if (accountOld == null) {
				map.put("flag", "false");
				map.put("content", "此用户不存在");
				return map;
			} else {
				Roles role = roleService.findbyAccountRole(account.getId()+"");
				accountOld.setRoleIds(String.valueOf(role.getId()));
			}
			
			//判断是否有相同用户名
			if (account.getAccountName().equals(accountOld.getAccountName())) {  //用户名无修改
			} else {
				Account account1 = accountService.isExist(account.getAccountName());
				if(account1 != null){
					map.put("flag", "false");
					map.put("content", "此用户名已存在，请重新填写");
					return map;
				}
			}
			
			
			//如果为公安用户，则一个警员编号只能注册一个账户
			if (!Common.isEmpty(account.getPoliceNumber())) {
				String policeNumberOld = accountOld.getPoliceNumber() != null ? accountOld.getPoliceNumber() : "";
				if (policeNumberOld.equals(account.getPoliceNumber())) {} else {
					Account accountTemp = accountService.isExistByPoliceNumber(account.getPoliceNumber());
					if(accountTemp != null){
						map.put("flag", "false");
						map.put("content", "此警员编号已注册，请重新填写");
						return map;
					}
				}
			}
			
			if (!account.getPassword().equals(accountOld.getPassword())) {
				account.setPassword(Md5Tool.getMd5(account.getPassword()));
			} else {
				account.setPassword(accountOld.getPassword());
			}
			if(account.getState() == null){
				account.setState("0");
			}
			account.setUpdateTime(new Date());
			
			String oldRoleIDs = accountOld.getRoleIds();
			String policeId = roleService.isExistByRoleKey(Constant.ROLE_KEY_POLICE).getId().toString();
			//警员角色修改为其他角色时，警员编号置空
			if (oldRoleIDs.endsWith(policeId) && !account.getRoleIds().equals(oldRoleIDs)) {
				account.setPoliceNumber("");
			}
			
			accountService.update(account);
			
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "修改失败");
		}
		return map;
	}
}

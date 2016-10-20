package com.hisign.publicsafety.controller.ccp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.Account;
import com.hisign.publicsafety.entity.Roles;
import com.hisign.publicsafety.entity.ccp.ClueInfo;
import com.hisign.publicsafety.entity.ccp.ClueProviderInfo;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataRequest;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.service.DicService;
import com.hisign.publicsafety.service.RolesService;
import com.hisign.publicsafety.service.ccp.ClueProviderInfoService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;
import com.hisign.publicsafety.web.tool.O2oCookieHelper;

/**
 * 线索提供人（手机注册用户）controller
 * @author hotdog
 *
 */
@Controller
@RequestMapping("/ccp/clueProvider/")
public class ClueProviderInfoController extends BaseController{
	
	@Inject
	private ClueProviderInfoService clueProviderInfoService;
	@Inject
	private RolesService roleService;
	@Inject
	private DicService dicService;
	
	/**
	 * 进入数据查询模块
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		
		JSONArray ja = new JSONArray();
		Roles role = new Roles();
		role.setEnable("1");
		List<Roles> roleList = roleService.queryAll(role);
		if (roleList != null) {
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
		
		return  Common.CCP_PATH + "/clueProvider/clueProviderList";
	}
	
	/**
	 * 查询注册用户总数（首页用）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getProviderCount")
	public Map getClueProviderCount () {
		Map map = new HashMap<String, Object>();
		try {
			int clueProviderCount = clueProviderInfoService.queryCount(new ClueProviderInfo());
			map.put("flag", "success");
			map.put("providerCount", clueProviderCount);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
		}
		return map;
	}
	
	/**
	 * jgrid表格查询
	 * @param model
	 * @param clueInfo
	 * @param dataRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query")
	public DataResponse<ClueProviderInfo> query (Model model, ClueProviderInfo t, DataRequest dataRequest) {
		
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		pageView = clueProviderInfoService.query(getPageView(String.valueOf(page),String.valueOf(rows)), t);
		return pageView.getDataResponse();
	}
	
	/**
	 * 查看线索详细信息
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("view")
	public Map<String, Object> view (String id) {
		Map map = new HashMap<String, Object>();
		try {
			ClueProviderInfo obj = clueProviderInfoService.getById(id);
			if (obj != null) {
				map.put("clueProviderInfo", obj);
			} else {
				map.put("content", "未找到相关用户信息");
			}
			map.put("flag", "success");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "查询异常");
		}
		return map;
	}
	
	/**
	 * 删除
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteById")
	public Map deleteById(Model model, String id) {
		Map map = new HashMap();
		try {
			clueProviderInfoService.delete(id);
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
	public Map<String, Object> update(Model model,ClueProviderInfo obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//获取登录用户信息
			String loginUserId = O2oCookieHelper.getUserInfo().getAccountId() + "";
			obj.setUpdateUser(loginUserId);
			
			String openFlag = "on".equals(obj.getOpenFlag()) ? "1" : "0";
			obj.setOpenFlag(openFlag);
			
			clueProviderInfoService.update(obj);
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "更新失败");
		}
		return map;
	}
	
}

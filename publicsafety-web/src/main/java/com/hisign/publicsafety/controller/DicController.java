package com.hisign.publicsafety.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hisign.publicsafety.entity.Account;
import com.hisign.publicsafety.entity.DicType;
import com.hisign.publicsafety.entity.Resources;
import com.hisign.publicsafety.entity.Dic;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataRequest;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.DicService;
import com.hisign.publicsafety.service.DicTypeService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;

/**
 * 
 * @author zhaoqian
 * @time：2015-08-10
 * 字典管理controller
 */
@Controller
@RequestMapping("/background/dic/")
public class DicController extends BaseController{
	@Inject
	private DicService dicService;
	@Inject
	private DicTypeService dicTypeService;
	@Inject
	private IdService idService;
	
	@RequestMapping("list")
	public String list(Model model, Resources menu, String pageNow) {
		
		//加载字典类型
		JSONArray ja = new JSONArray();
		List<DicType> dicTypeList = dicTypeService.queryAll(new DicType());
		for (DicType dict : dicTypeList) {
			JSONObject jobj = new JSONObject();
			jobj.put("dicTypeId", dict.getId());
			jobj.put("dicTypeName", dict.getDicTypeName());
			
			ja.add(jobj);
		}
		
		/**
		 * 加入token验证
		 */
		model.addAttribute("csrf", CsrfTokenManager.getToken());
		model.addAttribute("ja", ja.toString());
		return Common.BACKGROUND_PATH+"/dic/dicList";
	}
	/**
	 * @param model
	 * 存放返回界面的model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query")
	public DataResponse<Dic> query(Dic dic,DataRequest dataRequest) {
		
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		
		//页面有任何输入查询条件时，全局查询，否则只查最高级别字典
		if (Common.isEmpty(dic.getDicKey()) && 
			Common.isEmpty(dic.getDicName()) && 
			dic.getDicTypeId() == null ) {
			
			if(dic.getDicLevel() == null || "".equals(dic.getDicLevel())){
				dic.setDicLevel("1"); //默认查询最高级
			}
		}
		
		pageView = dicService.query(getPageView(String.valueOf(page),String.valueOf(rows)), dic);
		return pageView.getDataResponse();
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
	public Map<String, Object> add(@RequestParam String CSRFToken, Dic dic) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			if(!isExist(dic)){
				map.put("flag", "false");
				map.put("content", "字典值已存在，新增失败");
				return map;
			}
			dic.setId(idService.getId());
			if(dic.getStatus() == null){
				dic.setStatus("0");
			}
			if(dic.getDicLevel() == null || "".equals(dic.getDicLevel())){
				dic.setDicLevel("1");
			}
			dicService.add(dic);
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "新增失败");
		}
		return map;
	}

	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(Dic dic){
		Dic d = dicService.isExist(dic);
		if(d == null){
			return true;
		}else{
			return false;
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
	public Map<String, Object> deleteById(Model model, String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id[] = ids.split(",");
			for (String string : id) {
				if(!Common.isEmpty(string)){
					dicService.delete(string);
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
	 * 用户编辑
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("findDetailById")
	@ResponseBody
	public Map findDetailById(String dicId) {
		try {
			Map map = new HashMap();
			Dic dic = dicService.getById(dicId);
			map.put("dic", dic);
			
			return map;
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			return null;
		}
	}
	
	/**
	 * 更新类型
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("update")
	public Map<String, Object> update(@RequestParam String CSRFToken, Model model, Dic dic) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(dic.getStatus() == null){
				dic.setStatus("0");
			}
			dicService.update(dic);
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "更新失败");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("findDicType")
	public List<Dic> findDicType(Model model, Dic dic) {
		return dicService.queryAll(dic);
	}
}

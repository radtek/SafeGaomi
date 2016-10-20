package com.hisign.publicsafety.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hisign.publicsafety.entity.Dic;
import com.hisign.publicsafety.entity.Resources;
import com.hisign.publicsafety.entity.DicType;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.DicTypeService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;

/**
 * 字典类型
 * @author zhaoqian
 * @time：2015-08-10
 */
@Controller
@RequestMapping("/background/dicType/")
public class DicTypeController extends BaseController{
	@Inject
	private DicTypeService dicTypeService;
	@Inject
	private IdService idService;
	
	
	@RequestMapping("list")
	public String list(Model model, Resources menu, String pageNow) {
		return Common.BACKGROUND_PATH+"/dicType/list";
	}
	/**
	 * @param model
	 * 存放返回界面的model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query")
	public PageView query(DicType dicType,String pageNow,String pagesize) {
		pageView = dicTypeService.query(getPageView(pageNow,pagesize), dicType);
		return pageView;
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
	public Map<String, Object> add(@RequestParam String CSRFToken, DicType dicType) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			if(!isExist(dicType)){
				map.put("flag", "false");
				map.put("content", "字典值已存在，新增失败");
				return map;
			}
			
			dicType.setId(idService.getId());
			
			dicTypeService.add(dicType);
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "新增失败");
		}
		return map;
	}

	/**
	 * @author lanyuan
	 * @return
	 */
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(DicType dicType){
		DicType dt = dicTypeService.isExist(dicType);
		if(dt == null){
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
					dicTypeService.delete(string);
				}
			}
			map.put("flag", "true");
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
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
	public Map<String, Object> update(Model model, DicType dicType) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			dicTypeService.update(dicType);
			map.put("flag", "true");
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("findDicType")
	public List<DicType> findDicType(Model model, DicType dicType) {
		return dicTypeService.queryAll(dicType);
	}
	
	@ResponseBody
	@RequestMapping("findDicTypeDetail")
	public Map<String, Object> findDicTypeDetail(Model model, DicType dicType) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
		    DicType qdicType = dicTypeService.queryById(dicType);
		    map.put("description", qdicType.getDescription());
		    map.put("id", qdicType.getId());
		} catch (Exception e) {
			
		}
		return map;
		
	}
}

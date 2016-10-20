package com.hisign.publicsafety.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hisign.publicsafety.entity.Resources;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.ResourcesService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.util.PropertiesUtils;

/**
 * 模块管理controller
 * zhao
 * 2015-08-10
 */
@Controller
@RequestMapping("/background/resources/")
public class ResourcesController extends BaseController {
	@Inject
	private ResourcesService resourcesService;

	@ResponseBody
	@RequestMapping("perm")
	public Map<String, Object> perm(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}

	@ResponseBody
	@RequestMapping("findRoleRes")
	public List<Resources> findRoleRes(String roleId){
		return resourcesService.findRoleRes(roleId);
	}

	/**
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping("query")
	public String query(Model model, Resources resources, String pageNow) {
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView = resourcesService.query(pageView, resources);
		model.addAttribute("pageView", pageView);
		return Common.BACKGROUND_PATH+"/resources/list";
	}

	/**
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model, Resources resources, String pageNow) {
		return Common.BACKGROUND_PATH+"/resources/moduleList";
	}

	@ResponseBody
	@RequestMapping("queryAll")
	public List<Resources> queryAll(HttpServletRequest request) {
		if (PropertiesUtils.findPropertiesKey("rootName").equals(Common.findAuthenticatedUsername())) {// 根据账号拥有所有权限
			return resourcesService.queryAll(new Resources());
		} else {
			return resourcesService.queryAll(new Resources());
		}
	}

	@ResponseBody
	@RequestMapping("queryParentId")
		public List<Resources> queryParentId(Resources resources) {			
				return resourcesService.queryByParentId(resources);
	}
	
	/**
	 * 添加菜单
	 * 
	 * @param resources
	 * @return Map
	 */
	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(Resources resources) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//判断是否为目录(目录的parentId为0)
			if(-1==resources.getParentId()){
				resources.setParentId(0);
			}
			resourcesService.add(resources);
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
		}
		return map;
	}

	/**
	 * 更新菜单
	 * 
	 * @param model
	 * @param Map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("update")
	public Map<String, Object> update(Model model, Resources resources) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			resourcesService.update(resources);
			map.put("flag", "true");
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
	}

	/**
	 * 根据ID删除菜单
	 * 
	 * @param model
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteById")
	public Map<String, Object> deleteById(Model model, String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id[] = ids.split(",");
			for (int i = 0, inv = id.length; i < inv; i++) {
				if (!Common.isEmpty(id[i])) {
					resourcesService.delete(id[i]);
				}
			}
			map.put("flag", "true");
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
	}
	
	/**
	 * 验证菜单是否存在
	 * @param name
	 * @return
	 */
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String name){
		//Account account = accountService.isExist(name);
		Resources resources=resourcesService.isExist(name);
		if(resources == null){
			return true;
		}else{
			return false;
		}
	}
	
}

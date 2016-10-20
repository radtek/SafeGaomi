package com.hisign.publicsafety.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.google.gson.Gson;
import com.hisign.publicsafety.entity.Resources;
import com.hisign.publicsafety.entity.Roles;
import com.hisign.publicsafety.jstree.entity.LiAttr;
import com.hisign.publicsafety.jstree.entity.Node;
import com.hisign.publicsafety.jstree.entity.State;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataRequest;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.service.ResourcesService;
import com.hisign.publicsafety.service.RolesService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;

/**
 * 
 * @author zhaoqian
 * @time：2015-08-10
 * 角色管理controller
 */
@Controller
@RequestMapping("/background/role/")
public class RolesController extends BaseController{
	@Inject
	private RolesService roleService;
	@Inject
	private IdService idService;
	@Inject
	private ResourcesService resourcesService;
	
	/**
	 * 跳转页面
	 * @param model
	 * @param menu
	 * @param pageNow
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model, Resources menu, String pageNow) {
		Node node = new Node();
		node.setText("安全o2o");
		node.setIcon("fa fa-file icon-state-warning");
		
		State state = new State();
		state.setOpened(true);
		node.setState(state);
		
		LiAttr li_attr = new LiAttr ();
		li_attr.setRes_id("");
		li_attr.setRes_key("");
		node.setLi_attr(li_attr);
		
		//子节点
		List<Node> childrenNodeList = new ArrayList();
		Resources resources = new Resources();
		//首次读取最上级模块列表
		resources.setLevel(1);
		//递归读取模块子节点节点
		getChildrenNodeList (resources,childrenNodeList);
		node.setChildren(childrenNodeList);
		
		Gson gson = new Gson();
		String json = gson.toJson(node);
		model.addAttribute("jstreeJson", json);
		
		/**
		 * 添加token验证
		 * update 2015-09-07
		 */
		model.addAttribute("csrf", CsrfTokenManager.getToken());
		
		return Common.BACKGROUND_PATH+"/role/roleList";
	}
	
	/**
	 * 递归读取模块列表
	 * @param resources
	 * @param childrenNodeList
	 */
	private void getChildrenNodeList (Resources resources, List<Node> nodeList) {
		List<Resources> resourcesList = resourcesService.queryAll(resources);
		if (resourcesList != null && resourcesList.size() > 0) {
			Node node = null;
			State state = null;
			LiAttr li_attr = null;
			for (Resources r : resourcesList) {
				//新增节点，装入childrenNodeListlist中
				node = new Node();
				state = new State();
				//默认设置为子节点打开状态
				state.setOpened(true);
				node.setText(r.getName());
				node.setIcon("fa fa-file icon-state-warning");
				
				node.setState(state);
				
				li_attr = new LiAttr ();
				li_attr.setRes_id(String.valueOf(r.getId()));
				li_attr.setRes_key(r.getResKey());
				node.setLi_attr(li_attr);
				
				//将该节点下的子节点放入此节点下的children中
				List<Node> childrenNodeList = new ArrayList();
				Resources resourcesFilter = new Resources();
				resourcesFilter.setParentId(r.getId());
				resourcesFilter.setLevel(r.getLevel() + 1);
				getChildrenNodeList(resourcesFilter,childrenNodeList);
				node.setChildren(childrenNodeList);
				
				nodeList.add(node);
			}
		} else {
			
		}
	}
	
	/**
	 * @param model
	 * 存放返回界面的model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query")
	public DataResponse<Roles> query(Roles role, DataRequest dataRequest) {
		
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		pageView = roleService.query(getPageView(String.valueOf(page),String.valueOf(rows)), role);
		return pageView.getDataResponse();

	}
	public PageView query_bak(Roles role,String pageNow,String pagesize) {
		pageView = roleService.query(getPageView(pageNow,pagesize), role);
		return pageView;
	}
	
	@ResponseBody
	@RequestMapping("queryAll")
	public Map<String, Object> queryAll(Roles role) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roles",  roleService.queryAll(role));
		return map;
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
	public Map<String, Object> add(@RequestParam String CSRFToken, Roles role) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			//判断角色名是否唯一
			if (!isExist(role.getName())) {
				map.put("flag", "false");
				map.put("content", "此角色名已存在");
				return map;
			}
			//判断角色键值是否唯一
			if (!isExistByRoleKey(role.getRoleKey())) {
				map.put("flag", "false");
				map.put("content", "此角色键值已存在");
				return map;
			}
			
			if(role.getEnable() == null){
				role.setEnable("0");
			}
			role.setId(idService.getId());
			//角色新增
			roleService.add(role);
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "新增失败");
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
	public Map findDetailById(String roleId, HttpServletRequest request) {
		try {
			Map map = new HashMap();
			Roles roles = roleService.getById(roleId);
			
			List<Resources> resList = resourcesService.findRoleRes(roleId);
			if(resList != null && resList.size() > 0) {
				map.put("resList", resList);
			}
			map.put("role", roles);
			
			return map;
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			return null;
		}
	}
	
	/**
	 * 验证账号是否存在
	 * @param name
	 * @return
	 */
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String name){
		Roles role = roleService.isExist(name);
		if(role == null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据rolekey查询角色
	 * @param roleKey
	 * @return
	 */
	public boolean isExistByRoleKey(String roleKey){
		Roles role = roleService.isExistByRoleKey(roleKey);
		if(role == null){
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
					//暂做处理，公安用户，阿里用户，管理员无法删除，根据roleKey判断
					Roles role = roleService.getById(string);
					if ("police".equals(role.getRoleKey()) ||
						"ali".equals(role.getRoleKey()) ||
						"root".equals(role.getRoleKey())) {
						
						map.put("flag", "false");
						map.put("content", "此用户暂不能删除");
						return map;
					} else {
						roleService.delete(string);
					}
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
	 * 更新类型
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("update")
	public Map<String, Object> update(@RequestParam String CSRFToken, Model model, Roles role) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			if(role.getEnable() == null){
				role.setEnable("0");
			}
			roleService.update(role);
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "更新失败");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("findbyAccountRole")
	public Roles findbyAccountRole(Model model, String accountId) {
		return roleService.findbyAccountRole(accountId);
	}
	
	@ResponseBody
	@RequestMapping("addAccRole")
	public Map<String, Object> addAccRole(Model model, String accountId,String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				if(!Common.isEmpty(ids)){
					String[] roleIds = ids.split(",");
					roleService.addAccRole(accountId,Arrays.asList(roleIds));
				}
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "添加失败");
		}
		return map;
	}
	
}

package com.hisign.publicsafety.controller.ccp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.Resources;
import com.hisign.publicsafety.entity.ccp.BlackPlatnum;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataRequest;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.service.DicService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.service.ccp.BlackPlatnumService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;

/**
 * 
 * @author wangk
 * @time：2016年9月19日14:53:18
 * 车牌预警管理controller
 */
@Controller
@RequestMapping("/ccp/blackPlat/")
public class BlackPlatController extends BaseController{
	@Inject
	private BlackPlatnumService blackPlatnumService;
	
	@Inject
	private IdService idService;
	
	@Inject
	private DicService dicService;
	
	/**
	 * 跳转至车牌预警管理页面
	 * @param model
	 * @param menu
	 * @param pageNow
	 * @return
	 */
	@RequestMapping("index")
	public String list(Model model, Resources menu, String pageNow) {
		
		JSONArray ja = new JSONArray(); 
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
		return Common.CCP_PATH+"/blackPlat/blackPlatList";
	}

	/**
	 * jgrid表格查询
	 * @param blackPlat
	 * @param dataRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query")
	public DataResponse<BlackPlatnum> query(BlackPlatnum blackPlatnum, DataRequest dataRequest) {
		
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		pageView = blackPlatnumService.query(getPageView(String.valueOf(page),String.valueOf(rows)), blackPlatnum);
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
	public Map<String, Object> add(@RequestParam String CSRFToken, BlackPlatnum blackPlatnum) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//判断是否有相同车牌号
			BlackPlatnum blackPlatnum1 = blackPlatnumService.findByPlatnum(blackPlatnum.getPlatNum());
			if(blackPlatnum1 != null){
				map.put("flag", "false");
				map.put("content", "此车牌号已存在，新增失败");
				return map;
			}
			blackPlatnum.setId(String.valueOf(idService.getId()));
			blackPlatnum.setStatus("否");
			blackPlatnum.setCreateTime(new Date());			
			blackPlatnumService.addBlackPlatnum(blackPlatnum);
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "新增失败");
		}
		return map;
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
	public Map deleteById(Model model, String id) {
		Map map = new HashMap();
		try {
//			String id[] = ids.split(",");
//			for (String string : id) {
//				if(!Common.isEmpty(string)){
//					blackPlatnumService.delete(string);
//				}
//			}
			if(StringUtils.isNotEmpty(id)){
				blackPlatnumService.delete(id);
			}
			map.put("flag", "true");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "删除失败");
		}
		return map;
	}
	
}

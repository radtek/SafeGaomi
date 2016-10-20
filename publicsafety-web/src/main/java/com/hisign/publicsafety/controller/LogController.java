package com.hisign.publicsafety.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hisign.publicsafety.entity.Log;
import com.hisign.publicsafety.entity.Resources;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataRequest;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.LogService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;

/**
 * 
 * @author zhaoqian
 * @time：2015-08-10
 * 日志管理controller
 */
@Controller
@RequestMapping("/background/log/")
public class LogController extends BaseController{
	@Inject
	private LogService logService;
	
	/**
	 * 查询客户登陆信息
	 * @param model
	 * @param log
	 * @param pageNow
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query")
	public DataResponse<Log> query(Log log, DataRequest dataRequest){
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		PageView pageview = getPageView(String.valueOf(page),String.valueOf(rows));
		pageView = logService.query(pageview, log);
		return pageView.getDataResponse();
	}
	@RequestMapping("list")
	public String list(Model model, Resources menu, String pageNow) {
		
		/**
		 * 添加token验证
		 */
		model.addAttribute("csrf", CsrfTokenManager.getToken());
		return Common.BACKGROUND_PATH+"/log/logList";
	}
	
	/**
	 * 删除
	 * 
	 * @param model
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("deleteById")
	public String deleteLogByIds(Model model, String ids) {
		try {
			String id[] = ids.split(",");
			for (String string : id) {
				if(!Common.isEmpty(string)){
					logService.delete(string);
				}
			}
			return "success";
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			return "false";
		}
	}
}

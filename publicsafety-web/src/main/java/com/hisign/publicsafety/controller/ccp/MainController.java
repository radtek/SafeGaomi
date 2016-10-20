package com.hisign.publicsafety.controller.ccp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.ccp.ClueInfo;
import com.hisign.publicsafety.entity.ccp.ClueProviderInfo;
import com.hisign.publicsafety.service.ccp.ClueInfoService;
import com.hisign.publicsafety.service.ccp.ClueProviderInfoService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.DateUtils;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;

/**
 * 首页controller
 * @author hotdog
 *2015-11-23
 */
@Controller
@RequestMapping("/ccp/main/")
public class MainController  extends BaseController{
	
	@Inject
	private ClueInfoService clueInfoService;
	
	@Inject
	private ClueProviderInfoService clueProviderInfoService;
	
	/**
	 * 进入首页模块
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		
		/**
		 * 添加token验证
		 */
		model.addAttribute("csrf", CsrfTokenManager.getToken());
		
		return  Common.CCP_PATH + "/main";
	}
	
	/**
	 * 初始化图表数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("initChartData")
	public Map initChartData () {
		Map map = new HashMap<String, Object>();
		try {
			//获取当前月及前五个月的数据
			List listForX = new ArrayList();
			List listForClue = new ArrayList();
			List listForProvider = new ArrayList();
			ClueInfo obj  = null;
			ClueProviderInfo obj1 = null;
			
			SimpleDateFormat sdf=new SimpleDateFormat(DateUtils.YMD);
			Calendar  cd = null;
			
			for (int i = 0; i < 6; i ++) {
				obj = new ClueInfo();
				obj1= new ClueProviderInfo();
				
				cd = Calendar.getInstance();
				
				//加载横坐标 
				int month = cd.get(Calendar.MONTH) + 1 - i; //获取月分
				month = (month <= 0) ? month+12 : month;
				String monthEng = Common.monthToEng(month);
				String monthStr = Common.monthToUppder(month)+"月("+monthEng+")";
				listForX.add(monthStr);
				
				cd.add(Calendar.MONTH, -i);
				cd.set(Calendar.DAY_OF_MONTH,1); //当前月的第一天
				obj.setCreateBegDatetime(sdf.format(cd.getTime()));
				obj1.setCreateBegDatetime(sdf.format(cd.getTime()));
				
				cd.set(Calendar.DAY_OF_MONTH, cd.getActualMaximum(Calendar.DAY_OF_MONTH)); //当前月最后一天
				obj.setCreateEndDatetime(sdf.format(cd.getTime()) + " 23:59");
				obj1.setCreateEndDatetime(sdf.format(cd.getTime()) + " 23:59");
				  
				//加载各月份线索数量
				listForClue.add(clueInfoService.queryCount(obj));
				
				//加载各月份用户注册量
				listForProvider.add(clueProviderInfoService.queryCount(obj1));
			}
			Collections.reverse(listForX);
			Collections.reverse(listForClue);
			Collections.reverse(listForProvider);
			
			map.put("flag", "success");
			map.put("dataForX", listForX);
			map.put("dataForClue", listForClue);
			map.put("dataForProvider", listForProvider);
			map.put("buttonContent", listForX.get(0)+"至"+listForX.get(listForX.size()-1)+"统计曲线图");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
		}
		return map;
	}
}

package com.hisign.publicsafety.controller.ccp;

import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.util.LogUtil;
/**
 * 查询服务器当前时间controller
 * 
 * @author wangk 2016年9月9日15:54:12
 */
@Controller
@RequestMapping("/ccp/serverTime/")
public class ServerTimeController extends BaseController {

	
	/**
	 * 查询服务器时间
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryServerTime")
	public Map queryTime() {
		Map map = new HashMap();
		//JSONObject object = new JSONObject();            
		try {
			Date now = new Date(System.currentTimeMillis()); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(now); 
	        map.put("reply", "1");
			map.put("content", "服务器获取时间成功");
			map.put("time", time);  
		}catch (Exception e) {
	        LogUtil.errStack2Log4j(e);
			map.put("reply", "0");
			map.put("content", "服务器获取时间失败");
		}
		return map;
	}
	
	public static void main(String[] args) {
		try {
			 URL url=new URL("http://www.bjtime.cn");//取得资源对象
		       URLConnection uc=url.openConnection();//生成连接对象
		       uc.connect(); //发出连接
		       long ld=uc.getDate(); //取得网站日期时间
		       Date date=new Date(ld); //转换为标准时间对象
		       //分别取得时间中的小时，分钟和秒，并输出
		       System.out.print(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

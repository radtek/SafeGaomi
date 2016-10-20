package com.hisign.publicsafety.controller.ccp;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.ccp.CheckApk;
import com.hisign.publicsafety.service.ccp.CheckApkService;
import com.hisign.publicsafety.util.LogUtil;
/**
 * 更新版本controller
 * 
 * @author wangk 2016年9月12日11:51:10
 */
@Controller
@RequestMapping("/ccp/check/")
public class checkApkController extends BaseController {
	
	@Inject
	private CheckApkService checkApkService;
	/**
	 * 更新版本
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checkApk")
	public Map checkApk(String apkNo) {
		Map map = new HashMap();
		try {
			if(apkNo==null || "".equals(apkNo)){
				map.put("reply", "0");
				map.put("content", "传过来的版本号为空");
				return map;
			}
			CheckApk checkApk=checkApkService.findByApk();
			if(checkApk != null){
				String apknonew=checkApk.getApkno();
				if(!"".equals(apknonew) && !apknonew.equals(apkNo)){
					String path=request.getSession().getServletContext().getRealPath("static");
					//String url = "http://192.168.40.23/static/HisignClueCollection-debug.apk";  
					String url = "http://223.99.173.206:9099/static/HisignClueCollection-release.apk";
					
					map.put("reply", "2");
					map.put("path", url);
					map.put("content", apknonew);
				}else{
					map.put("reply", "1");
					map.put("content", "当前版本为最新版本");
				}	 
			}else{
				map.put("reply", "0");
				map.put("content", "数据库没有版本信息记录");
			}
		}catch (Exception e) {
	        LogUtil.errStack2Log4j(e);
			map.put("reply", "0");
			map.put("content", "更新版本失败");
		}
		return map;
	}
	
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

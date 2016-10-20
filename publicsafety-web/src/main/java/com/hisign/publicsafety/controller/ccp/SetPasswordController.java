package com.hisign.publicsafety.controller.ccp;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.ccp.ClueProviderInfo;
import com.hisign.publicsafety.service.ccp.ClueProviderInfoService;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.util.Md5Tool;

/**
 * 重置密码controller
 * 
 * @author wangk 2016年9月9日13:27:57
 */
@Controller
@RequestMapping("/ccp/setPass/")
public class SetPasswordController extends BaseController {

	@Inject
	private ClueProviderInfoService clueProviderInfoService;


	/**
	 * 设置新的密码
	 *  
	 * @return
	 */
	@ResponseBody
	@RequestMapping("setPassWord")
	public Map setPassword(String phoneNum,String passWord) {
		Map map = new HashMap();
		if (StringUtils.isEmpty(phoneNum)) {
			map.put("reply", "0");
			map.put("content", "手机号为空");
			return map;
		}else if(StringUtils.isEmpty(passWord)){
			map.put("reply", "0");
			map.put("content", "用户密码没有输入");
		}
		try {
			ClueProviderInfo clueProvider = clueProviderInfoService.findByPhoneNum(phoneNum);
			clueProvider.setPassWord(Md5Tool.getMd5(passWord));
			clueProviderInfoService.update(clueProvider);
			map.put("reply", "1");
			map.put("content", "重置密码成功");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("reply", "0");
			map.put("content", "重置密码失败");
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

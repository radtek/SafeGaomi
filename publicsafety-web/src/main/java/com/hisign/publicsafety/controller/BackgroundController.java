package com.hisign.publicsafety.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.api.CacheSessionService;
import com.hisign.publicsafety.common.bean.cookie.UserInfo;
import com.hisign.publicsafety.entity.Account;
import com.hisign.publicsafety.service.AccountService;
import com.hisign.publicsafety.service.auth.InitAfterAuth;
import com.hisign.publicsafety.service.auth.SigAuthInter;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.util.Md5Tool;
import com.hisign.publicsafety.util.WebUtil;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;
import com.hisign.publicsafety.web.entity.ChkCodeEntity;
import com.hisign.publicsafety.web.tool.O2oCookieHelper;

/**
 * 进行管理后台框架界面的类
 * @author zhaoqian
 * @time：2015-08-10
 */
@Controller
@RequestMapping ("/")
public class BackgroundController extends BaseController
{
	@Autowired
	private AccountService accountService;
	@Autowired
	private AuthenticationManager o2oAuthenticationManager;
	@Inject
	private SigAuthInter sigAuthInter;
	@Autowired
	private InitAfterAuth initAfterAuth;
	@Autowired
	private CacheSessionService cacheSessionService;
	
	/**
	 * @return
	 */
	@RequestMapping ("login")
	public String login(Model model, HttpServletRequest request)
	{
		String page = WebUtil.getSysCheckPagePara(request);
		if (page != null) {
			return processPagePara(page);
		}
		/**
		 * 添加token验证
		 */
		model.addAttribute("token", sigAuthInter.getToken());
		String csrfToken = CsrfTokenManager.getToken();
		model.addAttribute("csrf", csrfToken);
		
		return Common.BACKGROUND_PATH+"/framework/login";
	}

	/**
	 * 跳转到系统检测页面,检测cookie是否启用,超时提示等
	 * @param page
	 * @return
	 */
	private String processPagePara(String page) {
		request.setAttribute("contextPath", request.getContextPath());
		return Common.JSP_PATH + "/sys/" + page;
	}
	
	/**
	 * 登录校验
	 * @param CSRFToken token验证
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("submitlogin")
	public String submitlogin(RedirectAttributes attr, @RequestParam String CSRFToken, String username,String password,HttpServletRequest request) throws Exception{
		try {
			if (!request.getMethod().equals("POST")) {
				attr.addFlashAttribute("error","支持POST方法提交！");
				return "redirect:/login.html";
			}
			if (Common.isEmpty(username) || Common.isEmpty(password)) {
				attr.addFlashAttribute("error","用户或密码不正确！");
				return "redirect:/login.html";
			}
			// 验证用户账号与密码是否正确
			Account user = this.accountService.querySingleAccount(username);
			if (user == null) {
				attr.addFlashAttribute("error", "用户或密码不正确！");
				return "redirect:/login.html";
			} else if (!Md5Tool.getMd5(password).equals(user.getPassword())) {
				attr.addFlashAttribute("error", "用户或密码不正确！");
				return "redirect:/login.html";
			}
			Authentication authentication = o2oAuthenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			// Spring Security上下文放入缓存
		    cacheSessionService.setAttribute(Constant.SPRING_SECURITY_CONTEXT_CACHE, securityContext);

		    // 当验证都通过后，把用户信息放在Cookie里
			UserInfo userInfo = new UserInfo();
			userInfo.setAccountId(user.getId());
			userInfo.setLogin(true);
			userInfo.setLastAccessTime(System.currentTimeMillis());
			O2oCookieHelper.setUserInfo(userInfo);
		    // 当验证都通过后，把用户信息放在session里
			initAfterAuth.initLoginUserRelated(securityContext, user);
			
			request.removeAttribute("error");
		} catch (AuthenticationException ae) {  
		    attr.addFlashAttribute("error","登录异常，请联系管理员！");
			return "redirect:/login.html";
		}
		return "redirect:/main.html";
	}
	
	/**
	 * 进入首页
	 * @return
	 */
	@RequestMapping ("main")
	public String intoModelByRole (RedirectAttributes attr, HttpServletRequest request) {
		return "redirect:/ccp/main/index.html";
	}
	
	/**
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping ("index")
	public String index(Model model)
	{   
		return "index";
	}
	
	/**
	 * 获取某个用户的权限资源
	 * @author lanyuan
	 * Email：mmm333zzz520@163.com
	 * date：2014-3-4
	 * @param request
	 * @return
	 */
	@RequestMapping ("findAuthority")
	@ResponseBody
	public Map<String, Object> findAuthority(HttpServletRequest request, String clickedRes){
		
		Map<String, Object> map = new HashMap<String, Object>();
		  
		List<String> authorityStrList = (List<String>)cacheSessionService.getAttribute(Constant.AUTHORITY_STRS_KEY);

		map.put("authorities", authorityStrList);
		map.put("needPermission", clickedRes);
		return map;
	}
	
	@ResponseBody
	@RequestMapping ("install")
	public Map<String, Object> install(Model model)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("success", "初始化成功!");
		} catch (Exception e) {
			map.put("error", "初始化失败  ---------- >   "+e);
		}
		return map;
	}
	
	@RequestMapping("/download")
	public void download(
	String fileName, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		String ctxPath = RequestContextUtils.getWebApplicationContext(request).getServletContext().getRealPath(
				"/")
				+ "\\" + "filezip\\";
		String downLoadPath = ctxPath + fileName;
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	
}

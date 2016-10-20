package com.hisign.publicsafety.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.abs.BaseBean;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.LogUtil;
/**
 * Controller基类
 * @author fankai
 * @time：2015-08-10
 */

public class BaseController extends BaseBean {

	public static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	protected  HttpServletRequest request;
	
	protected  HttpServletResponse response;
	
	 /**
	  * 统一异常处理方法
	  * @param ex
	  * @return
	  */
	 @ExceptionHandler(Exception.class)
	public String exp(Exception ex) {
		LogUtil.errStack2Log4j(ex);
		// 根据不同错误转向不同页面  
		if (ex instanceof Exception) {
			return "error";
		}
		return "error";
	}
	
	protected PageView pageView = null;
	/**
	 * 
	 * @param pageNow 当前页码
	 * @param pagesize 每一页的记录数
	 * @return 分页数据
	 */
	public PageView getPageView(String pageNow,String pagesize) {
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pagesize), Integer.parseInt(pageNow));
		}
		return pageView;
	}
	
     /**
      * 设置request和response的值
      * @param request
      * @param response
      */
	@ModelAttribute
	 public void setReqAndResp(HttpServletRequest request,HttpServletResponse response){
		 this.request=request;
		 this.response=response;
	 }
	
	/**
	 * 获得HttpservletRequest 对象
	 * @return httpServletRequest 对象
	 */
	public HttpServletRequest getHttpServletRequest(){
		return  request;
	}
	
	/**
	 * 获得 HttpServletResponse 对象
	 * @return httpServletResponse 对象
	 */
	public HttpServletResponse getHttpServletResponse(){
	    return response;
	}
	
	
	
}

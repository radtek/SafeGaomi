/**
 * 
 */
package com.hisign;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.util.Common;

/**
 * @author chailiangzhi
 * @date 2015-8-21
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-application.xml", "classpath:spring-security.xml",
		"classpath:spring-servlet.xml", "classpath:spring-test.xml" })
// 加载配置
public class BaseUnit4Test {

	public static MockHttpSession session;
	public static MockHttpServletRequest request;
	public static MockHttpServletResponse response;

	@BeforeClass
	public static void startSession() {
		session = new MockHttpSession();
	}

	@AfterClass
	public static void endSession() {
		session.clearAttributes();
		session = null;
	}

	@BeforeClass
	public static void startRequest() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		request.setSession(session);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	@AfterClass
	public static void endRequest() {
		((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).requestCompleted();
		RequestContextHolder.resetRequestAttributes();
		request = null;
	}

	protected PageView pageView = null;

	/**
	 * 
	 * @param pageNow 当前页码
	 * @param pagesize 每一页的记录数
	 * @return 分页数据
	 */
	public PageView getPageView(String pageNow, String pagesize) {
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		if (Common.isEmpty(pagesize)) {
			pagesize = "10";
		}
		pageView.setPageSize(Integer.parseInt(pagesize));
		return pageView;
	}

	@Test
	public void testApp() {
		Assert.assertTrue(true);
	}
}

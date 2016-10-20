package com.hisign.publicsafety.web.beans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

import com.hisign.publicsafety.util.StringUtil;

/**
 * @author chailiangzhi
 * @date 2015-9-10
 * 过滤(替换HTML标签)防御XSS攻击和SQL注入
 */
public class HttpParaCleanRequestWrapper extends HttpServletRequestWrapper {
	/**
	 * @param servletRequest
	 */
	public HttpParaCleanRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	/** 
	 * 重写getParameterValues
	 */
	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	/** 
	 * 重写getParameter
	 */
	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	/**
	 * 替换HTML标签和SQL单引号
	 * @param value
	 * @return
	 */
	private String cleanXSS(String value) {
		value = StringUtil.htmlEncode(value);
		value = StringEscapeUtils.escapeSql(value);
		return value;
	}

}
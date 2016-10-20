/**
 * 
 */
package com.hisign.publicsafety.util;

import org.slf4j.LoggerFactory;

/**
 * @author chailiangzhi
 * @date 2015-7-13
 * 日志工具
 */
public class LogUtil {
	/**
	 * 在Log4j中输出异常堆栈
	 * @param e
	 */
	public static void errStack2Log4j(Exception e) {
		StringBuilder sb = new StringBuilder();
		sb.append(e.getMessage());
		sb.append("\r\n");
		StackTraceElement[] stackTraces = e.getStackTrace();
		for (int i = 0; i < stackTraces.length; i++) {
			sb.append(stackTraces[i]);
			sb.append("\r\n");
		}
		LoggerFactory.getLogger(LogUtil.class).error(sb.toString());
	}

	/**
	 * 自定义日志
	 * @param fileName
	 * @param msg
	 */
	public static void customLog(String fileName, String msg) {
		String customLogOn = PropertiesUtils.findPropertiesKey("customLogOn");
		if (!"true".equals(customLogOn)) {
			return;
		}
		String userHome = System.getProperty("user.home");
		FileUtil.writeFile(userHome + "/.customLog/" + fileName, msg, true);
	}
}

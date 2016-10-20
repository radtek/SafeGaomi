package com.hisign.publicsafety.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 对属性文件操作的工具类
 * 获取，新增，修改
 * 注意：	以下方法读取属性文件会缓存问题,在修改属性文件时，不起作用，
 *　InputStream in = PropertiesUtils.class.getResourceAsStream("/config.properties");
 *　解决办法：
 *　String savePath = PropertiesUtils.class.getResource("/config.properties").getPath();
 * @author ly
 * 2015-08-20
 * @version 1.0v
 */
public class PropertiesUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	
	private static Properties PROPERTIES=initProperties();
	
	/**
	 * 获取属性文件的数据 根据key获取值
	 * @param fileName 文件名　(注意：加载的是src下的文件,如果在某个包下．请把包名加上)
	 * @param key
	 * @return
	 */
	public static String findPropertiesKey(String key) {
		if (PROPERTIES == null) {
			logger.warn("PROPERTIES is null,can not findPropertiesKey,key={}",key);
			return null;
		}
		try {
			return PROPERTIES.getProperty(key);
		} catch (Exception e) {
			return "";
		}
		
	}

	/**
	 * 返回　Properties
	 * @param fileName 文件名　(注意：加载的是src下的文件,如果在某个包下．请把包名加上)
	 * @param 
	 * @return
	 */
	public static Properties initProperties(){
		Properties prop = new Properties();
		URL url = PropertiesUtils.class.getResource("/config.properties");
		String savePath = "";
		try {
			savePath = URLDecoder.decode(url.getFile(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			LogUtil.errStack2Log4j(e1);
		}
		//以下方法读取属性文件会缓存问题
//		InputStream in = PropertiesUtils.class
//				.getResourceAsStream("/config.properties");
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(savePath));  
			prop.load(in);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			return null;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				logger.error(e.toString());
			}
		}
		return prop;
	}
	/**
	 * 写入properties信息
	 * 
	 * @param key
	 *            名称
	 * @param value
	 *            值
	 */
	public static void modifyProperties(String key, String value) {
		if (PROPERTIES == null) {
			logger.warn("PROPERTIES is null,can not modifyProperties");
			return;
		}
		try {
			// 从输入流中读取属性列表（键和元素对）
			PROPERTIES.setProperty(key, value);
			String path = PropertiesUtils.class.getResource("/config.properties").getPath();
			FileOutputStream outputFile = new FileOutputStream(path);
			PROPERTIES.store(outputFile, "modify");
			outputFile.close();
			outputFile.flush();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}

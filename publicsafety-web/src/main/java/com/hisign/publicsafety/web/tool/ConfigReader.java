package com.hisign.publicsafety.web.tool;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ConfigReader {

	private static final Logger LOG = Logger.getLogger(ConfigReader.class);
	private static final String path_conf = "path_config.properties";
	private static String upload_img_path = null;
	private static long upload_img_maxsize = 0;

	public ConfigReader() {
		
	}
	/**
	 * @param propFile 路径.
	 * @return properties.
	 */
	public static Properties readConfigbak(String propFile) {
		Properties p = new Properties();
		try {
			p.load(ConfigReader.class.getResourceAsStream(propFile));
		} catch (IOException e) {
			LOG.error("加载配置异常:", e);
		}
		return p;
	}

	/**
	 * @param propFile 路径.
	 * @return proerties.
	 */
	public static Properties readConfig(String propFile) {
		Properties p = new Properties();
		FileInputStream input = null;
		try {
			input = new FileInputStream(propFile);
			p.load(input);
		} catch (IOException e) {
			LOG.error("加载配置异常:", e);
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				LOG.error("关闭流异常:", e);
			}
		}
		return p;
	}

	public static void readAllConfig() throws Exception{
		String path = ConfigReader.class.getClassLoader().getResource("").toURI().getPath(); 
		Properties pro = readConfig(path + path_conf);
		
		upload_img_path = pro.getProperty("upload_img_path");
		LOG.info("config--upload_img_path:"+upload_img_path);
		
		upload_img_maxsize = Long.parseLong(pro.getProperty("upload_img_maxsize"));
		LOG.info("config--upload_img_maxsize:"+upload_img_maxsize);
	}
	
	@PostConstruct
	public void initStartUp() throws Exception {
		LOG.info("-------初始化配置开始------");
		readAllConfig();
		LOG.info("-------初始化配置完成-----");
	}
	public static String getUpload_img_path() {
		return upload_img_path;
	}
	public static void setUpload_img_path(String upload_img_path) {
		ConfigReader.upload_img_path = upload_img_path;
	}
	public static long getUpload_img_maxsize() {
		return upload_img_maxsize;
	}
	public static void setUpload_img_maxsize(long upload_img_maxsize) {
		ConfigReader.upload_img_maxsize = upload_img_maxsize;
	}

}

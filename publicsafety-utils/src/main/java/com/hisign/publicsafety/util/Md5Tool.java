package com.hisign.publicsafety.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * MD5工具
 * @date 2015-9-10
 * 
 */
public class Md5Tool {
	
	public static String getMd5(String password){
		String str = "";
		if(password !=null && !password.equals("")){
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
//				BASE64Encoder base = new BASE64Encoder();
				//加密后的字符串
				str = Base64.encodeBase64String(md.digest(password.getBytes("utf-8")));
			} catch (Exception e) {
				LogUtil.errStack2Log4j(e);
			}
		}
		return str;
	}
	public static void main(String[] args) {
		System.err.println(getMd5("root"));
	}
}

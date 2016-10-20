package com.hisign.publicsafety.web.tool;


import java.io.File;

public class FileUtil {

	/**
	 * 根据年月日创建文件夹
	 * @return
	 */
	public static String getSavePath(String path) {
		StringBuffer uploadPath = new StringBuffer(path);
		uploadPath.append(CalendarUtil.getYear());
		uploadPath.append("/");
		uploadPath.append(CalendarUtil.getMonth());
		uploadPath.append("/");
		uploadPath.append(CalendarUtil.getDay());
		File file = new File(path + uploadPath);
		file.mkdirs();
		uploadPath.append("/");
		return uploadPath.toString();
	}

	/**
	 * 获取文件扩展名
	 * @param filename
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * 获取不带扩展名的文件名
	 * @param filename
	 * @return
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

}

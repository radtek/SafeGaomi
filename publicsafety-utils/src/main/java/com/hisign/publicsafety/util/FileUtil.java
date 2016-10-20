package com.hisign.publicsafety.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 
 * Date : 2013-9-5 上午11:31:25
 * @author YuanXiaolong
 * 读写文件操作的工具
 */
public class FileUtil {

	/**
	 * 文件是否存在
	 * @param fileName
	 * @return
	 */
	public static boolean isExists(String fileName){
		File file = new File(fileName);
		
		return file.exists();
	}
	
	/**
	 * 创建文件
	 * @param fileName
	 * @return
	 */
	public static boolean createFile(String fileName){
		boolean flag = false;
		File file = new File(fileName);
		flag = file.exists();
		if(!flag){	//flag == false;
			//文件不存在
			try {
				File parent = new File(file.getParent());
				if(!parent.exists())
					flag = parent.mkdirs();
				flag = file.createNewFile();
			} catch (IOException e) {
				LogUtil.errStack2Log4j(e);
			}
		}
		return flag;
	}
	
	/**
	 * 创建目录
	 * @param path
	 * @return
	 */
	public static boolean createDir(String path){
		File file = new File(path);
		if(!file.exists()){
			//path不存在
			return file.mkdirs();
		}
		
		return true;
	}
	
	/**
	 * 创建文件
	 * @param fileNames
	 * @return
	 */
	public static boolean createFiles(Set<String> fileNames){
		boolean flag = true;
		for(String fileName : fileNames)
			if(flag)
				flag = createFile(fileName);
			else
				return flag;
		
		return flag;
	}
	
	
	/**
	 * 删除文件
	 * @param fileName
	 * @return
	 */
	public static boolean removeFile(String fileName){
		
		return removeFile(new File(fileName));
	}
	
	/**
	 * 删除文件
	 * @param fileNames
	 * @return
	 */
	public static boolean removeFiles(Set<String> fileNames){
		boolean flag = true;
		for(String fileName : fileNames)
			if(flag)
				flag = removeFile(fileName);
			else
				return flag;
		
		return flag;
	}
	
	/**
	 * 删除文件
	 * @param file
	 * @return
	 */
	public static boolean removeFile(File file){
		boolean flag = false;
		flag = file.exists();
		if(flag){	//flag == true; 文件存在
			if(file.isFile())
				flag = file.delete();
			else if(file.isDirectory() && file.listFiles().length == 0)
				//如果是目录，必须是空目录
				flag = file.delete();
		}
		return flag;
	}
	
	/**
	 * 清空目录
	 * @param path
	 * @return
	 */
	public static boolean removePath(String path){
		return removePath(new File(path));
	}
	
	/***
	 * 清空目录
	 * @param path
	 * @return
	 */
	public static boolean removePath(File path){
		boolean flag = false;
		if(path.exists() && path.isDirectory()){
			File[] files = path.listFiles();
			if(path.isDirectory() && files.length == 0)
				return path.delete();
				
			for(File file : files){
				flag = removePath(file);
			}
			
			return flag;
		}
		
		return true;
	}
	
	/**
	 * 清空目录
	 * @param path
	 * @return
	 */
	public static boolean clean(File path){
		boolean flag = true;
		if(path.isFile()){
			removeFile(path);
		}else{
			File[] files = path.listFiles();
			for(File file : files){
				clean(file);
			}
			removeFile(path);
		}
		
		return flag;
	}
	
	/**
	 * 清空目录
	 * @param path
	 * @return
	 */
	public static boolean clean(String path){
		
		return clean(new File(path));
	}
	
	/**
	 * 重命名文件
	 * @param orginName 原文件名字
	 * @param newName	新名字
	 * @return
	 */
	public static boolean renameFile(String orginName, String newName){
		boolean flag = false;
		File file = new File(orginName);
		File newFile = new File(newName);
		
		if(file.exists() && !newFile.exists()){	//flag == true;
			//文件存在
			flag = file.renameTo(new File(newName));
		}
		
		return flag;
	}
	
	/**
	 * 读取二进制文件，返回字节数组
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static byte[] readBinaryFile(String fileName) throws IOException{
		Map map = new HashMap();
		InputStream is = new FileInputStream(new File(fileName));
		byte[] buffer = new byte[is.available()];
		
		is.read(buffer);
		
		return buffer;
//		int len = 0;
//		int i = 1;
//		int totalLen = 0;
//		while((len = is.read(buffer)) > 0){
//			map.put(i+ "," + len, buffer);
//			totalLen += len;
//			i++;
//		}
//		
//		is.close();
//		
//		byte[] bytes = new byte[totalLen];
//		
//		Set<Entry<String, byte[]>> entries = map.entrySet();
//		
//		int idx = 0;
//		for(Entry<String, byte[]> entry : entries){
//			len = Integer.parseInt(entry.getKey().split(",")[1]);
//			byte[] _buffer = entry.getValue();
//			
//			for(int j = 0; j < len; j++){
//				bytes[idx] = _buffer[j];
//				idx++;
//			}
//		}
//		
//		return bytes;
	}
	
	/**
	 * 
	 * @param fileName
	 * @param encode
	 * @return
	 * @throws IOException 
	 */
	public static String readFileUseByte(String fileName, String encode) {
		String content = "";
		File file = new File(fileName);
		if(file.isFile()){
			try {
				byte[] buff = readBinaryFile(fileName);
				
				content = new String(buff, encode);
			}catch (IOException e) {
				LogUtil.errStack2Log4j(e);
				return content;
			}
		}
		
		return content;
	}
	
	/**
	 * 读取文本文件,返回读取到的字符串
	 * @param fileName
	 * @return
	 */
	public static String readFile(String fileName, String encode){
		StringBuilder  sb = new StringBuilder();
		File file = new File(fileName);
		if(file.isFile()){
			try {
				InputStream is = new FileInputStream(file);
				InputStreamReader ir = new InputStreamReader(is, encode);
				BufferedReader reader = new BufferedReader(ir);
				while(reader.ready()){
					sb.append(reader.readLine() + "\n");
				}
				
				is.close();
				ir.close();
				reader.close();
			} catch (FileNotFoundException e) {
				LogUtil.errStack2Log4j(e);
			} catch (IOException e) {
				LogUtil.errStack2Log4j(e);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 读取文本文件,返回读取到的字符串
	 * @param 
	 * @return
	 */
	public static String readFile(InputStream is, String encode) {
		StringBuilder sb = new StringBuilder();
		try {
			InputStreamReader ir = new InputStreamReader(is, encode);
			BufferedReader reader = new BufferedReader(ir);
			while (reader.ready()) {
				sb.append(reader.readLine() + "\n");
			}
			is.close();
			ir.close();
			reader.close();
		} catch (FileNotFoundException e) {
			LogUtil.errStack2Log4j(e);
		} catch (IOException e) {
			LogUtil.errStack2Log4j(e);
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static List readFile2List(String fileName){
		List list = new ArrayList();
		File file = new File(fileName);
		if(file.isFile()){
			try {
				InputStream is = new FileInputStream(file);
				InputStreamReader ir = new InputStreamReader(is);
				BufferedReader reader = new BufferedReader(ir);
				while(reader.ready()){
					list.add(reader.readLine() + "\n");
				}
				

				is.close();
				ir.close();
				reader.close();
			} catch (FileNotFoundException e) {
				LogUtil.errStack2Log4j(e);
			} catch (IOException e) {
				LogUtil.errStack2Log4j(e);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<String> readFile2List(InputStream is) {
		List<String> list = new ArrayList<String>();
		try {
			InputStreamReader ir = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(ir);
			while (reader.ready()) {
				list.add(reader.readLine());
			}
			is.close();
			ir.close();
			reader.close();
		} catch (FileNotFoundException e) {
			LogUtil.errStack2Log4j(e);
		} catch (IOException e) {
			LogUtil.errStack2Log4j(e);
		}
		return list;
	}
	
	/**
	 * 将字符串write写入文件
	 * @param fileName 要写入的文件名称
	 * @param write	   要写的字符串
	 * @param flag true 表示追加， false 表示覆盖
	 * @return
	 */
	public static boolean writeFile(String fileName, String write, boolean flag){
		boolean _flag = false;
		File file = new File(fileName);
		if(file.exists()){
			if(file.isFile()){
				try {
					BufferedWriter writer = null;
					String[] strArr = write.split("\n");
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, flag)));
					for(String str : strArr){
						writer.write(str);
						writer.newLine();
					}
					writer.flush();
					writer.close();
					
					_flag = true;
				} catch (IOException e) {
					LogUtil.errStack2Log4j(e);
				}
			}
		}else{
			_flag = createFile(fileName);
			if(_flag)
				_flag = writeFile(fileName, write, flag);
		}
		
		return _flag;
	}
	
	public static boolean writeFileSimple(String fileName, String write, boolean flag) {
		try {
			BufferedWriter writer = null;
			String[] strArr = write.split("\n");
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, flag)));
			for (String str : strArr) {
				writer.write(str);
				writer.newLine();
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LogUtil.errStack2Log4j(e);
			return false;
		}
		return true;
	}
	
	/**
	 * 将字节数组bytes写入文件
	 * @param fileName 要写入的文件名称
	 * @param bytes	   要写的字符串
	 * @param flag true 表示追加， false 表示覆盖
	 * @return
	 */
	public static boolean writeBinaryFile(String fileName, byte[] bytes, boolean flag){
		boolean _flag = false;
		File file = new File(fileName);
		if(file.exists()){
			if(file.isFile()){
				try {
					OutputStream os = new FileOutputStream(new File(fileName), flag);
					os.write(bytes);
					os.flush();
					os.close();
					_flag = true;
				} catch (IOException e) {
					LogUtil.errStack2Log4j(e);
				}
			}
		}else{
			_flag = createFile(fileName);
			if(_flag)
				_flag = writeBinaryFile(fileName, bytes, flag);
		}
		
		return _flag;
	}
	
	/**
	 * 将字节数组bytes写入文件
	 * @param fileName 要写入的文件名称
	 * @param bytes	   要写的字符串
	 * @param flag true 表示追加， false 表示覆盖
	 * @return
	 */
	public static OutputStream writeBinaryFile(String fileName, byte[] bytes){
		OutputStream os = null;
		File file = new File(fileName);
		if(file.exists()){
			if(file.isFile()){
				try {
					os = new FileOutputStream(new File(fileName));
					os.write(bytes);
					os.flush();
				} catch (IOException e) {
					LogUtil.errStack2Log4j(e);
				}
			}
		}else{
			if(createFile(fileName))
				os = writeBinaryFile(fileName, bytes);
		}
		
		return os;
	}
	
	/**
	 * 取得path下的满足固定扩展名称的文件列表
	 * @param path
	 * @param extensions
	 * @return
	 */
	public static List listFile(String path, String... extensions){
		List list = new ArrayList();
		
		File file = new File(path);
		if(file.exists() && file.isDirectory()){
			
			String[] files = file.list();
			for(String _file : files){
				for(String extension : extensions){
					if(_file.toLowerCase().endsWith(extension.toLowerCase())){
						list.add(_file);
					}
				}
			}
			
		}
		
		return list;
	}
	
	/**
	 * 复制srcFile文件至destFile
	 * @param srcFile
	 * @param destFile
	 * @return
	 */
	public static boolean copyFile(String srcFile, String destFile){
		File file = new File(srcFile);
		if(file.exists() && file.isFile()){
			try {
				InputStream in = new FileInputStream(file);
				
				OutputStream out = new FileOutputStream(destFile);
				
				byte[] buf = new byte[4096];
				
				while(in.read(buf) != -1){
					out.write(buf);
				}
				
				out.flush();
				in.close();
				out.close();
				
				return true;
			} catch (FileNotFoundException e) {
				LogUtil.errStack2Log4j(e);
			} catch (IOException e) {
				LogUtil.errStack2Log4j(e);
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param srcPath
	 * @param destPath
	 */
	public void copyPath(String srcPath, String destPath){
		File file = new File(srcPath);
		if(file.exists() && file.isDirectory()){
			String[] fileNames = file.list();
			
			if(this.createDir(destPath)){
				for(String fileName : fileNames){
					this.copyFile(srcPath + "\\" + fileName, destPath + "\\" + fileName);
				}
			}
		}
		
	}
	
	/**
	 * 判定路径下是否还有文件，如果有N多空目录，作为空目录处理
	 * @param path
	 * @return
	 */
	public static boolean isEmpty(String path){
		return isEmpty(new File(path));
	}
	
	/**
	 * 判定路径下是否还有文件，如果有N多空目录，作为空目录处理
	 * @param path
	 * @return
	 */
	public static boolean isEmpty(File path){
		File[] files = path.listFiles();
		
		if(null == files || files.length == 0) return true;
		
		for(File file : files){
			if(file.isFile()) return false;
			else if(file.isDirectory()){
				return isEmpty(file);
			}
		}
		
		return true;
	}
	
}

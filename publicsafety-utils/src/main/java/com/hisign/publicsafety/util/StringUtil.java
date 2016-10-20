package com.hisign.publicsafety.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ognl.IntHashMap;

import org.apache.commons.lang.UnhandledException;


/**
 * 字符串处理辅助类
 * @Date 2013-8-27 上午8:43:51
 * @author YuanXiaolong
 * 
 * TODO
 */
public class StringUtil {

	//RIGHT_ADD_RIGHT:两边添加， LEFT_ADD：左边添加，ADD_RIGHT：右边添加
	public static enum OperateFlag{RIGHT_ADD_RIGHT, LEFT_ADD, ADD_RIGHT, SPLIT_LEFT, SPLIT_RIGHT};
	
	/**
	 * 判断字符串不为空
	 * @param str
	 * @return boolean
	 */
	public static boolean isNotEmpty(String str){
		if(str == null || str.trim().equals(""))
			return false;
		return true;
	}
	
	/**
	 * 判断字符串不为空
	 * @param str
	 * @return boolean
	 */
	public static boolean isNotEmpty(Object strObj){
		if(strObj == null)
			return false;
		if(strObj.toString().trim().equals(""))
			return false;
		return true;
	}
	
	/**
	 * 判断字符串为空
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(String str){
		if(str == null || str.trim().equals(""))
			return true;
		return false;
	}
	
	/**
	 * 判断字符串值是否相等
	 * @param str1 str2
	 * @return boolean
	 */
	public static boolean isEqual(String str1, String str2){
		if(str1.equals(str2))
			return true;
		return false;
	} 
	
	/**
	 * 返回数组元素添加字符串separator之后的数组
	 * @param strArr
	 * @param separator 添加的字符串
	 * @param flag 
	 * @return String[]
	 */
	public static String[] addSeparator(String[] strArr, String separator, OperateFlag flag){
		for(int i=0; i<strArr.length; i++){
			switch(flag){
			case RIGHT_ADD_RIGHT: strArr[i]=separator+strArr[i]+separator;break;
			case LEFT_ADD: strArr[i]=separator+strArr[i];break;
			case ADD_RIGHT: strArr[i]=strArr[i]+separator;break;
			default:break;
			}
		}
		return strArr;
	}
	
	/**
	 * 
	 * 返回数组元素添加字符串separator之后,并且元素之间以connector连接的字符串
	 * @param strArr
	 * @param separator 添加的字符串
	 * @param flag 
	 * @param connector 连接符
	 * @return String
	 */
	public static String addSeparator(String[] strArr, String separator, OperateFlag flag, String connector){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<strArr.length; i++){
			switch(flag){
			case RIGHT_ADD_RIGHT: strArr[i]=separator+strArr[i]+separator;break;
			case LEFT_ADD: strArr[i]=separator+strArr[i];break;
			case ADD_RIGHT: strArr[i]=strArr[i]+separator;break;
			default:break;
			}
			
			sb.append(strArr[i]);
			if(i < strArr.length - 1)
				sb.append(connector);
		}
		return sb.toString();
	}
	
	/***
	 * 返回str拆分后元素添加字符串separator之后的字符串
	 * @param str
	 * @param splitor 拆分符
	 * @param separator 添加的字符串
	 * @param flag 
	 * @return String
	 */
	public static String addSeparator(String str, String splitor, String separator, OperateFlag flag){
		if(isNotEmpty(str))
			return addSeparator(str.split(splitor), separator, flag, "");
		
		return null;
	}
	
	/***
	 * 返回str拆分后元素添加字符串separator之后,并且元素之间以connector连接的字符串
	 * @param str
	 * @param splitor 拆分符
	 * @param separator 添加的字符串
	 * @param flag 
	 * @param connector 连接符
	 * @return String
	 */
	public static String addSeparator(String str, String splitor, String separator, OperateFlag flag, String connector){
		if(isNotEmpty(str))
			return addSeparator(str.split(splitor), separator, flag, connector);
		
		return null;
	}
	
	/**
	 * 字符串中splitor的个数
	 * @param str
	 * @param splitor
	 * @return
	 */
	public static int splitorCount(String str, String splitor){
		if(str.endsWith(splitor))
			return str.split(splitor).length;
		
		return str.split(splitor).length - 1;
	}
	
	/**
	 * 拆分字符串成两部分，第一部分为左边，第二部分为右边
	 * @param str
	 * @param splitor
	 * @param flag SPLIT_LEFT：拆分符位置在左边  SPLIT_RIGHT：拆分符位置在右边
	 * @param index 索引位置，根据flag判断是从左边开始或右边开始索引 , 从1开始
	 * @param leftWith 左边的部分是否需要包括分割符,结尾不需要分隔符
	 * @param rightWith 右边的部分是否需要包括分割符，结尾不需要分隔符
	 * @return
	 */
	public static String[] splitString(String str, String splitor, OperateFlag flag, int index, boolean leftWith, boolean rightWith){
		String[] arr = str.split(splitor);
		String[] rs = new String[2];
		
		//给出的索引值大于等于元素的数目
		if(index >= arr.length)
			return null;

		StringBuilder sbLeft = new StringBuilder();
		StringBuilder sbRight = new StringBuilder();
		
		switch(flag){
		case SPLIT_LEFT:
			for(int i = 0; i < index; i++){
				sbLeft.append(arr[i]);
				if(leftWith && i < index - 1)
					sbLeft.append(splitor);
			}
			
			for(int i = index; i < arr.length; i++){
				sbRight.append(arr[i]);
				if(rightWith && i < arr.length - 1)
					sbRight.append(splitor);
			}
			break;
		case SPLIT_RIGHT:
			for(int i = 0; i < arr.length - index; i++){
				sbLeft.append(arr[i]);
				if(leftWith && i < arr.length - index - 1)
					sbLeft.append(splitor);
			}
			
			for(int i = arr.length - index; i < arr.length; i++){
				sbRight.append(arr[i]);
				if(rightWith&& i < arr.length - 1)
					sbRight.append(splitor);
			}
			break;
		default:break;
		}
		
		rs[0] = sbLeft.toString();
		rs[1] = sbRight.toString();
		
		return rs;
	}
	
	/**
	 * 
	 * @param str
	 * @param index
	 * @return
	 */
	public static String toUpperCase(String str, int index){
		if(index >= str.length())
			return str;
		
		return str.substring(0, index)+(str.charAt(index) +"").toUpperCase() + str.substring(index + 1, str.length());
	}
	
	/**
	 * 
	 * @param str
	 * @param idx
	 * @return
	 */
	public static String toUpperCase(String str, int... idx){
		for(int i : idx)
			str = toUpperCase(str, i);
		
		return str;
	}
	
	/**
	 * 
	 * @param str
	 * @param index
	 * @return
	 */
	public static String toLowerCase(String str, int index){
		if(index >= str.length())
			return str;
		
		return str.substring(0, index)+(str.charAt(index) +"").toLowerCase() + str.substring(index + 1, str.length());
	}
	
	/**
	 * 
	 * @param str
	 * @param idx
	 * @return
	 */
	public static String toLowerCase(String str, int... idx){
		for(int i : idx)
			str = toLowerCase(str, i);
		
		return str;
	}
	
	/**
	 * 
	 * @param str
	 * @param startIdx
	 * @param len
	 * @return
	 */
	public static String removeSubStr(String str, int startIdx, int len){
		return replaceSubStr(str, "", startIdx, len);
	}
	
	/**
	 * 
	 * @param str
	 * @param num
	 * @param flag true, 移除前面num长度。 false,移除后面num长度。
	 * @return
	 */
	public static String removeFirstorLast(String str, int num, boolean flag){
		return replaceFirstorLast(str, "", num, flag);
	}
	
	/**
	 * 
	 * @param str
	 * @param startIdx
	 * @param len
	 * @return
	 */
	public static String replaceSubStr(String str, String repStr, int startIdx, int len){
		if(startIdx < 0 || startIdx + len > str.length())
			return null;
		return str.substring(0, startIdx) + repStr + str.substring(startIdx + len);
	}
	
	/**
	 * 
	 * @param str
	 * @param num
	 * @param flag true, 替换前面num长度。 false,替换后面num长度。
	 * @return
	 */
	public static String replaceFirstorLast(String str, String repStr, int num, boolean flag){
		if(flag){
			return replaceSubStr(str, repStr, 0, num);
		}else{
			return replaceSubStr(str, repStr, str.length() - num, num);
		}
	}
	
	/**
	 * 
	 * @param str 校验字符串
	 * @param regex 正则表达式
	 * @return
	 */
	public static boolean isRegexCondition(String str, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		
		return m.find();
	}
	
	/** 
	 * 将 Java 对象名称（每个单词的头字母大写）按照
	 * 数据库命名的习惯进行格式化
	 * 格式化后的数据为小写字母，并且使用下划线分割命名单词
	 * 
	 * 例如：employeeInfo 经过格式化之后变为 employee_info
	 * 
	 * @param name 	 Java 字段或者对象名称
	 * @param upper 数据库字段字符是否大写， true大写，false小写
	 */ 
	 public static String wordFormat4DB(String name, boolean upper){
		 if(name == null)
			 return null;
		 Pattern p = Pattern.compile("[A-Z]"); 
		 Matcher m = p.matcher(name); 
		 StringBuffer sb = new StringBuffer(); 
		
		 while(m.find()){ 
			 if(m.start() != 0) 
				 m.appendReplacement(sb, "_"+m.group()); 
		 }
		 if(upper)
			 return m.appendTail(sb).toString().toUpperCase();
		 else
			 return m.appendTail(sb).toString().toLowerCase();
	 }
	 
 	/** 
	 * 将数据库命名的习惯employee_info按照
	 * Java命名的习惯进行格式化（每个单词的头字母大写）
	 * 
	 * 例如： employee_info 经过格式化之后变为employeeInfo 
	 * 
	 * @param name 	 数据库的命名习惯的字段
	 */ 
	 public static String wordFormat4Prop(String name){
		 if(name == null)
			 return null;
		 name = name.toLowerCase();
		 StringBuffer sb = new StringBuffer(); 
		 
		 int index = name.indexOf('_');
		 while(index >= 0 && index < name.length()){
			 sb.append(name.substring(0, index));
			 if(index + 2 <= name.length())
				 sb.append(name.substring(index+1, index+2).toUpperCase());
			 else
				 ;
			 name = name.substring(index+2, name.length());
			 index = name.indexOf('_');
		 }
		 sb.append(name);
		 return sb.toString(); 
	 }
	 
	 /** 
		 * 将数据库命名的习惯employee_info按照
		 * Java命名的习惯进行格式化（每个单词的头字母大写）
		 * 
		 * 例如： employee_info 经过格式化之后变为employeeInfo 
		 * 
		 * @param name 	 数据库的命名习惯的字段
		 */ 
		 public static String wordFormat4Class(String name){
			 if(name == null)
				 return null;
			 name = name.toLowerCase();
			 StringBuffer sb = new StringBuffer(); 
			 
			 sb.append(name.substring(0, 1).toUpperCase());
			 name = name.substring(1, name.length());
			 int index = name.indexOf('_');
			 while(index >= 0 && index < name.length()){
				 sb.append(name.substring(0, index));
				 if(index + 2 <= name.length())
					 sb.append(name.substring(index+1, index+2).toUpperCase());
				 else
					 ;
				 name = name.substring(index+2, name.length());
				 index = name.indexOf('_');
			 }
			 sb.append(name);
			 return sb.toString(); 
		 }
	 
	 /**
	  * 
	  * @param str
	  * @param index
	  * @param subStr
	  * @return
	  */
	 public static String insertSubStr(String str, int index, String subStr){
		 
		 return str.substring(0, index) + subStr + str.substring(index);
	 }

	public static String htmlEncode(String source) {
		if (source == null) {
			return "";
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			switch (c) {
			case '<':
				buffer.append("&lt;");
				break;
			case '>':
				buffer.append("&gt;");
				break;
			case '&':
				buffer.append("&amp;");
				break;
			case '"':
				buffer.append("&quot;");
				break;
			case 10:
			case 13:
				break;
			default:
				buffer.append(c);
			}
		}
		html = buffer.toString();
		return html;
	}
}

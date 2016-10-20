/**
 * 
 */
package com.hisign.publicsafety.util;

import java.util.HashMap;
import java.util.Map;

import com.hisign.publicsafety.common.Constant;

/**
 * @author chailiangzhi
 * @date 2015-9-15
 * 转码工具类
 */
public class EncodeUtil {

	/**
	 * 串并依据 字符串到二进制的映射关系
	 */
	private static final Map<String, Integer> seriesReasonStrToIntMap = initSeriesReasonStrToIntMap();

	/**
	 * 串并依据 二进制到字符串的映射关系
	 */
	private static final Map<Integer, String> seriesReasonIntToStrMap = initSeriesReasonIntToStrMap();

	/**
	 * 初始化串并依据字符串到二进制的映射关系
	 * @return
	 */
	private static Map<String, Integer> initSeriesReasonStrToIntMap() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(Constant.SERIES_REASON_ALIPAY, Integer.parseInt("1", 2));
		map.put(Constant.SERIES_REASON_TELEPHONE, Integer.parseInt("10", 2));
		map.put(Constant.SERIES_REASON_CERT_NO, Integer.parseInt("100", 2));
		map.put(Constant.SERIES_REASON_BANKCARD, Integer.parseInt("1000", 2));
		return map;
	}

	/**
	 * 初始化串并依据二进制到字符串的映射关系
	 * @return
	 */
	private static Map<Integer, String> initSeriesReasonIntToStrMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (Map.Entry<String, Integer> entry : seriesReasonStrToIntMap.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		return map;
	}

	/**
	 * 逗号','分隔的串并依据字符串转整型
	 * @param seriesReason
	 * @return 取值范围0-15
	 */
	public static int encodeSeriesReason(String seriesReason) {
		String[] seriesReasons = seriesReason.split(",");
		int seriesReasonInt = 0;
		for (String str : seriesReasons) {
			seriesReasonInt += seriesReasonStrToIntMap.get(str);
		}
		return seriesReasonInt;
	}

	/**
	 * 整型的串并依据转字符串
	 * @param seriesReasonInt 取值范围0-15
	 * @return 逗号','分隔的串并依据字符串
	 */
	public static String decodeSeriesReason(int seriesReasonInt) {
		if (seriesReasonInt == 0) {
			return "";
		}
		StringBuilder seriesReasonSb = new StringBuilder();
		String seriesReasonBinary = Integer.toBinaryString(seriesReasonInt);
		int len = seriesReasonBinary.length();
		for (int i = len; i > 0; i--) {
			char bit = seriesReasonBinary.charAt(i);
			if (bit == '1') {
				Double bitDecimal = Math.pow(2, len - 1 - i);
				seriesReasonSb.append(seriesReasonIntToStrMap.get(bitDecimal.intValue())).append(',');
			}
		}
		seriesReasonSb.deleteCharAt(seriesReasonSb.length() - 1);
		return seriesReasonSb.toString();
	}

	/**
	 * Convert byte[] to hex string.
	 * 这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
	 * @param src byte[] data
	 * @return hex string
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * Convert hex string to byte[]
	 * @param hexString the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * @param c char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

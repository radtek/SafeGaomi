package com.hisign.publicsafety.util.dozer;

import java.util.HashMap;
import java.util.Map;

import org.dozer.CustomConverter;

import com.alibaba.dio2o.domain.request.InvestType;

/**
 * @author chailiangzhi
 * @date 2015-9-10
 * dozer转换枚举和字符串转换
 */
public class EnumInvestTypeConverter implements CustomConverter {
	//	1205	7	XCXX_TBYHM	淘宝用户名
	//	1206	7	XCXX_EMAIL	邮箱
	//	1207	7	XCXX_RUL	店面链接
	//	1208	7	XCXX_ZFB	支付宝账户名
	//	1209	7	XCXX_ZFBID	支付宝ID
	//	1211	7	XCXX_SFZ	身份证号
	//	1212	7	XCXX_MOBILE	手机

	private static final Map<String, InvestType> investTypeMapStrToEnum = initInvestTypeMapStrToEnum();
	private static final Map<InvestType, String> investTypeMapEnumToStr = initInvestTypeMapEnumToStr();

	private static Map<String, InvestType> initInvestTypeMapStrToEnum() {
		Map<String, InvestType> map = new HashMap<String, InvestType>();
		map.put("XCXX_TBYHM", InvestType.user_name_taobao);
		map.put("XCXX_EMAIL", InvestType.email_taobao);
		map.put("XCXX_RUL", InvestType.shop_url);
		map.put("XCXX_ZFB", InvestType.user_name_alipay);
		map.put("XCXX_ZFBID", InvestType.user_id_alipay);
		map.put("XCXX_SFZ", InvestType.cert_no);
		map.put("XCXX_MOBILE", InvestType.mobile_no);
		return map;
	}

	private static Map<InvestType, String> initInvestTypeMapEnumToStr() {
		Map<InvestType, String> map = new HashMap<InvestType, String>();
		map.put(InvestType.user_name_taobao, "XCXX_TBYHM");
		map.put(InvestType.email_taobao, "XCXX_EMAIL");
		map.put(InvestType.shop_url, "XCXX_RUL");
		map.put(InvestType.user_name_alipay, "XCXX_ZFB");
		map.put(InvestType.user_id_alipay, "XCXX_ZFBID");
		map.put(InvestType.cert_no, "XCXX_SFZ");
		map.put(InvestType.mobile_no, "XCXX_MOBILE");
		return map;
	}

	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		Object returnVale = null;
		if (sourceFieldValue != null) {
			if (sourceFieldValue instanceof String) {
				returnVale = investTypeMapStrToEnum.get(sourceFieldValue);
			} else if (sourceFieldValue instanceof Enum) {
				returnVale = investTypeMapEnumToStr.get(sourceFieldValue);
			}
		}
		return returnVale;
	}
}
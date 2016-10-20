/**
 * 
 */
package com.hisign.publicsafety.service.impl.cache;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.hisign.publicsafety.common.api.ObjectToString;

/**
 * @author chailiangzhi
 * @date 2015-9-18
 * 通过JSON转换对象和字符串
 */
//@Service("objectToString")
public class ObjectToStringImpl implements ObjectToString {

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.cache.ObjectToString#serial(java.lang.Object)
	 */
	@Override
	public String serial(Object obj) throws Exception {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.cache.ObjectToString#unserial(java.lang.String)
	 */
	@Override
	public <T> T unserial(String str, Class<T> clazz) throws Exception {
		Gson gson = new Gson();
		T t = gson.fromJson(str, clazz);
		return t;
	}

}

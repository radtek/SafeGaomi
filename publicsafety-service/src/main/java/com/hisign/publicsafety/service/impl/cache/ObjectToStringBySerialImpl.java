/**
 * 
 */
package com.hisign.publicsafety.service.impl.cache;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import com.hisign.publicsafety.common.api.ObjectToString;

/**
 * @author chailiangzhi
 * @date 2015-9-18
 * 通过序列化转换对象和字符串
 */
@Service("objectToStringBySerial")
public class ObjectToStringBySerialImpl implements ObjectToString {

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.cache.ObjectToString#serial(java.lang.Object)
	 */
	@Override
	public String serial(Object obj) throws Exception {
		//序列化对象  
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(bOut);
		objOut.writeObject(obj);
		//base64 encode  
		String str = Base64.encodeBase64String(bOut.toByteArray());
		return str;
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.cache.ObjectToString#unserial(java.lang.String)
	 */
	@Override
	public <T> T unserial(String str, Class<T> clazz) throws Exception {
		//base64 decode  
		byte[] objBytes = Base64.decodeBase64(str);
		//反序列化  
		ByteArrayInputStream bIn = new ByteArrayInputStream(objBytes);
		ObjectInputStream objIn = new ObjectInputStream(bIn);
		Object obj = objIn.readObject();
		return (T) obj;
	}

}

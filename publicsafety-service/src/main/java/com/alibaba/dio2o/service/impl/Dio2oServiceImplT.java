/**
 * 
 */
package com.alibaba.dio2o.service.impl;

import java.io.InputStream;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.dio2o.domain.request.RequestEntity;
import com.alibaba.dio2o.domain.response.LoginLog;
import com.alibaba.dio2o.domain.response.RegistInfo;
import com.alibaba.dio2o.domain.response.ResponseData;
import com.alibaba.dio2o.domain.response.ResponseEntity;
import com.alibaba.dio2o.result.Dio2oResult;
import com.alibaba.dio2o.service.Dio2oService;
import com.google.gson.Gson;
import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.util.FileUtil;

/**
 * @author chailiangzhi
 * @date 2015-9-2 
 * 模拟测试的实现，可删除
 */
@Service("dio2oService")
public class Dio2oServiceImplT implements Dio2oService {

	/* (non-Javadoc)
	 * @see com.alibaba.dio2o.service.Dio2oService#queryInformation(com.alibaba.dio2o.domain.request.RequestEntity)
	 */
	@Override
	public Dio2oResult<ResponseEntity> queryInformation(RequestEntity arg0) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("resEntity.json");
		String msg = FileUtil.readFile(is, Constant.HTTP_CHARSET);
		Gson gson = new Gson();
		Dio2oResult<ResponseEntity> res = new Dio2oResult<ResponseEntity>();
		ResponseEntity resEntity = gson.fromJson(msg, ResponseEntity.class);
		//		resEntity.getData().setEntrustNo(arg0.getData().getEntrustNo());
		ResponseData data = resEntity.getData();
		LoginLog loginLog1 = data.getLoginLog().get(0);
		loginLog1.setActionDt(new Date());

		RegistInfo registInfo1 = data.getRegistInfo().get(0);
		registInfo1.setRegisterTime(new Date(System.currentTimeMillis() - (3600 * 1000)));
		registInfo1.setLastLogin(new Date());
		res.setData(resEntity);
		return res;
	}

}

/**
 * 
 */
package com.hisign.series;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hisign.BaseUnit4Test;
import com.hisign.publicsafety.entity.EntrustCaseInfo;
import com.hisign.publicsafety.mapper.entrust.EntrustCaseMapper;
import com.hisign.publicsafety.service.EntrustCaseService;
import com.hisign.publicsafety.util.LogUtil;

/**
 * @author chailiangzhi
 * @date 2015-9-11
 * 根据案件好获取案件基本信息
 */
public class GetCaseByIdTest extends BaseUnit4Test {
	private static final Logger logger = LoggerFactory.getLogger(GetCaseByIdTest.class);
	@Autowired
	private EntrustCaseService entrustCaseService;
	@Autowired
	private EntrustCaseMapper entrustCaseMapper;

	@Test
	public void testCall() {
		try {
//			EntrustCaseInfo entrustCaseInfo=entrustCaseService.getById("3751");
			EntrustCaseInfo entrustCaseInfo=entrustCaseMapper.getByEntrustNo("WT110109400000201508002763");
			Gson gson = new Gson();
			String json = gson.toJson(entrustCaseInfo);
			logger.info(json);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}

	}
}

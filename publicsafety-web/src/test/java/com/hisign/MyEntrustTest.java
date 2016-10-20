/**
 * 
 */
package com.hisign;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.service.EntrustApplyService;
import com.hisign.publicsafety.service.impl.entrust.EntrustApplyServiceImpl;
import com.hisign.publicsafety.util.LogUtil;

/**
 * @author chailiangzhi
 * @date 2015-8-21
 * 
 */

public class MyEntrustTest extends BaseUnit4Test {
	private static final Logger logger = LoggerFactory.getLogger(MyEntrustTest.class);
	
	@Inject
	private EntrustApplyService entrustApplyService;

	@Test
	public void testQry() {
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			//		paraMap.put("entrustNo", "WT121212121212201508000083");
			paraMap.put("unit", "南宁市公安局");
			//		paraMap.put("caseType", "11,22");
			//		paraMap.put("processState", "33,44");
			DataResponse<EntrustApplyData> entrustList = entrustApplyService.query(getPageView("1", "10"), paraMap);
			Gson gson = new Gson();
			String json = gson.toJson(entrustList);
			logger.info(json);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}
	}
}

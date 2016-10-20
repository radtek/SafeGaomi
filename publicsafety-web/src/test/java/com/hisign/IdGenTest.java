package com.hisign;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.publicsafety.entity.EntrustBaseInfo;
import com.hisign.publicsafety.entity.EntrustCaseInfo;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.service.EntrustApplyService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.series.SeriCaseNoGenTest;

/**
 * 编号生成测试 
 */
public class IdGenTest extends BaseUnit4Test {
	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(SeriCaseNoGenTest.class);
	/**
	 * 
	 */
	@Autowired
	private IdService idService;

	/**
	 * 
	 */
	@Test
	public void testCall() {
		try {
			String entrustNo = idService.getEntrustNo("unitNounitNo");
			logger.info(entrustNo);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}

	}
}

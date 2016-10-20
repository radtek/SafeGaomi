/**
 * 
 */
package com.hisign.series;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.BaseUnit4Test;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.util.LogUtil;

/**
 * @author chailiangzhi
 * @date 2015-9-11
 * 串并案编号生成功能测试用例
 */
public class SeriCaseNoGenTest extends BaseUnit4Test {
	private static final Logger logger = LoggerFactory.getLogger(SeriCaseNoGenTest.class);
	@Autowired
	private IdService idService;

	@Test
	public void testCall() {
		try {
			String seriesCaseNo = idService.getSeriesCaseNo("unitNounitNo");
			logger.info(seriesCaseNo);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}

	}
}

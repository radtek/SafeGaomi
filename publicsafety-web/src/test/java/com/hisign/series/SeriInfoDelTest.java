/**
 * 
 */
package com.hisign.series;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.BaseUnit4Test;
import com.hisign.publicsafety.service.series.SeriesCaseInfoService;
import com.hisign.publicsafety.util.LogUtil;

/**
 * @author chailiangzhi
 * @date 2015-9-11
 * 我的串并案列表查询功能测试用例
 */
public class SeriInfoDelTest extends BaseUnit4Test {
	private static final Logger logger = LoggerFactory.getLogger(SeriInfoDelTest.class);
	@Autowired
	private SeriesCaseInfoService seriesCaseInfoService;

	@Test
	public void testCall() {
		try {
			seriesCaseInfoService.delete("11311");
			logger.info("---");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}

	}
}

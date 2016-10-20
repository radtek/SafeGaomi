/**
 * 
 */
package com.hisign.series;

import java.util.ArrayList;
import java.util.List;

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
public class SeriInfoDelBatchTest extends BaseUnit4Test {
	private static final Logger logger = LoggerFactory.getLogger(SeriInfoDelBatchTest.class);
	@Autowired
	private SeriesCaseInfoService seriesCaseInfoService;

	@Test
	public void testCall() {
		try {
			List<String> idList = new ArrayList<String>();
			idList.add("11311");
			seriesCaseInfoService.deleteBatch(idList);
			logger.info("---");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}

	}
}

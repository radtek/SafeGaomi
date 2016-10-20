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
import com.hisign.publicsafety.entity.series.SeriesData;
import com.hisign.publicsafety.service.impl.entrust.EntrustApplyServiceImpl;
import com.hisign.publicsafety.service.series.SeriesService;
import com.hisign.publicsafety.util.LogUtil;

/**
 * @author chailiangzhi
 * @date 2015-9-11
 * 串并案详情查看功能测试用例
 */
public class SeriDetailGetTest extends BaseUnit4Test {
	private static final Logger logger = LoggerFactory.getLogger(SeriDetailGetTest.class);
	@Autowired
	private SeriesService seriesService;

	@Test
	public void testCall() {
		try {
			String seriesInfoId = "1";
			SeriesData seriesData = seriesService.getSeriesDetail(seriesInfoId);
			Gson gson = new Gson();
			String json = gson.toJson(seriesData);
			logger.info(json);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}

	}
}

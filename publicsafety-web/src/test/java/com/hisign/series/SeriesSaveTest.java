/**
 * 
 */
package com.hisign.series;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.BaseUnit4Test;
import com.hisign.publicsafety.entity.SeriesCaseDetail;
import com.hisign.publicsafety.entity.SeriesCaseInfo;
import com.hisign.publicsafety.entity.series.SeriesData;
import com.hisign.publicsafety.service.impl.entrust.EntrustApplyServiceImpl;
import com.hisign.publicsafety.service.series.SeriesService;
import com.hisign.publicsafety.util.LogUtil;

/**
 * @author chailiangzhi
 * @date 2015-9-11
 * 串并案保存功能测试用例
 */
public class SeriesSaveTest extends BaseUnit4Test {
	private static final Logger logger = LoggerFactory.getLogger(SeriesSaveTest.class);
	@Autowired
	private SeriesService seriesService;

	@Test
	public void testCall() {
		try {
			SeriesData seriesData = new SeriesData();
			SeriesCaseInfo seriesCaseInfo = new SeriesCaseInfo();
			seriesCaseInfo.setId("7");
			seriesCaseInfo.setSeriesCaseNo("110");
			seriesCaseInfo.setSeriesReason("mobile,alipayno");
			seriesData.setSeriesCaseInfo(seriesCaseInfo);

			List<SeriesCaseDetail> seriesCaseDetails = new ArrayList<SeriesCaseDetail>();
			SeriesCaseDetail seriesCaseDetail = new SeriesCaseDetail();
			seriesCaseDetail.setId("13");
			seriesCaseDetail.setCaseId("10170");
			seriesCaseDetail.setCreateDate(new Date());
			seriesCaseDetails.add(seriesCaseDetail);

			seriesCaseDetail = new SeriesCaseDetail();
			seriesCaseDetail.setId("14");
			seriesCaseDetail.setCaseId("10296");
			seriesCaseDetail.setCreateDate(new Date());
			seriesCaseDetails.add(seriesCaseDetail);

			seriesData.setSeriesCaseDetails(seriesCaseDetails);
			boolean isSucc = seriesService.seriesSave(seriesData);
			logger.info(Boolean.toString(isSucc));
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}

	}
}

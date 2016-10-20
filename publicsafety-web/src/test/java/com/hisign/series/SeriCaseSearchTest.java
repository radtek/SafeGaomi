/**
 * 
 */
package com.hisign.series;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hisign.BaseUnit4Test;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.impl.entrust.EntrustApplyServiceImpl;
import com.hisign.publicsafety.service.series.SeriesService;
import com.hisign.publicsafety.util.LogUtil;

/**
 * @author chailiangzhi
 * @date 2015-9-11
 * 串并案串并搜索结果列表功能测试用例
 */
public class SeriCaseSearchTest extends BaseUnit4Test {
	private static final Logger logger = LoggerFactory.getLogger(SeriCaseSearchTest.class);
	@Autowired
	private SeriesService seriesService;

	@Test
	public void testCall() {
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("createUser", "1");
			paraMap.put("entrustCaseId", "3751");
			DataResponse dataResponse = seriesService.seriesSearch(getPageView("1", "10"), paraMap);
			Gson gson = new Gson();
			String json = gson.toJson(dataResponse);
			logger.info(json);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}

	}
}

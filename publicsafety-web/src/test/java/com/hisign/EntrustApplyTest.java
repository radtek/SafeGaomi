package com.hisign;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.publicsafety.entity.EntrustBaseInfo;
import com.hisign.publicsafety.entity.EntrustCaseInfo;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.service.EntrustApplyService;
import com.hisign.publicsafety.util.LogUtil;

/**
 * Unit test 
 */
public class EntrustApplyTest extends BaseUnit4Test {
	@Autowired
	EntrustApplyService serivce;

	/**
	 * 
	 */
	@Test
	public void testSave() {
		EntrustApplyData entrustApplyData = new EntrustApplyData();
		EntrustBaseInfo entrustInfo = new EntrustBaseInfo();
		entrustInfo.setId("2");
		entrustInfo.setEntrustNo("3");
		entrustInfo.setUpdateDate(new Date());
		entrustInfo.setIsShareCaseinfo("1");
		entrustInfo.setIsShareQueryresult("1");
		entrustApplyData.setEntrustInfo(entrustInfo);
		EntrustCaseInfo caseInfo = new EntrustCaseInfo();
		caseInfo.setId("2");
		entrustApplyData.setCaseInfo(caseInfo);
		try {
			serivce.save(entrustApplyData);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}
	}
}

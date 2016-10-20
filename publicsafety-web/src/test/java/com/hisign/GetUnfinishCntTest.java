/**
 * 
 */
package com.hisign;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.publicsafety.service.EntrustApplyService;
import com.hisign.publicsafety.util.LogUtil;

/**
 * @author chailiangzhi
 * @date 2015-8-21
 * 
 */

public class GetUnfinishCntTest extends BaseUnit4Test {

	@Autowired
	private EntrustApplyService entrustApplyService;

	@Test
	public void testCall() {
		try {
			int count = entrustApplyService.getUnfinishCount(System.currentTimeMillis(), 0);

		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}
	}
}

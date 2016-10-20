/**
 * 
 */
package com.hisign;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.publicsafety.service.AliPortService;
import com.hisign.publicsafety.util.LogUtil;

/**
 * @author chailiangzhi
 * @date 2015-8-21
 * 
 */

public class CallAliApiTest extends BaseUnit4Test {

	@Autowired
	private AliPortService aliPortService;

	@Test
	public void testCall() {
		try {
			aliPortService.callAli("WT110109400000201508002763");

		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}

	}
}

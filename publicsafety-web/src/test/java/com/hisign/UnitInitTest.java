/**
 * 
 */
package com.hisign;

import javax.inject.Inject;

import org.junit.Test;

import com.hisign.publicsafety.service.DicService;
import com.hisign.publicsafety.util.FileUtil;
import com.hisign.publicsafety.util.LogUtil;

/**
 * @author chailiangzhi
 * @date 2015-8-21
 * 
 */

public class UnitInitTest extends BaseUnit4Test {// extends TestCase

	@Inject
	private DicService dicService;

	@Test
	public void testInit() {
		try {
			long begin = System.currentTimeMillis();
			String json = dicService.getUnitJson();
			long end = System.currentTimeMillis();
			long use = end - begin;
			FileUtil.writeFileSimple("json1", json, false);

			begin = System.currentTimeMillis();
			json = dicService.getUnitJson();
			end = System.currentTimeMillis();
			use = end - begin;

			FileUtil.writeFileSimple("json2", json, false);

		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}
	}
}

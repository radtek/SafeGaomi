/**
 * 
 */
package com.hisign.series;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author chailiangzhi
 * @date 2015-9-14
 * 串并案测试组件,含五个功能用例
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	SeriCaseQryTest.class, 
	SeriCaseSearchTest.class, 
	SeriesSaveTest.class,
	SeriInfoQryTest.class, 
	SeriDetailGetTest.class })
public class SeriTestSuite1 {

}

package com.hisign.publicsafety.common;

/**
 * 常量定义
 * @author chailiangzhi
 * @date 2015-8-13
 * 
 */
public class Constant {

	/**
	 * HTTP接口字符串编码
	 */
	public static final String HTTP_CHARSET = "utf-8";

	/**
	 * 文件下载时文件名的编码方式
	 */
	public static final String DOWNLOAD_FILENAME_CHARSET = "ISO8859-1";

	/**
	 * 委托已反馈(待评价)状态
	 */
	public static final String PROCESS_STATE_FEEDBACK = "feedback";

	/**
	 * 委托已完成状态
	 */
	public static final String PROCESS_STATE_FINISH = "finish";

	/**
	 * 委托审核通过(处理中)状态
	 */
	public static final String PROCESS_STATE_CHECKPASS = "processing";

	/**
	 * 委托审核不通过状态
	 */
	public static final String PROCESS_STATE_CHECKNOTPASS = "notpass";

	/**
	 * 委托暂存状态
	 */
	public static final String PROCESS_STATE_SAVE = "save";

	/**
	 * 委托提交(待审核)状态
	 */
	public static final String PROCESS_STATE_SUBMIT = "submit";
	/**
	 * 委托有返回数据(待确认)状态
	 */
	public static final String PROCESS_STATE_RESULT = "result";

	/**
	 * 默认日期
	 */
	public static final String DATESTR_DEFAULT = "2000-01-01 00:00";

	/**
	 * 提交委托的接口的API_NAME
	 */
	public static final String API_NAME_SUBMIT = "o2o.police.submit";

	/**
	 * 阿里系统编码
	 */
	public static final String SYS_CODE_ALIB = "ALIB";

	/**
	 * 分页起始行
	 */
	public static final String SQL_PAGE_START_KEY = "start";
	/**
	 * 每页数据条数
	 */
	public static final String SQL_PAGE_SIZE_KEY = "size";

	/**
	 * 阿里安全验证码appkey
	 */
	public final static String APP_KEY = "CF_APP_DIO2O";

	/**
	 * 前台和后台传递CSRF Token的键值
	 */
	public final static String CSRF_TOKEN_KEY = "CSRFToken";

	/**
	 * 案件已串并标志
	 */
	public final static String CASE_IS_SERIESED = "1";

	/**
	 * 串并依据-支付宝账号
	 */
	public final static String SERIES_REASON_ALIPAY = "alipayno";

	/**
	 * 串并依据-电话
	 */
	public final static String SERIES_REASON_TELEPHONE = "mobile";

	/**
	 * 串并依据-身份证号
	 */
	public final static String SERIES_REASON_CERT_NO = "cardno";

	/**
	 * 串并依据-银行卡号
	 */
	public final static String SERIES_REASON_BANKCARD = "bankcard";

	/**
	 * HTTP请求头中AJAX请求的标记的键
	 */
	public final static String AJAX_REQ_TAG_KEY = "X-Requested-With";

	/**
	 * HTTP请求头中AJAX请求的标记的值
	 */
	public final static String AJAX_REQ_TAG_VALUE = "XMLHttpRequest";

	/**
	 * COOKIE最长有效时间（秒）
	 */
	public final static int COOKIE_MAX_AGE = -1;
	/**
	 * COOKIE路径
	 */
	public final static String COOKIE_PATH = "/";
	/**
	 * 放在COOKIE里的自定义会话ID的key
	 */
	public final static String J_SESSION_ID_NAME = "msessionid";
	/**
	 * 放在COOKIE里的用户信息的key
	 */
	public final static String USER_INFO_NAME = "ou";

	/**
	 * 放在cache里的Spring Security上下文的键值
	 */
	public final static String SPRING_SECURITY_CONTEXT_CACHE = "spring_security_context_cache";
	/**
	 * 放在cache里的字符串形式的授权列表的键值
	 */
	public final static String AUTHORITY_STRS_KEY = "authorityStrs";
	/**
	 * 放在cache里的当前登录账号实体的键值
	 */
	public final static String LOGIN_USER_ENTITY = "userSession";
	/**
	 * 放在cache里的当前登录账号ID的键值
	 */
	public final static String LOGIN_USER_ID = "userSessionId";
	/**
	 * 放在cache里的当前登录账号角色编码的键值
	 */
	public final static String LOGIN_USER_ROLE_KEY = "userRoleKey";
	/**
	 * 放在cache里的当前登录账号单位的键值
	 */
	public final static String LOGIN_USER_UNIT = "userUnitCode";

	/**
	 * 公安角色key
	 * roleKey唯一，暂时写死公安用户roleKey为police
	 */
	public final static String ROLE_KEY_POLICE = "police";

	/**
	 * 系统登录链接
	 */
	public final static String LOGIN_URL = "/login.html";
	/**
	 * 系统提交登录信息的链接
	 */
	public final static String SUBMIT_LOGIN_URL = "/submitlogin.html";
	/**
	 * 系统需检查cookie是否启用的跳转页面链接
	 */
	public final static String URI_COOKIE_CHECK = LOGIN_URL + "?sys_check_page=cookie_check";
	/**
	 * 系统cookie超时的跳转页面链接
	 */
	public final static String URI_COOKIE_TIMEOUT = LOGIN_URL + "?sys_check_page=cookie_timeout";
	/**
	 * 系统遭受CSRF黑客攻击的跳转页面链接
	 */
//	public final static String URI_HACK_CSRF = LOGIN_URL + "?sys_check_page=hack_csrf";
	/**
	 * AJAX请求系统需检查cookie是否启用的返回状态
	 */
	public final static int STATUS_COOKIE_CHECK = 601;
	/**
	 * AJAX请求系统cookie超时的返回状态
	 */
	public final static int STATUS_COOKIE_TIMEOUT = 602;
	/**
	 * AJAX请求系统遭受CSRF黑客攻击的返回状态
	 */
	public final static int STATUS_HACK_CSRF = 603;
	
	/**
	 * 串并案选中后样式名称
	 */
	public final static String  SERIES_REASON_SUCESS_CLASSNAME = "btn btn-success active";
	
	/**
	 * 串并案没有选中后样式名称
	 */
	public final static String  SERIES_REASON_DEFAULT_CLASSNAME = "btn btn-default";
	
	/**
	 * 手持设备加密文件存储路径
	 */
	public final static String AUTHORIZATION_FILE = "authorizationFile";
}

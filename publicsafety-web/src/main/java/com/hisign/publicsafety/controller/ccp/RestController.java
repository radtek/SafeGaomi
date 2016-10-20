package com.hisign.publicsafety.controller.ccp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.SystemCaches;
import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.ccp.ClueProviderInfo;
import com.hisign.publicsafety.entity.ccp.PhoneLicense;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.service.ccp.ClueProviderInfoService;
import com.hisign.publicsafety.service.ccp.PhoneLicenseService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.util.Md5Tool;
import com.hisign.publicsafety.util.PropertiesUtils;

/**
 * rest服务controller（移动端调用）
 * 
 * @author hotdog 2015-11-26
 */
@Controller
@RequestMapping("/")
public class RestController extends BaseController {

	@Inject
	private IdService idService;

	@Inject
	private ClueProviderInfoService clueProviderInfoService;

	@Inject
	private PhoneLicenseService phoneLicenseService;

	/**
	 * 移动端用户实名认证服务
	 * 
	 * @param clurProvider
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/authentication")
	public Map register(ClueProviderInfo obj) {
		Map map = new HashMap();
		try {
			// 校验参数是否完整 姓名，身份证号，电话d号码，身份证照片（正反面），id
			if (StringUtils.isNotEmpty(obj.getId())
					&& StringUtils.isNotEmpty(obj.getTrueName())
					&& StringUtils.isNotEmpty(obj.getPhoneNum())
					&& StringUtils.isNotEmpty(obj.getIdCardNum())
					&& obj.getIdCardPhotoFront() != null
					&& obj.getIdCardPhotoBack() != null) {

				// 手机号是否已注册校验
				ClueProviderInfo clueProviderInfo = clueProviderInfoService
						.findByPhoneNum(obj.getPhoneNum());
				if (clueProviderInfo != null) {

					// 图片文件流转base64
					// 正面
					CommonsMultipartFile cf = (CommonsMultipartFile) obj
							.getIdCardPhotoFront();
					DiskFileItem fi = (DiskFileItem) cf.getFileItem();
					File frontFile = fi.getStoreLocation();
					// 限制身份证照片大小不超过3M
					if (frontFile.length() > 3145728) {
						map.put("reply", "0");
						map.put("content", "正面照片大小超出限制");
						return map;
					}
					FileInputStream inputFrontFile = new FileInputStream(
							frontFile);
					byte[] frontBuffer = new byte[(int) frontFile.length()];
					inputFrontFile.read(frontBuffer);
					inputFrontFile.close();
					String frontPhotoStr = org.apache.commons.codec.binary.Base64
							.encodeBase64String(frontBuffer);
					obj.setIdCardPhotoFrontStr(frontPhotoStr);

					// 反面
					CommonsMultipartFile cf1 = (CommonsMultipartFile) obj
							.getIdCardPhotoBack();
					DiskFileItem fi1 = (DiskFileItem) cf1.getFileItem();
					File backFile = fi1.getStoreLocation();
					// 限制身份证照片大小不超过3M
					if (backFile.length() > 3145728) {
						map.put("reply", "0");
						map.put("content", "反面照片大小超出限制");
						return map;
					}
					FileInputStream inputBackFile = new FileInputStream(
							backFile);
					byte[] backBuffer = new byte[(int) backFile.length()];
					inputBackFile.read(backBuffer);
					inputBackFile.close();
					String backPhotoStr = org.apache.commons.codec.binary.Base64
							.encodeBase64String(backBuffer);
					obj.setIdCardPhotoBackStr(backPhotoStr);

					obj.setUpdateDate(new Date());

					// 实名认证
					clueProviderInfoService.update(obj);

					map.put("reply", "1");
				} else {
					map.put("reply", "0");
					map.put("content", "此手机号未注册");
				}
			} else {
				map.put("reply", "0");
				map.put("content", "数据不完整");
			}
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("reply", "0");
			map.put("content", "注册失败");
		}
		return map;
	}

	/**
	 * 移动端用户调用发送短信服务(手机号)
	 * 
	 * @param idCardNum
	 * @return 1成功 0系统异常 -1发送短信失败
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/sendVerificationCode")
	public Map sendVerificationCode(String phoneNum,String type,String platNum,String offerName,String offerPho) {
		Map map = new HashMap();
		if (StringUtils.isEmpty(phoneNum)) {
			map.put("reply", "0");
			map.put("content", "手机号为空");
			return map;
		}
		String content="";//短信发送的内容
		try {
			// 生成六位数字校验码
			String verificationCode = Common.generateCheckPass();
			// 调用短信接口，1为短信验证码 2为车牌预警发送短息
			if("1".equals(type)){
				content = "【平安高密】您的动态验证码为" + verificationCode + "，五分钟内输入有效。";
			}else if("2".equals(type)){
				content = "【平安高密】"+platNum+"车辆系" + offerName + "设定布控报警车辆，请及时查控。布控民警 "+offerName+",电话 "+offerPho+"。";
			}else{
				map.put("reply", "0");
				map.put("content", "短信调用类型不对");
				return map;
			}
			String returnstatus = readContentFromPost(phoneNum,
					content);

			if ("Success".equals(returnstatus)) {
				verificationCode += "_" + System.currentTimeMillis() + "";
				map.put("reply", "1");
				map.put("verificationCode", verificationCode);
			} else if ("Faild".equals(returnstatus)) {
				map.put("reply", "-1");
			}

		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("reply", "0");
			map.put("content", "系统异常，注册失败");
		}
		return map;
	}

	/**
	 * 调用短信接口发送验证码
	 * 
	 * @param phoneNum
	 * @param verificationCode
	 * @throws Exception
	 */
	public static String readContentFromPost(String phoneNum,
			String content) throws Exception {
		String postUrl = PropertiesUtils.findPropertiesKey("smsUrl");
		URL url = new URL(postUrl);
		// 打开连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 设置是否向connection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true
		connection.setDoOutput(true);
		// Read from the connection. Default is true.
		connection.setDoInput(true);
		// 设置请求方式，默认get
		connection.setRequestMethod("POST");
		// Post 请求不能使用缓存
		connection.setUseCaches(false);
		// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
		connection.setInstanceFollowRedirects(true);
		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
		// 进行编码
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		// 要注意的是connection.getOutputStream会隐含的进行connect。
		connection.connect();
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());

		// 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
		String contentStr = new String();
		// 企业id
		String userid = PropertiesUtils.findPropertiesKey("smsID");
		// 企业账号
		String account = PropertiesUtils.findPropertiesKey("smsAccount");
		// 密码
		String password = PropertiesUtils.findPropertiesKey("smsPassword");
		// 短信内容 （发送短信内容有固定模板，不可随意修改）
		//String content = "【平安高密】您的动态验证码为" + verificationCode + "，五分钟内输入有效。";

		contentStr = "action=send&userid=" + userid + "&account="
				+ URLEncoder.encode(account, "utf-8") + "&password=" + password
				+ "&mobile=" + phoneNum + "&content="
				+ URLEncoder.encode(content, "utf-8") + "&sendTime=&extno=";
		out.writeBytes(contentStr);
		out.flush();
		out.close(); // flush and close

		// 对返回值进行解析
		String returnstatus = parseXML(connection.getInputStream());
		connection.disconnect();
		return returnstatus;
	}
	
	
	/**
	 * dom4j 解析短信接口返回的xml
	 * 
	 * @param in
	 * @throws Exception
	 */
	public static String parseXML(InputStream in) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(in);
		// 获取根节点
		Element root = doc.getRootElement();
		// 获取子节点
		Element element = root.element("returnstatus");
		String returnstatus = element.getText();
		return returnstatus;
	}

	/**
	 * 移动端用户登录/注册服务(手机号)
	 * 
	 * @param phoneNum
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public Map loginForApp(String phoneNum,String passWord,String type) {
		Map map = new HashMap();
		if (StringUtils.isEmpty(phoneNum)) {
			map.put("reply", "0");
			map.put("content", "手机号为空");
			return map;
		}

		try {
			ClueProviderInfo obj = clueProviderInfoService
					.findByPhoneNum(phoneNum);
			if (obj != null) { // 该手机用户已注册
				/**
				 * 校验用户名和密码是否对应(type=1时为密码登录)
				 */
				if ("1".equals(type) &&
						!Md5Tool.getMd5(passWord).equals(obj.getPassWord())) {
					if(StringUtils.isEmpty(passWord)){
						map.put("reply", "0");
						map.put("content", "用户密码没有输入");
					}else{
						map.put("reply", "0");
						map.put("content", "用户密码错误");
					}
				}else{

					/**
					 * 返回用户基本信息 用户id, 手机号，积分，排名，头像图片（base64）
					 */
					map.put("reply", "1");
					map.put("trueName", obj.getTrueName());
					map.put("userid", obj.getId());
					map.put("phoneNum", obj.getPhoneNum());
					map.put("score", "1000");
					map.put("rank", "1");
					map.put("headPicture", obj.getHeadPicture());
					if(StringUtils.isEmpty(obj.getPassWord())){
						map.put("content", "已经设置过密码");
					}
				}
			} else { // 该手机用户为新用户，进行新增操作
				obj = new ClueProviderInfo();
				obj.setId(Common.getUUID());
				obj.setPhoneNum(phoneNum);
				obj.setScore("0");
				obj.setOpenFlag("1");
				obj.setCreateDate(new Date());
				
				// 注册
				clueProviderInfoService.add(obj);
				map.put("reply", "2");
				map.put("userid", obj.getId());
			}
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("reply", "0");
			map.put("content", "登录失败");
		}
		return map;
	}

	/**
	 * 用户上传/修改头像
	 * 
	 * @param id
	 *            用户id
	 * @param headPictureStr
	 *            头像
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/setHeadPicture")
	public Map setHeadPicture(String id, String headPicture) {
		Map map = new HashMap();
		try {
			if (StringUtils.isNotEmpty(id)
					&& StringUtils.isNotEmpty(headPicture)) {

				ClueProviderInfo obj = clueProviderInfoService.getById(id);
				if (obj != null) {
					obj.setHeadPicture(headPicture);
					clueProviderInfoService.setHeadPictureById(obj);
					map.put("reply", "1"); // 设置头像成功
				} else {
					map.put("reply", "0");
					map.put("content", "用户不存在");
				}

			} else {
				map.put("reply", "0");
				map.put("content", "参数值为空");
			}
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("reply", "0");
			map.put("content", "头像设置失败");
		}
		return map;
	}

	/**
	 * 初始化用户信息
	 * 
	 * @param phoneNum
	 * @param verificationCode
	 *            手机校验码
	 * @param serialNumber
	 *            序列号
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/initUserData")
	public Map initUserData(String phoneNum) {
		Map map = new HashMap();
		if (StringUtils.isEmpty(phoneNum)) {
			map.put("reply", "0");
			map.put("content", "手机号为空");
			return map;
		}

		try {
			ClueProviderInfo obj = clueProviderInfoService
					.findByPhoneNum(phoneNum);
			if (obj == null) {
				map.put("reply", "0");
				map.put("content", "手机号未注册");
				return map;
			}

			/**
			 * 返回用户基本信息 用户id, 手机号，积分，排名，头像图片（base64）
			 */
			map.put("reply", "1");
			map.put("trueName", obj.getTrueName());
			map.put("userid", obj.getId());
			map.put("phoneNum", obj.getPhoneNum());
			map.put("score", "1000");
			map.put("rank", "1");
			map.put("headPicture", obj.getHeadPicture());

		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("reply", "0");
			map.put("content", "加载用户信息异常");
		}
		return map;
	}

	/**
	 * 移动端调用获取授权文件
	 * 
	 * @param phoneNum
	 * @param verificationCode
	 *            手机号
	 * @param serialNumber
	 *            序列号
	 * @return 授权文件
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/getAuthorization")
	public byte[] getAuthorization(String phoneNum, String serialNumber) {
		Map map = new HashMap();
		if (StringUtils.isEmpty(phoneNum)) {
			map.put("reply", "0");
			map.put("content", "手机号为空");
			return null;
		}
		if (StringUtils.isEmpty(serialNumber)) {
			map.put("reply", "0");
			map.put("content", "序列号为空");
			return null;
		}

		// 生成总文件夹
		String basePath = request.getSession().getServletContext()
				.getRealPath("/authorDir");
		File baseDir = new File(basePath);
		if (!baseDir.exists()) {
			baseDir.mkdir();
		}
		// 生成存储序列号和对应授权文件的文件夹
		String authorPath = basePath + File.separator + phoneNum + "_"
				+ serialNumber;
		File authorDir = new File(authorPath);
		if (!authorDir.exists()) {
			authorDir.mkdir();
		}

		try {
			// 根据序列号查询表中是否有该授权文件
			PhoneLicense t = phoneLicenseService.findByIMEI(serialNumber);
			if (t == null) {// 该用户首次下载， 调用exe程序生成licessonw文件，并入库
				/**
				 * 获取授权文件 调用exe程序生成licessonw文件
				 */
				BufferedWriter fw = null;
				// 将待授权的序列号生成文件
				File tempSerialNumberFile = new File(authorPath
						+ File.separator + "authorNum.txt");
				if (!tempSerialNumberFile.exists()) {
					tempSerialNumberFile.createNewFile();
				}
				fw = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(tempSerialNumberFile, true),
						"UTF-8"));
				fw.append("IMEI:" + serialNumber);
				fw.flush(); // 全部写入缓存中的内容
				if (fw != null) {
					try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				// 拼接cmd命令
				StringBuffer cmdString = new StringBuffer();
				// 需要运行的exe文件路径
				cmdString.append(PropertiesUtils
						.findPropertiesKey(Constant.AUTHORIZATION_FILE)
						+ ".\\THIDMakeExpoLicense.exe ");
				// 读取生成的序列号文件
				cmdString.append("/cmd -h " + authorPath + File.separator
						+ "authorNum.txt" + " ");
				// 生成车牌识别授权文件
				cmdString.append("-p " + authorPath + File.separator
						+ "plateidsdk.lic ");
				// 授权截止时间
				cmdString.append("-t " + "2020.12.30 ");
				// 最大库容，1~200000
				cmdString.append("-n " + 200000 + " ");
				// 错误提示框级别选择（默认0：全部提示框，1：文件错误；2：授权错误，-1：无提示框）
				cmdString.append("-m -1");

				Process p = Runtime.getRuntime().exec(cmdString.toString());
				int num = p.waitFor();
				if (num == 0) {
					File licenseFile = new File(authorPath + File.separator
							+ "\\plateidsdk.lic");
					if (!licenseFile.exists()) {
						map.put("reply", "0");
						map.put("content", "授权文件生成失败");
						return null;
					}

					// 生成文件转字节数组
					FileInputStream is = new FileInputStream(licenseFile);
					byte[] resbyte = new byte[(int) licenseFile.length()];
					is.read(resbyte);
					is.close();

					// 新增一条数据
					t = new PhoneLicense();
					// 二进制流写入
					t.setAuthorFile(resbyte);
					t.setId(Common.getUUID());
					t.setPhoneNum(phoneNum);
					t.setSerialNum(serialNumber);
					t.setAuthorCount(0);
					t.setCreateDate(new Date());

					phoneLicenseService.add(t);

					map.put("reply", "1");
					map.put("license", resbyte);
					return resbyte;

				} else {
					map.put("reply", "0");
					return null;
				}

			} else { // 该用户已下载过授权文件，直接从数据库读取

				t.setPhoneNum(phoneNum);
				t.setAuthorCount(t.getAuthorCount() + 1);
				t.setSerialNum(serialNumber);
				t.setUpdateDate(new Date());
				phoneLicenseService.update(t);

				map.put("reply", "1");
				map.put("license", t.getAuthorFile());
				return t.getAuthorFile();
			}
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("reply", "0");
			map.put("content", "获取授权文件异常");
		} finally {
			// 删除生成的文件
			if (authorDir.isDirectory()) {
				authorDir.delete();
			}
		}

		return null;
	}

	public byte[] InputStreamToByte(InputStream iStrm) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = iStrm.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}

	public static void main(String[] args) {
		try {
			String returnstatus = readContentFromPost("18734804868", "123456");
			System.out.println(returnstatus);
			String aa=Md5Tool.getMd5("123");
			System.out.println(aa);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.hisign.publicsafety.web.filter;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import java.util.Map;
import java.text.SimpleDateFormat;

import com.hisign.publicsafety.controller.ccp.RestController;
import com.hisign.publicsafety.entity.ccp.BlackPlatnum;
import com.hisign.publicsafety.entity.ccp.ClueInfo;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.service.ccp.BlackPlatnumService;
import com.hisign.publicsafety.service.ccp.ClueInfoService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.DateUtils;
/**
 * 平板上传线索信息接口servlet
 * @author fankai
 * 2015-11-25
 */
public class UploadFileServlet extends HttpServlet {

	
	private static final Logger logger = LoggerFactory.getLogger(UploadFileServlet.class);
	
	private static final long serialVersionUID = 1242454377629451494L;

	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String upload = config.getServletContext().getRealPath("/upload");
		logger.info("upload==="+upload);
		ServletContext servletContext = config.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		IdService idService = (IdService) context.getBean("idService");
		ClueInfoService clueInfoService=(ClueInfoService) context.getBean("clueInfoService");
		StringBuffer FilePath = new StringBuffer("");
		String providerId = "";
		String collectNo = "";
		String collectPlace="";
		String platNum = "";
		Date collectDate=null;
		Date mineDate=null;
		String lon="";
		String lat="";
		String remark="";
		String subTime="";
		int id=0;
			
		String photoName= "";
		
		OutputStream reout = response.getOutputStream();
		// 创建文件项目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
		String temp = System.getProperty("java.io.tmpdir");
		// 设置缓冲区大小为 5M
		factory.setSizeThreshold(1024 * 1024 * 5);
		// 设置临时文件夹为temp
		factory.setRepository(new File(temp));
		// 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		// 设置上传文件大小的上限，-1表示无上限
		//servletFileUpload.setSizeMax(10 * 1024 * 1024);
		
		List<FileItem> photoList = null;
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			if (list != null && list.size() > 0) {
				photoList = new ArrayList();
				
				ClueInfo clueInfo=new ClueInfo();
				for (FileItem item : list) {
					
					//参数名
					String paraName = item.getFieldName();
					InputStream is = item.getInputStream();
					
					if ("collectPlace".equals(paraName)) { //采集地点
						collectPlace=inputStream2String(is);
						collectPlace=URLDecoder.decode(collectPlace,"utf-8");
					} else if ("providerId".equals(paraName)) { //线索提供者id
						providerId=inputStream2String(is);
					} else if ("lon".equals(paraName)) { //经度
						lon=inputStream2String(is);
					} else if ("lat".equals(paraName)) { //纬度
						lat=inputStream2String(is);
					} else if ("remark".equals(paraName)) { //备注
						remark=inputStream2String(is);
						remark= URLDecoder.decode(remark,"utf-8");
					} else if ("platNum".equals(paraName)) { //车牌号
						platNum = inputStream2String(is);
						platNum = URLDecoder.decode(platNum,"utf-8");
					} else if ("collectDate".equals(paraName)) { //采集时间
						String collectDateStr=inputStream2String(is);
						//精确至分钟 
						collectDate = DateUtils.string2Datetime(collectDateStr,DateUtils.YMDHMCCP);
					} else if ("mobileTime".equals(paraName)) { //本机时间
						String mobileTimeStr=inputStream2String(is);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						mineDate= sdf.parse(mobileTimeStr);
						//计算本机时间与服务器时间差
						Date now = new Date(System.currentTimeMillis());
						long time=(now.getTime()-mineDate.getTime())/1000/60;
						subTime =String.valueOf(time);
					} else if (paraName.contains("file")) {
						photoList.add(item);
					} else if ("photoName".equals(paraName)) { //采集照片名称 update by zq 2016/10/11
						photoName = inputStream2String(is);
						photoName = URLDecoder.decode(platNum,"utf-8");
					}
					
				}
				
				/**
				 * update by zq 2016/10/11 
				 * 用于恢复之前丢失的数据信息与本地图片文件进行关联
				 * 对于没有照片的上报数据，则必须提供照片名称（多个照片名称以‘,’分割）
				 */
				if (photoList.size() < 1 && StringUtils.isEmpty(photoName)) {
					reout.write("failure：请上传照片或者提供照片名称".getBytes());
				} else {
					//采集地点 采集人id 采集时间必填
					if (StringUtils.isNotEmpty(collectPlace) &&
						StringUtils.isNotEmpty(providerId) &&
						collectDate != null) {
						
						//系统生成采集编号
						collectNo = idService.getEntrustNo("");
						id=idService.getId();
						
						//拼写图片保存路径  服务根目录/线索提供人/采集编号/线索图片
						FilePath.append(File.separator).append(providerId);
						FilePath.append(File.separator).append(collectNo);
						FilePath.append(File.separator);
						
						//赋值
						clueInfo.setId(Common.getUUID());
						clueInfo.setCollectNo(collectNo);
						clueInfo.setCollectPlace(collectPlace);
						clueInfo.setCollectDate(collectDate);
						clueInfo.setProviderId(providerId);
						clueInfo.setCreateDate(new Date());
						clueInfo.setLon(lon);
						clueInfo.setLat(lat);
						clueInfo.setPlatNum(platNum);
						clueInfo.setRemark(remark);
						clueInfo.setSubTime(subTime);
						clueInfo.setPhotoName(photoName);
						
						//补齐用户单位
						String unitCode=clueInfoService.queryByProviderId(providerId);
						clueInfo.setUnitCode(unitCode);
						
						if (photoList.size() < 1) { //若没有图片 只创建空文件夹
							File file = new File(upload + FilePath.toString());
							if (!file.exists()) {
								file.mkdirs();
							}
						} else {
							for (FileItem item : photoList) {
								
								String fileName = item.getName();
								fileName = (fileName == null) ? null : fileName.toLowerCase();
								InputStream is = item.getInputStream();
								//存储文件
								inputStream2File(is, upload + FilePath.toString(), fileName);
							}
						}
						
						
						//存储线索信息
						clueInfoService.insertClueInfo(clueInfo);
						
						//根据车牌号查询黑名单
						if(StringUtils.isNotEmpty(platNum)){
							//短息模板没有下发下来，暂时不做处理
							BlackPlatnumService blackPlatnumService=
									(BlackPlatnumService) context.getBean("blackPlatnumService");						
							BlackPlatnum blackPlat=blackPlatnumService.findByPlatnum(platNum);
							if(blackPlat!=null){
								RestController rest=new RestController();
								Map map=rest.sendVerificationCode(blackPlat.getOfferPho(),"2",platNum,blackPlat.getOfferName(),blackPlat.getOfferPho());
								if("1".equals(map.get("reply"))){
									map=rest.sendVerificationCode(blackPlat.getInformPho(),"2",platNum,blackPlat.getOfferName(),blackPlat.getOfferPho());
									if("1".equals(map.get("reply"))){//两条短信都发送成功后更改车牌预警下的数据状态
										//blackPlat.setUpdateTime(new Date(System.currentTimeMillis()));
										blackPlat.setStatus("是");
										blackPlatnumService.update(blackPlat);
									}
								}
							}
						}
						reout.write("success".getBytes());
					} else {
						reout.write("failure：参数不完整".getBytes());
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			reout.write("failure：线索上传异常".getBytes());
		}
		
		reout.flush();
		reout.close();
	}

	// 流转化成字符串
	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	// 流转化成文件
	public static void inputStream2File(InputStream is, String savePath,String fileName) throws Exception {
		System.out.println("文件保存路径为:" + savePath);
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		File savefile = new File(savePath+ fileName);
		if (!savefile.exists()) {
			savefile.createNewFile();
		}
		InputStream inputSteam = is;
		BufferedInputStream fis = new BufferedInputStream(inputSteam);
		FileOutputStream fos = new FileOutputStream(savefile);
		int f;
		while ((f = fis.read()) != -1) {
			fos.write(f);
		}
		fos.flush();
		fos.close();
		fis.close();
		inputSteam.close();

	}

}

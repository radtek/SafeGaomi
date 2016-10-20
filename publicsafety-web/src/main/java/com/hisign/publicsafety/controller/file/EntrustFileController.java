/**
 * 委托相关项目操作
 */
package com.hisign.publicsafety.controller.file;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.excel.entity.ExcelData;
import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.AliAccountDetail;
import com.hisign.publicsafety.entity.AliLoginHistory;
import com.hisign.publicsafety.entity.AliRegisterInfo;
import com.hisign.publicsafety.entity.AliTransactionRecords;
import com.hisign.publicsafety.entity.AliTransferDetail;
import com.hisign.publicsafety.entity.EntrusAttachment;
import com.hisign.publicsafety.entity.Log;
import com.hisign.publicsafety.service.AliAccountService;
import com.hisign.publicsafety.service.AliLoginService;
import com.hisign.publicsafety.service.AliRegistService;
import com.hisign.publicsafety.service.AliTransactionService;
import com.hisign.publicsafety.service.AliTransferService;
import com.hisign.publicsafety.service.EntrustAttachService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.POIUtils;

/**
 * @author chailiangzhi
 * @date 2015-8-7
 * 文书下载和阿里反馈结果导出
 */
@Controller
@RequestMapping("/entrust/file/")
public class EntrustFileController extends BaseController {

	@Inject
	private EntrustAttachService entrustAttachService;
	@Inject
	private AliRegistService aliRegistService;
	@Inject
	private AliLoginService aliLoginService;
	@Inject
	private AliTransactionService aliTransactionService;
	@Inject
	private AliAccountService aliAccountService;
	@Inject
	private AliTransferService aliTransferService;


	private static Logger logger = LoggerFactory.getLogger(EntrustFileController.class);

	public enum AliDataType {
		REGIST_INFO, LOGIN_LOG, TRADE_LOG, ACCOUNT_LOG, TRANSFER_LOG;
		public static AliDataType toEnum(String str) {
			try {
				return valueOf(str);
			} catch (Exception ex) {
				return REGIST_INFO;
			}
		}
	}

	/**
	 * 下载委托文书
	 * @param response
	 * @param fileName
	 * @param entrustNo
	 * @throws Exception
	 */
	@RequestMapping("official_doc")
	public void officialDoc(HttpServletResponse response, String attachId) throws Exception {
		//, String entrustNo
		EntrusAttachment attachment = entrustAttachService.getById(attachId);
		String fileContent = attachment.getFileContent();
		String[] fileContents = fileContent.split(";");
		String[] dataStrs = fileContents[0].split(":");
		String dataStr;
		if (dataStrs.length == 2) {
			dataStr = dataStrs[1];
		} else {
			dataStr = "";
		}
		String base64Str = fileContents[1].split(",")[1];
		String tempName = new String(attachment.getFileName().getBytes(), Constant.DOWNLOAD_FILENAME_CHARSET);
		response.setHeader("content-disposition", "attachment;filename=" + tempName);
		response.setContentType(dataStr);
		//		Base64 base64 = new Base64();
		byte[] bytes = Base64.decodeBase64(base64Str);
		OutputStream out = response.getOutputStream();
		try {
			out.write(bytes);
		} catch (Exception e) {
			throw e;
		} finally {
			out.flush();
			out.close();
		}
	}

	/**
	 * 下载阿里反馈的指定数据文件
	 * @param response
	 * @param aliDataType
	 * @param entrustNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ali_result")
	public String aliResult(HttpServletResponse response, String aliDataType, String entrustNo) throws Exception {
		switch (AliDataType.toEnum(aliDataType)) {
		case REGIST_INFO:
			AliRegisterInfo paraRegister = new AliRegisterInfo();
			paraRegister.setEntrustNo(entrustNo);
			List list = aliRegistService.queryAll(paraRegister);
			POIUtils.exportToExcel(response, "注册信息", list, AliRegisterInfo.class, "注册", list.size());
			//			response.sendRedirect("/entrust/file/ali_result?aliDataType=LOGIN_LOG&entrustNo=" + entrustNo);
			return "redirect:/entrust/file/ali_result?aliDataType=LOGIN_LOG&entrustNo=" + entrustNo;
		case LOGIN_LOG:
			AliLoginHistory paraLogin = new AliLoginHistory();
			paraLogin.setEntrustNo(entrustNo);
			list = aliLoginService.queryAll(paraLogin);
			POIUtils.exportToExcel(response, "登录日志", list, AliLoginHistory.class, "登录", list.size());
			//			response.sendRedirect("/entrust/file/ali_result?aliDataType=TRADE_LOG&entrustNo=" + entrustNo);
			//			break;
			return "redirect:/entrust/file/ali_result?aliDataType=TRADE_LOG&entrustNo=" + entrustNo;
		case TRADE_LOG:
			AliTransactionRecords paraTransaction = new AliTransactionRecords();
			paraTransaction.setEntrustNo(entrustNo);
			list = aliTransactionService.queryAll(paraTransaction);
			POIUtils.exportToExcel(response, "交易记录", list, AliTransactionRecords.class, "交易", list.size());
			//			response.sendRedirect("/entrust/file/ali_result?aliDataType=ACCOUNT_LOG&entrustNo=" + entrustNo);
			//			break;
			return "redirect:/entrust/file/ali_result?aliDataType=ACCOUNT_LOG&entrustNo=" + entrustNo;
		case ACCOUNT_LOG:
			AliAccountDetail paraAccount = new AliAccountDetail();
			paraAccount.setEntrustNo(entrustNo);
			list = aliAccountService.queryAll(paraAccount);
			POIUtils.exportToExcel(response, "账户明细", list, AliAccountDetail.class, "账户", list.size());
			//			response.sendRedirect("/entrust/file/ali_result?aliDataType=TRANSFER_LOG&entrustNo=" + entrustNo);
			//			break;
			return "redirect:/entrust/file/ali_result?aliDataType=TRANSFER_LOG&entrustNo=" + entrustNo;
		case TRANSFER_LOG:
			AliTransferDetail paraTransfer = new AliTransferDetail();
			paraTransfer.setEntrustNo(entrustNo);
			list = aliTransferService.queryAll(paraTransfer);
			POIUtils.exportToExcel(response, "转账明细", list, AliTransferDetail.class, "转账", list.size());
			break;
		default:
			logger.error("unknown aliDataType:" + aliDataType);
			break;
		}
		return null;
	}

	/**
	 * 下载阿里反馈的所有数据到一个文件
	 * @param response
	 * @param entrustNo
	 * @throws Exception
	 */
	@RequestMapping("ali_result_all")
	public String aliResultAll(HttpServletResponse response, String entrustNo) throws Exception {
		int allCount=0;
		AliRegisterInfo paraRegister = new AliRegisterInfo();
		paraRegister.setEntrustNo(entrustNo);
		List<ExcelData> excelDatas = new ArrayList<ExcelData>();
		//注册信息
		List list = aliRegistService.queryAll(paraRegister);
		allCount+=list.size();
		ExcelData excelData = new ExcelData();
		excelData.objs = list;
		excelData.clazz = AliRegisterInfo.class;
		excelData.sheetName = "注册信息";
		excelDatas.add(excelData);
		//登录日志
		AliLoginHistory paraLogin = new AliLoginHistory();
		paraLogin.setEntrustNo(entrustNo);
		list = aliLoginService.queryAll(paraLogin);
		allCount+=list.size();
		excelData = new ExcelData();
		excelData.objs = list;
		excelData.clazz = AliLoginHistory.class;
		excelData.sheetName = "登录日志";
		excelDatas.add(excelData);
		//交易记录
		AliTransactionRecords paraTransaction = new AliTransactionRecords();
		paraTransaction.setEntrustNo(entrustNo);
		list = aliTransactionService.queryAll(paraTransaction);
		allCount+=list.size();
		excelData = new ExcelData();
		excelData.objs = list;
		excelData.clazz = AliTransactionRecords.class;
		excelData.sheetName = "交易记录";
		excelDatas.add(excelData);
		//账户明细
		AliAccountDetail paraAccount = new AliAccountDetail();
		paraAccount.setEntrustNo(entrustNo);
		list = aliAccountService.queryAll(paraAccount);
		allCount+=list.size();
		excelData = new ExcelData();
		excelData.objs = list;
		excelData.clazz = AliAccountDetail.class;
		excelData.sheetName = "账户明细";
		excelDatas.add(excelData);
		//转账明细
		AliTransferDetail paraTransfer = new AliTransferDetail();
		paraTransfer.setEntrustNo(entrustNo);
		list = aliTransferService.queryAll(paraTransfer);
		allCount+=list.size();
		excelData = new ExcelData();
		excelData.objs = list;
		excelData.clazz = AliTransferDetail.class;
		excelData.sheetName = "转账明细";
		if(allCount==0){
			return Common.JSP_PATH + "/entrust/nofeedback";
		}
		excelDatas.add(excelData);
		//
		POIUtils.exportToExcel(response, "阿里反馈信息", excelDatas, 10000);
		//业务日志
		Log log = new Log();
		StringBuilder moduleActionSb = new StringBuilder();
		moduleActionSb.append("委托反馈全部导出");
		log.setModule(moduleActionSb.toString());
		log.setRev1(entrustNo);
		putLog("aliResultAll", log);
		return null;
	}

}

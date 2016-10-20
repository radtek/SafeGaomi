package com.hisign.publicsafety.entity;

import com.hisign.publicsafety.base.entity.BaseEntity;
import com.hisign.publicsafety.util.ExcelDataMapper;
import java.util.*;

/**
 * 阿里反馈-阿里交易记录
 * @author fankai
 * @date 2015-8-15
 */
public class AliTransactionRecords extends BaseEntity implements java.io.Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -8367431114269381990L;
	
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 委托编号
	 */
	private String entrustNo;
	
	/**
	 * 交易号
	 */
	private String tradeNo;
	
	/**
	 * 外部交易号
	 */
	private String outTradeNo;
	
	/**
	 * 交易状态
	 */
	private String tradeStatusDesc;
	
	/**
	 * 合作id
	 */
	private String partnerId;
	
	/**
	 * 买家卡id
	 */
	private String tradeBuyerId;
	
	/**
	 * 买家信息
	 */
	private String buyerInfo;
	
	/**
	 * 卖家卡id
	 */
	private String tradeSellerId;
	
	/**
	 * 卖家信息
	 */
	private String sellerInfo;
	
	/**
	 * 交易金额
	 */
	private String tradeTotalAmt;
	
	/**
	 * 收款时间
	 */
	private String tradeReceivePayDate;
	
	/**
	 * 最后修改时间
	 */
	private String gmtModified;
	
	/**
	 * 创建时间
	 */
	private String tradeCreateDate;
	
	/**
	 * 交易类型
	 */
	private String tradeTypeDesc;
	
	/**
	 * 来源地
	 */
	private String tradeFromDesc;
	
	/**
	 * 商品名称
	 */
	private String goodsTitle;
	
	/**
	 * 收货人地址
	 */
	private String receiveAddress;
	
	/**
	 * 数据创建人
	 */
	private int dataCreateUser;
	
	/**
	 * 数据入库时间
	 */
	private Date dataCreateDate;

	@ExcelDataMapper(title = "最后修改时间", order = 1)
	public String getGmtModified() {
		return this.gmtModified;
	}

	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}

	@ExcelDataMapper(title = "买家信息", order = 2)
	public String getBuyerInfo() {
		return this.buyerInfo;
	}

	public void setBuyerInfo(String buyerInfo) {
		this.buyerInfo = buyerInfo;
	}

	@ExcelDataMapper(title = "交易类型", order = 3)
	public String getTradeTypeDesc() {
		return this.tradeTypeDesc;
	}

	public void setTradeTypeDesc(String tradeTypeDesc) {
		this.tradeTypeDesc = tradeTypeDesc;
	}

	@ExcelDataMapper(title = "创建时间", order = 4)
	public String getTradeCreateDate() {
		return this.tradeCreateDate;
	}

	public void setTradeCreateDate(String tradeCreateDate) {
		this.tradeCreateDate = tradeCreateDate;
	}

	@ExcelDataMapper(title = "卖家卡ID", order = 5)
	public String getTradeSellerId() {
		return this.tradeSellerId;
	}

	public void setTradeSellerId(String tradeSellerId) {
		this.tradeSellerId = tradeSellerId;
	}

	@ExcelDataMapper(title = "交易号", order = 6)
	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	@ExcelDataMapper(title = "卖家信息", order = 7)
	public String getSellerInfo() {
		return this.sellerInfo;
	}

	public void setSellerInfo(String sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	@ExcelDataMapper(title = "买家卡ID", order = 8)
	public String getTradeBuyerId() {
		return this.tradeBuyerId;
	}

	public void setTradeBuyerId(String tradeBuyerId) {
		this.tradeBuyerId = tradeBuyerId;
	}

	@ExcelDataMapper(title = "合作ID", order = 9)
	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	@ExcelDataMapper(title = "收款时间", order = 10)
	public String getTradeReceivePayDate() {
		return this.tradeReceivePayDate;
	}

	public void setTradeReceivePayDate(String tradeReceivePayDate) {
		this.tradeReceivePayDate = tradeReceivePayDate;
	}

	@ExcelDataMapper(title = "外部交易号", order = 11)
	public String getOutTradeNo() {
		return this.outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDataCreateUser() {
		return this.dataCreateUser;
	}

	public void setDataCreateUser(int dataCreateUser) {
		this.dataCreateUser = dataCreateUser;
	}

	@ExcelDataMapper(title = "来源地", order = 14)
	public String getTradeFromDesc() {
		return this.tradeFromDesc;
	}

	public void setTradeFromDesc(String tradeFromDesc) {
		this.tradeFromDesc = tradeFromDesc;
	}

	public Date getDataCreateDate() {
		return this.dataCreateDate;
	}

	public void setDataCreateDate(Date dataCreateDate) {
		this.dataCreateDate = dataCreateDate;
	}

	@ExcelDataMapper(title = "收货人地址", order = 16)
	public String getReceiveAddress() {
		return this.receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	@ExcelDataMapper(title = "商品名称", order = 17)
	public String getGoodsTitle() {
		return this.goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	@ExcelDataMapper(title = "交易金额", order = 18)
	public String getTradeTotalAmt() {
		return this.tradeTotalAmt;
	}

	public void setTradeTotalAmt(String tradeTotalAmt) {
		this.tradeTotalAmt = tradeTotalAmt;
	}

	@ExcelDataMapper(title = "交易状态", order = 19)
	public String getTradeStatusDesc() {
		return this.tradeStatusDesc;
	}

	public void setTradeStatusDesc(String tradeStatusDesc) {
		this.tradeStatusDesc = tradeStatusDesc;
	}

	public String getEntrustNo() {
		return this.entrustNo;
	}

	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}
}

package com.hisign.publicsafety.entity.ccp;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hisign.publicsafety.base.entity.BaseEntity;
import com.hisign.publicsafety.util.JsonDateSerializer;

/**
 * 线索征集平台-线索信息实体
 * 
 * @author hotdog 2015-11-23
 */
public class ClueInfo extends BaseEntity implements java.io.Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 数据主键
	 */
	private String id;

	/**
	 * 采集编号
	 */
	private String collectNo;

	/**
	 * 采集地点
	 */
	private String collectPlace;

	/**
	 * 采集人
	 */
	private String providerName;

	/**
	 * 采集人ID
	 */
	private String providerId;

	/**
	 * 采集人手机号
	 */
	private String proViderPhoneNum;

	/**
	 * 采集时间
	 */
	private Date collectDate;

	/**
	 * 采集时间（起始 查询用）
	 */
	private String collectBegDatetime;

	/**
	 * 采集时间（截止 查询用）
	 */
	private String collectEndDatetime;

	/**
	 * GPS信息-经度
	 */
	private String lon;

	/**
	 * GPS信息-纬度
	 */
	private String lat;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 车牌号
	 */
	private String platNum;

	/**
	 * 图片地址List
	 */
	private List photoSrcList;

	/**
	 * 线索照片数量
	 */
	private int cluePhotoCount;

	/**
	 * 取第一张图片作为列表展示的图片
	 */
	private String firstPhotoSrc;

	/**
	 * 第一张图片转base64字符串
	 */
	private String firstPhotoBase64Str;

	/**
	 * 用户单位
	 * */
	private String unitCode;
	/**
	 * 用户单位中文
	 * */
	private String unitName;
	/**
	 * 本机时间与服务器时间差
	 * */
	private String subTime;
	
	/**
	 * update by zq 2016/10/11
	 * 照片名称
	 */
	public String photoName;
	
	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getCollectNo() {
		return collectNo;
	}

	public void setCollectNo(String collectNo) {
		this.collectNo = collectNo;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getCollectPlace() {
		return collectPlace;
	}

	public void setCollectPlace(String collectPlace) {
		this.collectPlace = collectPlace;
	}

	/**
	 * 时间格式化
	 * 
	 * @return
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

	public String getCollectBegDatetime() {
		return collectBegDatetime;
	}

	public void setCollectBegDatetime(String collectBegDatetime) {
		this.collectBegDatetime = collectBegDatetime;
	}

	public String getCollectEndDatetime() {
		return collectEndDatetime;
	}

	public void setCollectEndDatetime(String collectEndDatetime) {
		this.collectEndDatetime = collectEndDatetime;
	}

	public List getPhotoSrcList() {
		return photoSrcList;
	}

	public void setPhotoSrcList(List photoSrcList) {
		this.photoSrcList = photoSrcList;
	}

	public int getCluePhotoCount() {
		return cluePhotoCount;
	}

	public void setCluePhotoCount(int cluePhotoCount) {
		this.cluePhotoCount = cluePhotoCount;
	}

	public String getFirstPhotoSrc() {
		return firstPhotoSrc;
	}

	public void setFirstPhotoSrc(String firstPhotoSrc) {
		this.firstPhotoSrc = firstPhotoSrc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlatNum() {
		return platNum;
	}

	public void setPlatNum(String platNum) {
		this.platNum = platNum;
	}

	public String getProViderPhoneNum() {
		return proViderPhoneNum;
	}

	public void setProViderPhoneNum(String proViderPhoneNum) {
		this.proViderPhoneNum = proViderPhoneNum;
	}

	public String getFirstPhotoBase64Str() {
		return firstPhotoBase64Str;
	}

	public void setFirstPhotoBase64Str(String firstPhotoBase64Str) {
		this.firstPhotoBase64Str = firstPhotoBase64Str;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getSubTime() {
		return subTime;
	}

	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


}

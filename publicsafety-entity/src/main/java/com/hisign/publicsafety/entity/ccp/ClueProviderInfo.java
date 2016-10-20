package com.hisign.publicsafety.entity.ccp;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.hisign.publicsafety.base.entity.BaseEntity;

/**
 * 线索征集平台-线索提供人信息实体
 * 
 * @author hotdog 2015-11-23
 */
public class ClueProviderInfo extends BaseEntity implements
		java.io.Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 数据主键
	 */
	private String id;

	/**
	 * 真实姓名
	 */
	private String trueName;

	/**
	 * 电话号码
	 */
	private String phoneNum;

	/**
	 * 身份证号
	 */
	private String idCardNum;

	/**
	 * 用户积分
	 */
	private String score;

	/**
	 * 启用标识
	 */
	private String openFlag;

	/**
	 * 身份证照片（正面） 流文件
	 */
	private MultipartFile idCardPhotoFront;

	/**
	 * 身份证照片（背面）流文件
	 */
	private MultipartFile idCardPhotoBack;

	/**
	 * 身份证照片（正面）base64转码
	 */
	private String idCardPhotoFrontStr;

	/**
	 * 身份证照片（背面）base64转码
	 */
	private String idCardPhotoBackStr;
	
	/**
	 * 用户单位
	 * */
	private String unitCode;
	/**
	 * 单位名称
	 */
	private String unitName;
	/**
	 * 用户密码
	 * */
	private String passWord;

	
	/**
	 * 用户头像（base64）
	 */
	private String headPicture;

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getIdCardPhotoFrontStr() {
		return idCardPhotoFrontStr;
	}

	public void setIdCardPhotoFrontStr(String idCardPhotoFrontStr) {
		this.idCardPhotoFrontStr = idCardPhotoFrontStr;
	}

	public String getIdCardPhotoBackStr() {
		return idCardPhotoBackStr;
	}

	public void setIdCardPhotoBackStr(String idCardPhotoBackStr) {
		this.idCardPhotoBackStr = idCardPhotoBackStr;
	}

	public MultipartFile getIdCardPhotoFront() {
		return idCardPhotoFront;
	}

	public void setIdCardPhotoFront(MultipartFile idCardPhotoFront) {
		this.idCardPhotoFront = idCardPhotoFront;
	}

	public MultipartFile getIdCardPhotoBack() {
		return idCardPhotoBack;
	}

	public void setIdCardPhotoBack(MultipartFile idCardPhotoBack) {
		this.idCardPhotoBack = idCardPhotoBack;
	}

	public String getHeadPicture() {
		return headPicture;
	}

	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	

}

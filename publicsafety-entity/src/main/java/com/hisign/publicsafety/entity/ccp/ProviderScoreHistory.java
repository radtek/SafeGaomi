package com.hisign.publicsafety.entity.ccp;

import com.hisign.publicsafety.base.entity.BaseEntity;

/**
 * 线索征集平台-用户积分记录实体
 * @author hotdog
 *
 */
public class ProviderScoreHistory extends BaseEntity implements java.io.Serializable{

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 数据主键
	 */
	private String id;
	
	/**
	 * 积分类型
	 */
	private String type;
	
	/**
	 * 当前积分
	 */
	private String score;
	
	/**
	 * 关联id
	 */
	private String linkId;
	
	/**
	 * 注册用户id
	 */
	private String providerId;
	
}

/**
 * 
 */
package com.hisign.publicsafety.service.impl;

import java.text.NumberFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisign.publicsafety.entity.SeqId;
import com.hisign.publicsafety.mapper.SeqIdMapper;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.util.DateUtils;
import com.hisign.publicsafety.util.DateUtils.DateFormat;

/**
 * @author chailiangzhi
 * @date 2015-8-11
 * 编号生成服务实现
 */
@Service("idService")
public class IdServiceImpl implements IdService {
	@Autowired
	private SeqIdMapper seqIdMapper;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.IdService#getId()
	 */
	@Override
	public int getId() throws Exception {
		SeqId seqId = new SeqId();
		seqIdMapper.add(seqId);
		return seqId.getId();
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.IdService#getEntrustNo()
	 */
	@Override
	public String getEntrustNo(String unitNo) throws Exception {
		String dateYM = DateUtils.parse2String(new Date(), DateFormat.YMDS);
		return "XS" + dateYM + getSn6();
	}

	private String getSn6() throws Exception {
		int id = getId();
		// 得到一个NumberFormat的实例  
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组  
		nf.setGroupingUsed(false);
		// 设置最大整数位数  
		nf.setMaximumIntegerDigits(6);
		// 设置最小整数位数  
		nf.setMinimumIntegerDigits(6);
		String sn = nf.format(id);
		return sn;
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.IdService#getSeriesCaseNo(java.lang.String)
	 */
	@Override
	public String getSeriesCaseNo(String unitNo) throws Exception {
		String dateYM = DateUtils.parse2String(new Date(), DateFormat.YM);
		StringBuilder sb = new StringBuilder();
		sb.append("CB").append(unitNo).append(dateYM).append(getSn6());
		return sb.toString();
	}

	public String getApiSn() throws Exception {
		int id = getId();
		// 得到一个NumberFormat的实例  
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组  
		nf.setGroupingUsed(false);
		// 设置最大整数位数  
		nf.setMaximumIntegerDigits(12);
		// 设置最小整数位数  
		nf.setMinimumIntegerDigits(12);
		String sn = nf.format(id);
		return sn;
	}

}

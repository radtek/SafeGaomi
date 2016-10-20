/**
 * 
 */
package com.hisign.publicsafety.service.impl.series;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.entity.AliRegisterInfo;
import com.hisign.publicsafety.entity.SeriesCaseAlipay;
import com.hisign.publicsafety.entity.SeriesCaseTelephone;
import com.hisign.publicsafety.mapper.series.SeriesAlipayMapper;
import com.hisign.publicsafety.mapper.series.SeriesTelephoneMapper;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.service.series.SeriesBasisService;

/**
 * @author chailiangzhi
 * @date 2015-9-1
 * 串并案依据服务实现
 */
@Transactional
@Service("seriesBasisService")
public class SeriesBasisServiceImpl extends GeneralService implements SeriesBasisService {

	@Autowired
	private IdService idService;
	@Autowired
	private SeriesAlipayMapper seriesAlipayMapper;
	@Autowired
	private SeriesTelephoneMapper seriesTelephoneMapper;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.series.SeriesBasisService#saveBasis(com.hisign.publicsafety.entity.AliRegisterInfo)
	 */
	@Override
	public boolean saveBasis(String entrustCaseId, List<AliRegisterInfo> aliRegisterInfos) throws Exception {
		for (AliRegisterInfo aliRegisterInfo : aliRegisterInfos) {
			//保存串并依据-电话
			SeriesCaseTelephone telephone = new SeriesCaseTelephone();
			telephone.setId(String.valueOf(idService.getId()));
			telephone.setEntrustCaseId(entrustCaseId);
			telephone.setTelephone(aliRegisterInfo.getMobile());
			telephone.setCreateDate(new Date());
			seriesTelephoneMapper.add(telephone);
			//保存串并依据-支付宝
			SeriesCaseAlipay alipay = new SeriesCaseAlipay();
			alipay.setId(String.valueOf(idService.getId()));
			alipay.setEntrustCaseId(entrustCaseId);
			alipay.setAlipayCard(aliRegisterInfo.getAlipayId());
			alipay.setCreateDate(new Date());
			seriesAlipayMapper.add(alipay);
		}
		return true;
	}

}

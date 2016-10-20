/**
 * 
 */
package com.hisign.publicsafety.service.impl.series;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.EntrustBaseInfo;
import com.hisign.publicsafety.entity.EntrustCaseInfo;
import com.hisign.publicsafety.entity.SeriesCaseAlipay;
import com.hisign.publicsafety.entity.SeriesCaseBankcard;
import com.hisign.publicsafety.entity.SeriesCaseCertificate;
import com.hisign.publicsafety.entity.SeriesCaseDetail;
import com.hisign.publicsafety.entity.SeriesCaseInfo;
import com.hisign.publicsafety.entity.SeriesCaseTelephone;
import com.hisign.publicsafety.entity.series.CaseDataForSelect;
import com.hisign.publicsafety.entity.series.CaseInfoInSeries;
import com.hisign.publicsafety.entity.series.SeriesData;
import com.hisign.publicsafety.mapper.entrust.EntrustBaseMapper;
import com.hisign.publicsafety.mapper.entrust.EntrustCaseMapper;
import com.hisign.publicsafety.mapper.series.SeriesAlipayMapper;
import com.hisign.publicsafety.mapper.series.SeriesBankcardMapper;
import com.hisign.publicsafety.mapper.series.SeriesCaseDetailMapper;
import com.hisign.publicsafety.mapper.series.SeriesCaseInfoMapper;
import com.hisign.publicsafety.mapper.series.SeriesCertificateMapper;
import com.hisign.publicsafety.mapper.series.SeriesTelephoneMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.EntrustCaseService;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.service.series.SeriesCaseListService;
import com.hisign.publicsafety.service.series.SeriesDetailCaseListService;
import com.hisign.publicsafety.service.series.SeriesInfoListService;
import com.hisign.publicsafety.service.series.SeriesSearchListService;
import com.hisign.publicsafety.service.series.SeriesService;
import com.hisign.publicsafety.util.EncodeUtil;

/**
 * @author chailiangzhi
 * @date 2015-9-11
 * 串并案服务实现
 */
@Transactional
@Service("seriesService")
public class SeriesServiceImpl extends GeneralService implements SeriesService {

	/**
	 * 可选案件列表
	 */
	@Autowired
	private SeriesCaseListService seriesListService;
	/**
	 * 串并搜索结果列表
	 */
	@Autowired
	private SeriesSearchListService seriesSearchListService;
	@Autowired
	private SeriesCaseInfoMapper seriesCaseInfoMapper;
	@Autowired
	private SeriesCaseDetailMapper seriesCaseDetailMapper;
	/**
	 * 串并信息列表
	 */
	@Autowired
	private SeriesInfoListService seriesInfoListService;
	/**
	 * 串并详情里的案件列表
	 */
	@Autowired
	private SeriesDetailCaseListService seriesDetailCaseListService;
	@Autowired
	private EntrustCaseService entrustCaseService;
	@Autowired
	private EntrustBaseMapper entrustBaseMapper;
	@Autowired
	private EntrustCaseMapper entrustCaseMapper;

	@Autowired
	private SeriesAlipayMapper seriesAlipayMapper;
	@Autowired
	private SeriesTelephoneMapper seriesTelephoneMapper;
	@Autowired
	private SeriesCertificateMapper seriesCertificateMapper;
	@Autowired
	private SeriesBankcardMapper seriesBankcardMapper;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.series.SeriesService#queryCase(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.util.Map)
	 */
	@Override
	public DataResponse<CaseDataForSelect> queryCase(PageView pageView, Map<String, Object> paraMap) {
		PageView seriesListPageView = seriesListService.query(pageView, paraMap);
		return seriesListPageView.getDataResponse();
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.series.SeriesService#seriesSearch(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.util.Map)
	 */
	@Override
	public DataResponse<CaseDataForSelect> seriesSearch(PageView pageView, Map<String, Object> paraMap)
			throws Exception {
		PageView seriesSearchListPageView = seriesSearchListService.query(pageView, paraMap);
		return seriesSearchListPageView.getDataResponse();
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.series.SeriesService#seriesSave(com.hisign.publicsafety.entity.series.SeriesData)
	 */
	@Override
	public boolean seriesSave(SeriesData seriesData) throws Exception {
		SeriesCaseInfo seriesCaseInfo = seriesData.getSeriesCaseInfo();
		seriesCaseInfo.setSeriesReasonInt(EncodeUtil.encodeSeriesReason(seriesCaseInfo.getSeriesReason()));
		List<SeriesCaseDetail> seriesCaseDetails = seriesData.getSeriesCaseDetails();
		// 串并案编号
		String seriesCaseNo = seriesCaseInfo.getSeriesCaseNo();
		// 根据串并案编号删除旧的串并案信息
		seriesCaseInfoMapper.deleteBySeriesNo(seriesCaseNo);
		// 根据串并案编号删除旧的串并案详情
		seriesCaseDetailMapper.deleteBySeriesNo(seriesCaseNo);
		// 增加新的串并案信息
		seriesCaseInfoMapper.add(seriesCaseInfo);
		// 增加新的串并案详情
		seriesCaseDetailMapper.addBatch(seriesCaseDetails);
		// 修改案件状态为"已串并"
		for (SeriesCaseDetail seriesCaseDetail : seriesCaseDetails) {
			entrustCaseService.updateSeriesStatus(seriesCaseDetail.getCaseId(), Constant.CASE_IS_SERIESED,
					seriesCaseDetail.getCreateDate(), seriesCaseDetail.getCreateUser());
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.series.SeriesService#querySeries(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.util.Map)
	 */
	@Override
	public DataResponse<SeriesData> querySeries(PageView pageView, Map<String, Object> paraMap) throws Exception {
		PageView seriesInfoListPageView = seriesInfoListService.query(pageView, paraMap);
		return seriesInfoListPageView.getDataResponse();
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.series.SeriesService#getSeriesDetail(java.lang.String)
	 */
	@Override
	public SeriesData getSeriesDetail(String seriesInfoId) throws Exception {
		SeriesCaseInfo seriesCaseInfo = seriesCaseInfoMapper.getById(seriesInfoId);
		SeriesCaseDetail para = new SeriesCaseDetail();
		para.setSeriesCaseId(seriesInfoId);
		List<SeriesCaseDetail> seriesCaseDetails = seriesCaseDetailMapper.queryAll(para);
		SeriesData seriesData = new SeriesData();
		seriesData.setSeriesCaseInfo(seriesCaseInfo);
		seriesData.setSeriesCaseDetails(seriesCaseDetails);
		seriesData.setCaseCount(seriesCaseDetails.size());
		List<CaseInfoInSeries> caseInfos = new ArrayList<CaseInfoInSeries>();
		for (SeriesCaseDetail seriesCaseDetail : seriesCaseDetails) {
			String entrustNo = seriesCaseDetail.getEntrustNo();
			String caseId = seriesCaseDetail.getCaseId();
			//根据委托号查询委托基本信息
			EntrustBaseInfo entrustInfo = entrustBaseMapper.getById(entrustNo);
			//根据委托号查询案件信息
			EntrustCaseInfo caseInfo = entrustCaseMapper.getByEntrustNo(entrustNo);
			CaseInfoInSeries caseInfoInSeri = new CaseInfoInSeries();
			caseInfoInSeri.setEntrustInfo(entrustInfo);
			caseInfoInSeri.setCaseInfo(caseInfo);
			//根据案件ID查询串并依据-支付宝
			SeriesCaseAlipay paraAlipay = new SeriesCaseAlipay();
			paraAlipay.setEntrustCaseId(caseId);
			List<SeriesCaseAlipay> clueAlipays = seriesAlipayMapper.queryAll(paraAlipay);
			caseInfoInSeri.setClueAlipays(clueAlipays);
			//根据案件ID查询串并依据-电话号码
			SeriesCaseTelephone paraTelephone = new SeriesCaseTelephone();
			paraAlipay.setEntrustCaseId(caseId);
			List<SeriesCaseTelephone> clueTelephones = seriesTelephoneMapper.queryAll(paraTelephone);
			caseInfoInSeri.setClueTelephones(clueTelephones);
			//根据案件ID查询串并依据-身份证号
			SeriesCaseCertificate paraCertificate = new SeriesCaseCertificate();
			paraAlipay.setEntrustCaseId(caseId);
			List<SeriesCaseCertificate> clueCertificates = seriesCertificateMapper.queryAll(paraCertificate);
			caseInfoInSeri.setClueCertificates(clueCertificates);
			//根据案件ID查询串并依据-银行卡号
			SeriesCaseBankcard paraBankcard = new SeriesCaseBankcard();
			paraAlipay.setEntrustCaseId(caseId);
			List<SeriesCaseBankcard> clueBankcards = seriesBankcardMapper.queryAll(paraBankcard);
			caseInfoInSeri.setClueBankcards(clueBankcards);
			caseInfos.add(caseInfoInSeri);
		}
		seriesData.setCaseInfos(caseInfos);
		return seriesData;
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.series.SeriesService#queryCaseInSeri(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.util.Map)
	 */
	@Override
	public DataResponse<CaseInfoInSeries> queryCaseInSeri(PageView pageView, Map<String, Object> paraMap)
			throws Exception {
		PageView seriesInfoListPageView = seriesDetailCaseListService.query(pageView, paraMap);
		return seriesInfoListPageView.getDataResponse();
	}

}

/**
 * 
 */
package com.hisign.publicsafety.service.impl.series;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.entity.SeriesCaseInfo;
import com.hisign.publicsafety.mapper.series.SeriesCaseDetailMapper;
import com.hisign.publicsafety.mapper.series.SeriesCaseInfoMapper;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.service.series.SeriesCaseInfoService;

/**
 * @author chailiangzhi
 * 串并案信息服务实现
 */
@Transactional
@Service("seriesCaseInfoService")
public class SeriesCaseInfoServiceImpl extends GeneralService<SeriesCaseInfo> implements SeriesCaseInfoService {
	@Autowired
	private SeriesCaseInfoMapper seriesCaseInfoMapper;
	@Autowired
	private SeriesCaseDetailMapper seriesCaseDetailMapper;

	/** 
	 * 删除串并案
	 */
	@Override
	public void delete(String id) throws Exception {
		seriesCaseInfoMapper.delete(id);
		seriesCaseDetailMapper.deleteBySeriesId(id);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.series.SeriesCaseInfoService#deleteBatch(java.util.List)
	 */
	@Override
	public void deleteBatch(List<String> idList) throws Exception {
		for (String id : idList) {
			delete(id);
		}

	}

}

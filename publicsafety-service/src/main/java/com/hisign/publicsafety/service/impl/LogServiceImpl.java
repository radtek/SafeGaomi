package com.hisign.publicsafety.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.Log;
import com.hisign.publicsafety.mapper.LogMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.LogService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * 日志管理service实现类
 * @author hotdog
 * 上午11:28:24
 */
@Transactional
@Service("logService")
public class LogServiceImpl extends GeneralService<Log> implements LogService {
	@Autowired
	private LogMapper logMapper;

	public PageView query(PageView pageView, Log log) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", log);
		
		int count = logMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		
		List<Log> list = logMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	public void add(Log log) throws Exception {
		logMapper.add(log);
	}

	public void delete(String id) throws Exception {
		logMapper.delete(id);
	}

	public Log getById(String id) {
		return logMapper.getById(id);
	}

	public void update(Log log) throws Exception {
		logMapper.update(log);
	}

	public List<Log> queryAll(Log log) {
		return logMapper.queryAll(log);
	}

}

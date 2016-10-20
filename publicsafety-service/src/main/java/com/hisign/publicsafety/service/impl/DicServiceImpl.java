package com.hisign.publicsafety.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.SystemCaches;
import com.hisign.publicsafety.entity.Dic;
import com.hisign.publicsafety.mapper.DicMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.DicService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * 字典管理service实现类
 * @author zhao
 * 上午11:24:43
 */
@Transactional
@Service("dicService")
public class DicServiceImpl extends GeneralService<Dic> implements DicService {
	@Autowired
	private DicMapper dicMapper;
	@Autowired
	private SystemCaches caches;

	public PageView query(PageView pageView, Dic dic) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", dic);
		
		int count = dicMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		
		List<Dic> list = dicMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	public void add(Dic dic) throws Exception {
		dicMapper.add(dic);
	}

	public void delete(String id) throws Exception {
		
		Dic dicTemp = new Dic();
		dicTemp.setId(Integer.parseInt(id));
		List<Dic> resList = this.queryAll(dicTemp);
		if(resList != null){
			Dic resDic = resList.get(0);
			String dicKey = resDic.getDicKey();
			//级联删除子字典
			deleteChildren(dicKey);
		}
		
		dicMapper.delete(id);
	}

	private void deleteChildren (String dicParentKey) throws Exception{
		Dic dic = new Dic();
		dic.setDicParentKey(dicParentKey);
		List<Dic> tempList = this.queryAll(dic);
		if (tempList != null && tempList.size() > 0) {
			for (Dic d : tempList) {
				this.delete(d.getId().toString());
				deleteChildren(d.getDicKey());
			}
		}
		
	}
	
	public Dic getById(String id) {
		return dicMapper.getById(id);
	}

	public void update(Dic dic) throws Exception {
		dicMapper.update(dic);
	}

	public List<Dic> queryAll(Dic dic) {
		return dicMapper.queryAll(dic);
	}

	public Dic isExist(Dic dic) {
		return dicMapper.isExist(dic);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.DicService#getUnitJson()
	 */
	@Override
	public String getUnitJson() throws Exception {
		Object result = caches.getUnitCache().get("unitJson", new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				Dic d = new Dic();
				d.setDicTypeId(10);
				List<Dic> childDicList = queryAll(d);
				JSONArray dicJsonArr = new JSONArray();
				if (childDicList != null && childDicList.size() > 0) {
					getGxsdmByDic(childDicList, "GXSDM", 1, dicJsonArr);
				}
				return dicJsonArr.toString();
			}
		});
		return String.valueOf(result);
	}

	/**
	 * 遍历递归查询管辖所代码
	 */
	private void getGxsdmByDic(List<Dic> dicList, String dicParentKey, int dicLevel, JSONArray dicJsonArr) {
		for (Dic d : dicList) {
			if (Integer.parseInt(d.getDicLevel()) == dicLevel && d.getDicParentKey().equals(dicParentKey)) {
				JSONObject jobj = new JSONObject();
				jobj.put("dictKey", d.getDicKey());
				jobj.put("dictName", d.getDicName());
				jobj.put("dicParentKey", d.getDicParentKey());
				JSONArray childrenDicJsonArr = new JSONArray();
				getGxsdmByDic(dicList, d.getDicKey(), Integer.parseInt(d.getDicLevel()) + 1, childrenDicJsonArr);
				if (childrenDicJsonArr.size() > 0) {
					jobj.put("childrenFlag", "1");
				} else {
					jobj.put("childrenFlag", "0");
				}
				jobj.put("childrenDicJsonArr", childrenDicJsonArr);
				dicJsonArr.add(jobj);
			}
		}
	}

}

package com.hisign.publicsafety.pulgin.mybatis.plugin;
import java.util.List;

import ognl.Ognl;
import ognl.OgnlException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 分页模型
 * @param <T>
 */
public class PageView {
	private static final Logger logger = LoggerFactory.getLogger(PageView.class);
	
	@SuppressWarnings("rawtypes")
	private DataResponse dataResponse=new DataResponse();

	/**
	 * 每页显示几条记录
	 */
	private int pageSize = 10;


	/**
	 * 从第几条记录开始
	 */
	private int startPage;

	/**
	 * 规定显示5个页码
	 */
	private int pagecode = 10;

	public PageView() {
	}

	/**
	 * 要获得记录的开始索引　即　开始页码
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (this.dataResponse.getPage() - 1) * this.pageSize;
	}

	public int getPagecode() {
		return pagecode;
	}

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}

	/**
	 * 使用构造函数，，强制必需输入 每页显示数量　和　当前页
	 * 
	 * @param pageSize
	 *            　　每页显示数量
	 * @param pageNow
	 *            　当前页
	 */
	public PageView(int pageSize, int pageNow) {
		this.pageSize = pageSize;
		this.dataResponse.setPage(pageNow);
		startPage = (this.dataResponse.getPage() - 1) * this.pageSize;
	}

	/**
	 * 使用构造函数，，强制必需输入 当前页
	 * 
	 * @param pageNow
	 *            　当前页
	 */
	public PageView(int pageNow) {
		this.dataResponse.setPage(pageNow);
		startPage = (this.dataResponse.getPage() - 1) * this.pageSize;
	}

	/**
	 * 查询结果方法 把　记录数　结果集合　放入到　PageView对象
	 * 
	 * @param rowCount
	 *            总记录数
	 * @param records
	 *            结果集合
	 */

	public void setQueryResult(long rowCount, List records) {
		setRowCount(rowCount);
		setRecords(records);
	}

	public void setRowCount(long rowCount) {
		this.dataResponse.setRecords((int)rowCount);
		setPageCount(this.dataResponse.getRecords() % this.pageSize == 0 ? this.dataResponse.getRecords() / this.pageSize : this.dataResponse.getRecords() / this.pageSize + 1);
	}

	public List getRecords() {
		return this.dataResponse.getRows();
	}

	public void setRecords(List records) {
		for (int i = 0; i < records.size(); i++) {
			Object obj = records.get(i);
			try {
				Ognl.setValue("no", obj, startPage + i+1);
			} catch (OgnlException e) {
				logger.warn(e.toString());
			}
		}
		this.dataResponse.setRows(records);
	}

	public int getPageNow() {
		return this.dataResponse.getPage();
	}

	public void setPageNow(int pageNow) {
		this.dataResponse.setPage(pageNow);
	}

	public long getPageCount() {
		return this.dataResponse.getTotal();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getRowCount() {
		return this.dataResponse.getRecords();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public void setPageCount(long pageCount) {
		this.dataResponse.setTotal((int)pageCount);
	}

	/**
	 * @return the dataResponse
	 */
	public DataResponse getDataResponse() {
		return dataResponse;
	}

	/**
	 * @param dataResponse the dataResponse to set
	 */
	public void setDataResponse(DataResponse dataResponse) {
		this.dataResponse = dataResponse;
	}

	
}

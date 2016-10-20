package com.hisign.publicsafety.pulgin.mybatis.plugin;
import java.util.List;
import org.apache.poi.ss.formula.functions.T;
/**
 * JGRID 表格请求返回模型
 * @author fankai
 * @date 2015-8-7
 * 
 */
@SuppressWarnings("hiding")
public class DataResponse<T> {
	 //需要显示的数据集  
	    private List<T> rows;  
	  
	    //当前页码
	    private int page = 1;  
	      
	    //数据总数  
	    private int records;  
	      
	    //可显示的页数  
	    private int total;  

		public List<T> getRows() {
			return rows;
		}

		public void setRows(List<T> rows) {
			this.rows = rows;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getRecords() {
			return records;
		}

		public void setRecords(int records) {
			this.records = records;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

}

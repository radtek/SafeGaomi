/**
 * 
 */
package com.hisign.publicsafety.db.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.hisign.publicsafety.util.DateUtils;
import com.hisign.publicsafety.util.DateUtils.DateFormat;

/**
 * @author chailiangzhi
 * @date 2015-8-20
 * 自定义系统内字符串形式的日期与数据库Timestamp类型的转换
 */
public class CustomDateStrHandler extends BaseTypeHandler<String> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		if (parameter == null || parameter.trim().equals("")) {
			ps.setNull(i, jdbcType.TYPE_CODE);
		} else {
			Date date = DateUtils.string2Datetime(parameter, DateUtils.YMDHM);
			Timestamp ts = new Timestamp(date.getTime());
			ps.setTimestamp(i, ts);
		}

	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp sqlTimestamp = rs.getTimestamp(columnName);
		if (sqlTimestamp != null) {
			return DateUtils.convet2String(sqlTimestamp.getTime(), DateFormat.YMDHM);
		}
		return null;
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp sqlTimestamp = rs.getTimestamp(columnIndex);
		if (sqlTimestamp != null) {
			return DateUtils.convet2String(sqlTimestamp.getTime(), DateFormat.YMDHM);
		}
		return null;
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp sqlTimestamp = cs.getTimestamp(columnIndex);
		if (sqlTimestamp != null) {
			return DateUtils.convet2String(sqlTimestamp.getTime(), DateFormat.YMDHM);
		}
		return null;
	}
}

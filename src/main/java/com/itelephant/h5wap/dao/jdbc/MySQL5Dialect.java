package com.itelephant.h5wap.dao.jdbc;

/**
 * MySQL 方言
 * 
 * @author liuxiaoyu
 * 
 */
public class MySQL5Dialect extends Dialect {

	public boolean supportsLimit() {
		return true;
	}

	public boolean supportsLimitOffset() {
		return true;
	}

	public String getLimitString(String sql, int offset,
			String offsetPlaceholder, int limit, String limitPlaceholder) {

		sql = sql.trim();
		return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
	}

}

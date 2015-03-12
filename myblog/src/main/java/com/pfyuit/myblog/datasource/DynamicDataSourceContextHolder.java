package com.pfyuit.myblog.datasource;

import org.springframework.util.Assert;

public class DynamicDataSourceContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public static final String DATA_SOURCE_MASTER_TYPE = "MASTER";
	public static final String DATA_SOURCE_SLAVE_TYPE = "SLAVE";

	public static void setDataSource(String dataSource) {
		Assert.notNull(dataSource, "dataSource cannot be null");
		contextHolder.set(dataSource);
	}

	public static String getDataSource() {
		return (String) contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}
}

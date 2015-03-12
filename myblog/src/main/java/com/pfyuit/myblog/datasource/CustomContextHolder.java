package com.pfyuit.myblog.datasource;

import org.springframework.util.Assert;

public class CustomContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public static final String CUSTOM_MASTER_TYPE = "MASTER";
	public static final String CUSTOM_SLAVE_TYPE = "SLAVE";

	public static void setCustomType(String customerType) {
		Assert.notNull(customerType, "customerType cannot be null");
		contextHolder.set(customerType);
	}

	public static String getCustomType() {
		return (String) contextHolder.get();
	}

	public static void clearCustomType() {
		contextHolder.remove();
	}
}

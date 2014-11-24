package com.pfyuit.myblog.search;

import org.springframework.stereotype.Component;

@Component
public class SolrConfig {

	private String url;
	private String soTimeout;
	private String connectionTimeout;
	private String defaultMaxConnectionsPerHost;
	private String maxTotalConnections;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(String soTimeout) {
		this.soTimeout = soTimeout;
	}

	public String getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public String getDefaultMaxConnectionsPerHost() {
		return defaultMaxConnectionsPerHost;
	}

	public void setDefaultMaxConnectionsPerHost(String defaultMaxConnectionsPerHost) {
		this.defaultMaxConnectionsPerHost = defaultMaxConnectionsPerHost;
	}

	public String getMaxTotalConnections() {
		return maxTotalConnections;
	}

	public void setMaxTotalConnections(String maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}

}

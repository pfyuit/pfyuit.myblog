package com.pfyuit.myblog.search;

import org.apache.solr.client.solrj.beans.Field;

public class SolrItem {

	@Field
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

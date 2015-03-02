package com.pfyuit.myblog.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Link {

	@Id
	private int linkid;

	private String name;

	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

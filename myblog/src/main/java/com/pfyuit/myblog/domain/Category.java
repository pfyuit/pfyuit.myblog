package com.pfyuit.myblog.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Lazy;

@Entity
public class Category {

	@Id
	private int categoryid;
	
	private String name;
	
	private Date createTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

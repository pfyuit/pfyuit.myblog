package com.pfyuit.myblog.dto;

public class ArchiveDto {

	private String month;
	private int blogCount;

	public ArchiveDto(String month, int blogCount) {
		super();
		this.month = month;
		this.blogCount = blogCount;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(int blogCount) {
		this.blogCount = blogCount;
	}

}

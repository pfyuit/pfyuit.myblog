package com.pfyuit.myblog.dto;

import com.pfyuit.myblog.domain.Category;

public class CategoryDto extends Category {

	private int blogCount;

	public int getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(int blogCount) {
		this.blogCount = blogCount;
	}

}

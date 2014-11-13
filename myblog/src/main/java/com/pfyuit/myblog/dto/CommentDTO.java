package com.pfyuit.myblog.dto;

import com.pfyuit.myblog.domain.Comment;

public class CommentDTO extends Comment {

	private int blogid;

	public int getBlogid() {
		return blogid;
	}

	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}

}

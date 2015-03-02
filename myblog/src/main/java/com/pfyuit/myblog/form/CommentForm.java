package com.pfyuit.myblog.form;

import com.pfyuit.myblog.domain.Comment;

public class CommentForm extends Comment {

	private int blogid;

	public int getBlogid() {
		return blogid;
	}

	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}

}

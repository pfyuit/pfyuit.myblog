package com.pfyuit.myblog.service;

import java.util.List;

import com.pfyuit.myblog.domain.Comment;
import com.pfyuit.myblog.form.CommentForm;

public interface CommentService {

	public abstract void save(CommentForm commentForm);

	public abstract void delete(Comment Comment);

	public abstract void update(Comment Comment);

	public abstract List<Comment> findAll();

}
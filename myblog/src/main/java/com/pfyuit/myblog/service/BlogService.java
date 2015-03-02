package com.pfyuit.myblog.service;

import java.util.List;

import com.pfyuit.myblog.domain.Blog;
import com.pfyuit.myblog.form.BlogForm;

public interface BlogService {

	public abstract void save(BlogForm blogForm);

	public abstract void delete(Blog blog);

	public abstract void update(BlogForm blogForm);

	public abstract void update(Blog blog);

	public abstract Blog find(int id);

	public abstract List<Blog> findAll();

}
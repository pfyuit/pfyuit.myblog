package com.pfyuit.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfyuit.myblog.dao.BlogDAO;
import com.pfyuit.myblog.domain.Blog;

@Service
public class BlogService {

	@Autowired
	private BlogDAO blogDAO;

	public void save(Blog blog) {
		blogDAO.save(blog);
	}

	public void delete(Blog blog) {
		blogDAO.delete(blog);
	}

	public void update(Blog blog) {
		blogDAO.update(blog);
	}
	
	public Blog find(int id){
		return blogDAO.find(id);
	}

	public List<Blog> findAll() {
		return blogDAO.findAll();
	}
}

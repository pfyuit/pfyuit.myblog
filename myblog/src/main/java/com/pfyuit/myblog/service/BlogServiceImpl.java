package com.pfyuit.myblog.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfyuit.myblog.dao.BlogDao;
import com.pfyuit.myblog.domain.Blog;
import com.pfyuit.myblog.domain.Category;
import com.pfyuit.myblog.form.BlogForm;

@Service
@Transactional(rollbackFor = Exception.class)
public class BlogServiceImpl implements BlogService {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BlogDao blogDAO;

	@Override
	public void save(BlogForm blogForm) {
		Blog blog = new Blog();
		blog.setAuthor(blogForm.getAuthor());
		Category category = categoryService.findByName(blogForm.getCategoryName());
		blog.setCategory(category);
		blog.setContent(blogForm.getContent());
		blog.setOriginal(blogForm.isOriginal());
		blog.setTitle(blogForm.getTitle());
		blog.setCreateDate(new Timestamp(new Date().getTime()));
		blog.setReadCount(new Long(0).longValue());
		blogDAO.save(blog);
	}

	@Override
	public void delete(Blog blog) {
		blogDAO.delete(blog);
	}

	@Override
	public void update(BlogForm blogForm) {
		Blog blog = blogDAO.find(blogForm.getBlogid());
		blog.setAuthor(blogForm.getAuthor());
		Category category = categoryService.findByName(blogForm.getCategoryName());
		blog.setCategory(category);
		blog.setContent(blogForm.getContent());
		blog.setOriginal(blogForm.isOriginal());
		blog.setTitle(blogForm.getTitle());
		blog.setLastModified(new Timestamp(new Date().getTime()));
		blogDAO.update(blog);
	}
	
	@Override
	public void update(Blog blog) {
		blogDAO.update(blog);
	}

	@Override
	public Blog find(int id) {
		return blogDAO.find(id);
	}

	@Override
	public List<Blog> findAll() {
		return blogDAO.findAll();
	}
}

package com.pfyuit.myblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pfyuit.myblog.domain.Blog;
import com.pfyuit.myblog.dto.BlogDto;
import com.pfyuit.myblog.dto.builder.BlogDtoBuilder;
import com.pfyuit.myblog.form.BlogForm;
import com.pfyuit.myblog.service.BlogService;
import com.pfyuit.myblog.service.CategoryService;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/create")
	public ModelAndView createBlog(BlogForm blogForm) {
		blogService.save(blogForm);

		List<Blog> blogs = blogService.findAll();
		List<BlogDto> blogDtos = BlogDtoBuilder.buildBlogDtos(blogs);

		ModelAndView mav = new ModelAndView();
		mav.addObject("blogs", blogDtos);
		mav.addObject("message", "Blog Saved Succesfully");
		mav.setViewName("/business/content");
		return mav;
	}

	@RequestMapping("/update")
	public ModelAndView updateBlog(BlogForm blogForm) {
		blogService.update(blogForm);

		List<Blog> blogs = blogService.findAll();
		List<BlogDto> blogDtos = BlogDtoBuilder.buildBlogDtos(blogs);

		ModelAndView mav = new ModelAndView();
		mav.addObject("blogs", blogDtos);
		mav.addObject("message", "Blog Updated Succesfully");
		mav.setViewName("/business/content");
		return mav;
	}

}

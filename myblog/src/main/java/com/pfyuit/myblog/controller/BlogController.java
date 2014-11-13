package com.pfyuit.myblog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pfyuit.myblog.domain.Blog;
import com.pfyuit.myblog.domain.Category;
import com.pfyuit.myblog.dto.BlogDto;
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
	public ModelAndView createBlog(BlogDto blog) {
		Blog blogPO = new Blog();
		blogPO.setAuthor(blog.getAuthor());
		Category category = categoryService.findByName(blog.getCategoryName());
		blogPO.setCategory(category);
		blogPO.setContent(blog.getContent());
		blogPO.setOriginal(blog.isOriginal());
		blogPO.setTitle(blog.getTitle());
		blogPO.setCreateDate(new Date());
		blogPO.setReadCount(new Long(0).longValue());
		blogService.save(blogPO);

		List<Blog> blogs = blogService.findAll();
		List<BlogDto> blogDtos = new ArrayList<BlogDto>();
		for (Blog b : blogs) {
			BlogDto blogDto = new BlogDto();
			blogDto.setBlogid(b.getBlogid());
			blogDto.setAuthor(b.getAuthor());
			blogDto.setCategory(b.getCategory());
			blogDto.setComments(b.getComments());
			blogDto.setContent(b.getContent());
			blogDto.setCreateDate(b.getCreateDate());
			blogDto.setLastModified(b.getLastModified());
			blogDto.setOriginal(b.isOriginal());
			blogDto.setReadCount(b.getReadCount() == 0 ? 0 : b.getReadCount());
			blogDto.setTitle(b.getTitle());
			blogDto.setCommentCount(blogDto.getComments().size() == 0 ? "0" : String.valueOf(blogDto.getComments().size()));
			blogDto.setContentAbstract(PageController.getAbstract(blogDto.getContent()));
			blogDtos.add(blogDto);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("blogs", blogDtos);
		mav.addObject("message", "Blog Saved Succesfully");
		mav.setViewName("/business/content");
		return mav;
	}

	@RequestMapping("/update")
	public ModelAndView updateBlog(BlogDto blog) {
		Blog blogPO = blogService.find(blog.getBlogid());
		blogPO.setAuthor(blog.getAuthor());
		Category category = categoryService.findByName(blog.getCategoryName());
		blogPO.setCategory(category);
		blogPO.setContent(blog.getContent());
		blogPO.setOriginal(blog.isOriginal());
		blogPO.setTitle(blog.getTitle());
		blogPO.setLastModified(new Date());
		blogService.update(blogPO);

		List<Blog> blogs = blogService.findAll();
		List<BlogDto> blogDtos = new ArrayList<BlogDto>();
		for (Blog b : blogs) {
			BlogDto blogDto = new BlogDto();
			blogDto.setBlogid(b.getBlogid());
			blogDto.setAuthor(b.getAuthor());
			blogDto.setCategory(b.getCategory());
			blogDto.setComments(b.getComments());
			blogDto.setContent(b.getContent());
			blogDto.setCreateDate(b.getCreateDate());
			blogDto.setLastModified(b.getLastModified());
			blogDto.setOriginal(b.isOriginal());
			blogDto.setReadCount(b.getReadCount() == 0 ? 0 : b.getReadCount());
			blogDto.setTitle(b.getTitle());
			blogDto.setCommentCount(blogDto.getComments().size() == 0 ? "0" : String.valueOf(blogDto.getComments().size()));
			blogDto.setContentAbstract(PageController.getAbstract(blogDto.getContent()));
			blogDtos.add(blogDto);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("blogs", blogDtos);
		mav.addObject("message", "Blog Updated Succesfully");
		mav.setViewName("/business/content");
		return mav;
	}

	@RequestMapping("/view")
	public ModelAndView viewBlog(@RequestParam String blogid) {
		Blog blog = blogService.find(Integer.parseInt(blogid));
		blog.setReadCount(blog.getReadCount() + 1);
		blogService.update(blog);

		BlogDto blogDto = new BlogDto();
		blogDto.setBlogid(blog.getBlogid());
		blogDto.setAuthor(blog.getAuthor());
		blogDto.setCategory(blog.getCategory());
		blogDto.setComments(blog.getComments());
		blogDto.setContent(blog.getContent());
		blogDto.setCreateDate(blog.getCreateDate());
		blogDto.setLastModified(blog.getLastModified());
		blogDto.setOriginal(blog.isOriginal());
		blogDto.setReadCount(blog.getReadCount() == 0 ? 0 : blog.getReadCount());
		blogDto.setTitle(blog.getTitle());
		blogDto.setCommentCount(blogDto.getComments().size() == 0 ? "0" : String.valueOf(blogDto.getComments().size()));

		ModelAndView view = new ModelAndView();
		view.addObject("blog", blogDto);
		view.setViewName("/business/viewblog");
		return view;
	}

}

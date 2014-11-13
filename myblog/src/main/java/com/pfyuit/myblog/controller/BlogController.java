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
import com.pfyuit.myblog.dto.BlogDTO;
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
	public ModelAndView newblog(BlogDTO blog) {
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
		List<BlogDTO> blogDTOs = new ArrayList<BlogDTO>();
		for (Blog b : blogs) {
			BlogDTO blogDTO = new BlogDTO();
			blogDTO.setBlogid(b.getBlogid());
			blogDTO.setAuthor(b.getAuthor());
			blogDTO.setCategory(b.getCategory());
			blogDTO.setComments(b.getComments());
			blogDTO.setContent(b.getContent());
			blogDTO.setCreateDate(b.getCreateDate());
			blogDTO.setLastModified(b.getLastModified());
			blogDTO.setOriginal(b.isOriginal());
			blogDTO.setReadCount(b.getReadCount() == 0 ? 0 : b.getReadCount());
			blogDTO.setTitle(b.getTitle());
			blogDTO.setCommentCount(blogDTO.getComments().size() == 0 ? "0" : String.valueOf(blogDTO.getComments().size()));
			blogDTO.setContentAbstract(IndexController.getAbstract(blogDTO.getContent()));
			blogDTOs.add(blogDTO);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("blogs", blogDTOs);
		mav.addObject("message", "Blog saved succesfully");
		mav.setViewName("/business/content");
		return mav;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(BlogDTO blog) {
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
		List<BlogDTO> blogDTOs = new ArrayList<BlogDTO>();
		for (Blog b : blogs) {
			BlogDTO blogDTO = new BlogDTO();
			blogDTO.setBlogid(b.getBlogid());
			blogDTO.setAuthor(b.getAuthor());
			blogDTO.setCategory(b.getCategory());
			blogDTO.setComments(b.getComments());
			blogDTO.setContent(b.getContent());
			blogDTO.setCreateDate(b.getCreateDate());
			blogDTO.setLastModified(b.getLastModified());
			blogDTO.setOriginal(b.isOriginal());
			blogDTO.setReadCount(b.getReadCount() == 0 ? 0 : b.getReadCount());
			blogDTO.setTitle(b.getTitle());
			blogDTO.setCommentCount(blogDTO.getComments().size() == 0 ? "0" : String.valueOf(blogDTO.getComments().size()));
			blogDTO.setContentAbstract(IndexController.getAbstract(blogDTO.getContent()));
			blogDTOs.add(blogDTO);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("blogs", blogDTOs);
		mav.addObject("message", "Blog updated succesfully");
		mav.setViewName("/business/content");
		return mav;
	}
	
	@RequestMapping("/view")
	public ModelAndView viewblog(@RequestParam String blogid) {
		Blog blog = blogService.find(Integer.parseInt(blogid));	
		blog.setReadCount(blog.getReadCount()+1);
		blogService.update(blog);
		
		BlogDTO blogDTO = new BlogDTO();
		blogDTO.setBlogid(blog.getBlogid());
		blogDTO.setAuthor(blog.getAuthor());
		blogDTO.setCategory(blog.getCategory());
		blogDTO.setComments(blog.getComments());
		blogDTO.setContent(blog.getContent());
		blogDTO.setCreateDate(blog.getCreateDate());
		blogDTO.setLastModified(blog.getLastModified());
		blogDTO.setOriginal(blog.isOriginal());
		blogDTO.setReadCount(blog.getReadCount() == 0 ? 0 : blog.getReadCount());
		blogDTO.setTitle(blog.getTitle());
		blogDTO.setCommentCount(blogDTO.getComments().size() == 0 ? "0" : String.valueOf(blogDTO.getComments().size()));

		ModelAndView view = new ModelAndView();
		view.addObject("blog", blogDTO);
		view.setViewName("/business/viewblog");
		return view;
	}

}

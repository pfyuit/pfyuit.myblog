package com.pfyuit.myblog.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.pfyuit.myblog.service.LinkService;
import com.pfyuit.myblog.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private LinkService linkService;

	@RequestMapping("/index")
	public ModelAndView index() {
		List<Blog> blogs = blogService.findAll();
		List<BlogDTO> blogDTOs = new ArrayList<BlogDTO>();
		for (Blog blog : blogs) {
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
			blogDTO.setContentAbstract(getAbstract(blogDTO.getContent()));
			blogDTOs.add(blogDTO);
		}

		ModelAndView view = new ModelAndView();
		view.addObject("menu", "Home");
		view.addObject("blogs", blogDTOs);
		view.setViewName("/business/content");
		return view;
	}

	public static String getAbstract(String content) {
		String result = "";
		if (content.length() > 500) {
			result = content.substring(0, 500) + "...";
		} else {
			result = content;
		}
		return result;
	}

	@RequestMapping("/alumni")
	public ModelAndView alumni() {
		ModelAndView view = new ModelAndView();
		// TODO
		return view;
	}

	@RequestMapping("/about")
	public ModelAndView about() {
		ModelAndView view = new ModelAndView();
		view.addObject("menu", "About");
		view.setViewName("/business/about");
		return view;
	}

	@RequestMapping("/newblog")
	public ModelAndView newblog() {
		List<Category> categories = categoryService.findAll();

		ModelAndView view = new ModelAndView();
		view.addObject("menu", "NewBlog");
		view.addObject("categories", categories);
		view.setViewName("/business/newblog");
		return view;
	}

	@RequestMapping("/updateblog")
	public ModelAndView updateblog(@RequestParam String blogid) {
		Blog blog = blogService.find(Integer.parseInt(blogid));

		List<Category> categories = categoryService.findAll();

		ModelAndView view = new ModelAndView();
		view.addObject("blog", blog);
		view.addObject("categories", categories);
		view.setViewName("/business/updateblog");
		return view;
	}

	@RequestMapping("/navigator")
	public ModelAndView navigator() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/business/navigator");
		view.addObject("user", userService.findAll().get(0));
		view.addObject("categories", categoryService.findAll());
		view.addObject("archives", Arrays.asList(new String[] { "2014年1月", "2014年2月" }));
		view.addObject("links", linkService.findAll());
		return view;
	}

	@RequestMapping("/content")
	public ModelAndView content() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/business/content");
		return view;
	}

}

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
import com.pfyuit.myblog.dto.BlogDto;
import com.pfyuit.myblog.service.BlogService;
import com.pfyuit.myblog.service.CategoryService;
import com.pfyuit.myblog.service.LinkService;
import com.pfyuit.myblog.service.UserService;

@Controller
public class PageController {

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
		List<BlogDto> blogDtos = new ArrayList<BlogDto>();
		for (Blog blog : blogs) {
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
			blogDto.setContentAbstract(getAbstract(blogDto.getContent()));
			blogDtos.add(blogDto);
		}

		ModelAndView view = new ModelAndView();
		view.addObject("menu", "Home");
		view.addObject("blogs", blogDtos);
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

	@RequestMapping("/about")
	public ModelAndView about() {
		ModelAndView view = new ModelAndView();
		view.addObject("menu", "About");
		view.setViewName("/business/about");
		return view;
	}

	@RequestMapping("/newblog")
	public ModelAndView newBlog() {
		List<Category> categories = categoryService.findAll();

		ModelAndView view = new ModelAndView();
		view.addObject("menu", "NewBlog");
		view.addObject("categories", categories);
		view.setViewName("/business/newblog");
		return view;
	}

	@RequestMapping("/updateblog")
	public ModelAndView updateBlog(@RequestParam String blogid) {
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
		view.addObject("user", userService.findAll().get(0));
		view.addObject("categories", categoryService.findAll());
		view.addObject("archives", Arrays.asList(new String[] { "2014-1", "2014-2" }));//FIXME
		view.addObject("links", linkService.findAll());
		view.setViewName("/business/navigator");
		return view;
	}

	@RequestMapping("/content")
	public ModelAndView content() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/business/content");
		return view;
	}
	
	@RequestMapping("/authen")
	public ModelAndView login(@RequestParam(value="messages",required=false) String messages) {
		ModelAndView view = new ModelAndView();
		if(messages != null && !messages.isEmpty()){
			view.addObject("messages", messages);
		}
		view.setViewName("/business/authen");
		return view;
	}

}

package com.pfyuit.myblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pfyuit.myblog.datasource.DynamicDataSourceContextHolder;
import com.pfyuit.myblog.domain.Blog;
import com.pfyuit.myblog.dto.BlogDto;
import com.pfyuit.myblog.dto.builder.BlogDtoBuilder;
import com.pfyuit.myblog.form.CommentForm;
import com.pfyuit.myblog.service.BlogService;
import com.pfyuit.myblog.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private BlogService blogService;

	@RequestMapping("/create")
	public ModelAndView create(CommentForm commentForm) {
		DynamicDataSourceContextHolder.setDataSource(DynamicDataSourceContextHolder.DATA_SOURCE_MASTER_TYPE);
		commentService.save(commentForm);

		Blog blog = blogService.find(commentForm.getBlogid());
		BlogDto blogDto = BlogDtoBuilder.buildBlogDto(blog);

		ModelAndView view = new ModelAndView();
		view.addObject("blog", blogDto);
		view.setViewName("/business/viewblog");
		return view;
	}

}

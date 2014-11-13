package com.pfyuit.myblog.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pfyuit.myblog.domain.Blog;
import com.pfyuit.myblog.domain.Comment;
import com.pfyuit.myblog.dto.BlogDto;
import com.pfyuit.myblog.dto.CommentDto;
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
	public ModelAndView create(CommentDto comment) {
		Blog blog = blogService.find(comment.getBlogid());
		Comment commentPO = new Comment();
		commentPO.setAuthor(comment.getAuthor());
		commentPO.setBlog(blog);
		commentPO.setContent(comment.getContent());
		commentPO.setCreateTime(new Date());
		commentService.save(commentPO);

		BlogDto blogDto = new BlogDto();
		blog = blogService.find(comment.getBlogid());
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

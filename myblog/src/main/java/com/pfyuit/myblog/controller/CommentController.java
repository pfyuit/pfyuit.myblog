package com.pfyuit.myblog.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pfyuit.myblog.domain.Blog;
import com.pfyuit.myblog.domain.Comment;
import com.pfyuit.myblog.dto.BlogDTO;
import com.pfyuit.myblog.dto.CommentDTO;
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
	public ModelAndView create(CommentDTO comment) {
		Blog blog = blogService.find(comment.getBlogid());
		Comment commentPO = new Comment();
		commentPO.setAuthor(comment.getAuthor());
		commentPO.setBlog(blog);
		commentPO.setContent(comment.getContent());
		commentPO.setCreateTime(new Date());
		commentService.save(commentPO);

		BlogDTO blogDTO = new BlogDTO();
		blog = blogService.find(comment.getBlogid());
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

package com.pfyuit.myblog.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfyuit.myblog.dao.CommentDao;
import com.pfyuit.myblog.domain.Blog;
import com.pfyuit.myblog.domain.Comment;
import com.pfyuit.myblog.form.CommentForm;

@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

	@Autowired
	private BlogService blogService;

	@Autowired
	private CommentDao commentDAO;

	@Override
	public void save(CommentForm commentForm) {
		Blog blog = blogService.find(commentForm.getBlogid());
		Comment comment = new Comment();
		comment.setAuthor(commentForm.getAuthor());
		comment.setBlog(blog);
		comment.setContent(commentForm.getContent());
		comment.setCreateTime(new Timestamp(new Date().getTime()));
		commentDAO.save(comment);
	}

	@Override
	public void delete(Comment Comment) {
		commentDAO.delete(Comment);
	}

	@Override
	public void update(Comment Comment) {
		commentDAO.update(Comment);
	}

	@Override
	public List<Comment> findAll() {
		return commentDAO.findAll();
	}

}

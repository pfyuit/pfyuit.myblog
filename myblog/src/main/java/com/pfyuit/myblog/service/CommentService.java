package com.pfyuit.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfyuit.myblog.dao.CommentDAO;
import com.pfyuit.myblog.domain.Comment;

@Service
public class CommentService {
	
	@Autowired
	private CommentDAO commentDAO;

	public void save(Comment Comment) {
		commentDAO.save(Comment);
	}

	public void delete(Comment Comment) {
		commentDAO.delete(Comment);
	}

	public void update(Comment Comment) {
		commentDAO.update(Comment);
	}

	public List<Comment> findAll() {
		return commentDAO.findAll();
	}

}

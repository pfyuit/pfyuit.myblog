package com.pfyuit.myblog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfyuit.myblog.datasource.CustomContextHolder;
import com.pfyuit.myblog.domain.Comment;

@Repository
public class CommentDao extends BaseDao<Comment> {

	@Autowired
	private SessionFactory sessionFactory;

	public CommentDao() {
		super(Comment.class);
	}

	public List<Comment> findAll(){
		CustomContextHolder.setCustomType(CustomContextHolder.CUSTOM_SLAVE_TYPE);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Comment");
		@SuppressWarnings("unchecked")
		List<Comment> result = query.list();
		return result;
	}
}

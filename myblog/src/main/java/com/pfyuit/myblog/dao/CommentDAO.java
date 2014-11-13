package com.pfyuit.myblog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfyuit.myblog.domain.Comment;

@Repository
public class CommentDAO extends BaseDAO<Comment> {

	@Autowired
	private SessionFactory sessionFactory;

	public CommentDAO() {
		super(Comment.class);
	}

	public List<Comment> findAll(){
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		Query query = session.createQuery("from Comment");
		@SuppressWarnings("unchecked")
		List<Comment> result = query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}
}

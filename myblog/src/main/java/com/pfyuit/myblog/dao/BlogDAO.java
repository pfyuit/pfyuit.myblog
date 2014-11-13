package com.pfyuit.myblog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfyuit.myblog.domain.Blog;

@Repository
public class BlogDAO extends BaseDAO<Blog> {

	@Autowired
	private SessionFactory sessionFactory;

	public BlogDAO() {
		super(Blog.class);
	}

	public List<Blog> findAll(){
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		Query query = session.createQuery("from Blog");
		@SuppressWarnings("unchecked")
		List<Blog> result = query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}
}

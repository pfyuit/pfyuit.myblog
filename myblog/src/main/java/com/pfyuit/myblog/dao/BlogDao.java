package com.pfyuit.myblog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfyuit.myblog.domain.Blog;

@Repository
public class BlogDao extends BaseDao<Blog> {

	@Autowired
	private SessionFactory sessionFactory;

	public BlogDao() {
		super(Blog.class);
	}

	public List<Blog> findAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Blog");
		@SuppressWarnings("unchecked")
		List<Blog> result = query.list();
		return result;
	}
}

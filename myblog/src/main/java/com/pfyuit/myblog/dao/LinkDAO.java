package com.pfyuit.myblog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.pfyuit.myblog.domain.Link;

@Repository
public class LinkDAO extends BaseDAO<Link> {

	@Autowired
	private SessionFactory sessionFactory;

	public LinkDAO() {
		super(Link.class);
	}

	@Cacheable("links")
	public List<Link> findAll(){
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		Query query = session.createQuery("from Link");
		@SuppressWarnings("unchecked")
		List<Link> result = query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}
}

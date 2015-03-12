package com.pfyuit.myblog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.pfyuit.myblog.datasource.CustomContextHolder;
import com.pfyuit.myblog.domain.Link;

@Repository
public class LinkDao extends BaseDao<Link> {

	@Autowired
	private SessionFactory sessionFactory;

	public LinkDao() {
		super(Link.class);
	}

	@Cacheable("links")
	public List<Link> findAll(){
		CustomContextHolder.setCustomType(CustomContextHolder.CUSTOM_SLAVE_TYPE);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Link");
		@SuppressWarnings("unchecked")
		List<Link> result = query.list();
		return result;
	}
}

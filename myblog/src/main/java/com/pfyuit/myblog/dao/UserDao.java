package com.pfyuit.myblog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfyuit.myblog.datasource.CustomContextHolder;
import com.pfyuit.myblog.domain.User;

@Repository
public class UserDao extends BaseDao<User> {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDao() {
		super(User.class);
	}

	public List<User> findAll() {
		CustomContextHolder.setCustomType(CustomContextHolder.CUSTOM_SLAVE_TYPE);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> result = query.list();
		return result;
	}
}

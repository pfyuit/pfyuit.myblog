package com.pfyuit.myblog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfyuit.myblog.domain.User;

@Repository
public class UserDAO extends BaseDAO<User> {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDAO() {
		super(User.class);
	}

	public List<User> findAll() {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		Query query = session.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> result = query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}
}

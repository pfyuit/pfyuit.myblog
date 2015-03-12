package com.pfyuit.myblog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao<T> {

	private Class<T> type;

	@Autowired
	private SessionFactory sessionFactory;

	public BaseDao() {
	}

	public BaseDao(Class<T> type) {
		this.type = type;
	}

	public void save(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.save(t);
	}

	public void delete(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(t);
	}

	public void update(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.update(t);
	}

	@SuppressWarnings("unchecked")
	public T find(String id) {
		Session session = sessionFactory.getCurrentSession();
		T result = (T) session.get(type, id);
		return result;
	}

	@SuppressWarnings("unchecked")
	public T find(int id) {
		Session session = sessionFactory.getCurrentSession();
		T result = (T) session.get(type, id);
		return result;
	}

}

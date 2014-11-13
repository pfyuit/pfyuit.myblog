package com.pfyuit.myblog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDAO<T> {

	private Class<T> type;

	@Autowired
	private SessionFactory sessionFactory;
	
	public BaseDAO() {}

	public BaseDAO(Class<T> type) {
		this.type = type;
	}

	public void save(T t) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.save(t);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(T t) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.delete(t);
		session.getTransaction().commit();
		session.close();
	}

	public void update(T t) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.update(t);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public T find(String id) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		T result = (T) session.get(type, id);
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public T find(int id) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		T result = (T) session.get(type, id);
		session.getTransaction().commit();
		session.close();
		return result;
	}

}

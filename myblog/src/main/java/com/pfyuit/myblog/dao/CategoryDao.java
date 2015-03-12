package com.pfyuit.myblog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.pfyuit.myblog.datasource.CustomContextHolder;
import com.pfyuit.myblog.domain.Category;

@Repository
public class CategoryDao extends BaseDao<Category> {

	@Autowired
	private SessionFactory sessionFactory;

	public CategoryDao() {
		super(Category.class);
	}

	@SuppressWarnings("unchecked")
	@Cacheable("categoryByName")
	public Category findByName(String categoryName) {
		CustomContextHolder.setCustomType(CustomContextHolder.CUSTOM_SLAVE_TYPE);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Category where name=?");
		query.setString(0, categoryName);
		List<Category> list = query.list();
		Category category = list.get(0);
		return category;
	}

	@Cacheable("categories")
	public List<Category> findAll() {
		CustomContextHolder.setCustomType(CustomContextHolder.CUSTOM_SLAVE_TYPE);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Category");
		@SuppressWarnings("unchecked")
		List<Category> result = query.list();
		return result;
	}

}

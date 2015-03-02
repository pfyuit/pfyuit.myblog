package com.pfyuit.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfyuit.myblog.dao.CategoryDao;
import com.pfyuit.myblog.domain.Category;

@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDAO;

	@Override
	public void save(Category Category) {
		categoryDAO.save(Category);
	}

	@Override
	public void delete(Category Category) {
		categoryDAO.delete(Category);
	}

	@Override
	public void update(Category Category) {
		categoryDAO.update(Category);
	}
	
	@Override
	public Category find(int id){
		return categoryDAO.find(id);
	}
	
	@Override
	public Category findByName(String categoryName){
		return categoryDAO.findByName(categoryName);
	}

	@Override
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}

}

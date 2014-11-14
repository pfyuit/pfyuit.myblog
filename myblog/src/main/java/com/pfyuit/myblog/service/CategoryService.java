package com.pfyuit.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfyuit.myblog.dao.CategoryDAO;
import com.pfyuit.myblog.domain.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDAO categoryDAO;

	public void save(Category Category) {
		categoryDAO.save(Category);
	}

	public void delete(Category Category) {
		categoryDAO.delete(Category);
	}

	public void update(Category Category) {
		categoryDAO.update(Category);
	}
	
	public Category find(int id){
		return categoryDAO.find(id);
	}
	
	public Category findByName(String categoryName){
		return categoryDAO.findByName(categoryName);
	}

	public List<Category> findAll() {
		return categoryDAO.findAll();
	}

}

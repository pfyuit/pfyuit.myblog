package com.pfyuit.myblog.service;

import java.util.List;

import com.pfyuit.myblog.domain.Category;

public interface CategoryService {

	public abstract void save(Category Category);

	public abstract void delete(Category Category);

	public abstract void update(Category Category);

	public abstract Category find(int id);

	public abstract Category findByName(String categoryName);

	public abstract List<Category> findAll();

}
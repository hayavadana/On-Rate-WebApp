package com.hayavadana.rating.dao;

import java.util.List;

import com.hayavadana.rating.model.Category;

public interface CategoryDao {
	public List<Category> getAllCategories();
	public Category getCategory(String catCode);
}

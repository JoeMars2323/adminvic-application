package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.CategoryRest;

public interface CategoryService {

	CategoryRest getCategoryById(Long id) throws AdminVicException;

	List<CategoryRest> getAllCategories() throws AdminVicException;

	CategoryRest createCategory(CategoryRest actorRest) throws AdminVicException;

	CategoryRest updateCategory(CategoryRest actorRest) throws AdminVicException;

	CategoryRest deleteCategory(Long id) throws AdminVicException;

	CategoryRest deleteCategoryPhysically(Long id) throws AdminVicException;

}

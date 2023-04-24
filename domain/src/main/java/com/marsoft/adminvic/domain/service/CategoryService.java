package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.persistence.solr.entity.CategorySolr;

public interface CategoryService {

	CategorySolr getCategoryById(Long id) throws AdminVicException;

	List<CategorySolr> getAllCategories() throws AdminVicException;

	CategorySolr createCategory(CategorySolr actorRest) throws AdminVicException;

	CategorySolr updateCategory(CategorySolr actorRest) throws AdminVicException;

	CategorySolr deleteCategory(Long id) throws AdminVicException;

	CategorySolr deleteCategoryPhysically(Long id) throws AdminVicException;

}

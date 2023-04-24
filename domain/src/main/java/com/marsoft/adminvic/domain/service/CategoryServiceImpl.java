package com.marsoft.adminvic.domain.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.exception.NotFoundException;
import com.marsoft.adminvic.persistence.entity.Category;
import com.marsoft.adminvic.persistence.repository.CategoryRepository;
import com.marsoft.adminvic.persistence.solr.entity.CategorySolr;

@Service
public class CategoryServiceImpl implements CategoryService {

	private Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	private static final String ERROR_MESSAGE = "ERROR: ";
	private static final String CATEGORY_NOT_FOUND = "Categories not found!";
	private static final String CATEGORIES_NOT_FOUND = "Categories not found!";

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategorySolr getCategoryById(Long id) throws AdminVicException {
		log.info("Geting category...");
		CategorySolr categoryResponse = null;
		try {
			categoryResponse = modelMapper.map(categoryRepository.findById(id).orElse(null), CategorySolr.class);
			if (categoryResponse != null) {
				log.info("Category found");
			} else {
				throw new NotFoundException(CATEGORY_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return categoryResponse;
	}

	@Override
	public List<CategorySolr> getAllCategories() throws AdminVicException {
		log.info("Geting all available categories...");
		List<CategorySolr> categoryResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`vicod`.`dev`.`category` USING GSI;
			 */
			categoryResponseList = categoryRepository.findAll().stream()
					.map(category -> modelMapper.map(category, CategorySolr.class)).collect(Collectors.toList());
			if (!categoryResponseList.isEmpty()) {
				log.info("Categories found");
			} else {
				throw new NotFoundException(CATEGORIES_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return categoryResponseList;
	}

	@Override
	@Transactional
	public CategorySolr createCategory(CategorySolr categoryRest) throws AdminVicException {
		log.info("Creating category...");
		CategorySolr categoryResponse = null;
		try {
			Category category = modelMapper.map(categoryRest, Category.class);
			category.setInsertDate(String.valueOf(new Date()));
			categoryResponse = modelMapper.map(categoryRepository.save(category), CategorySolr.class);
			log.info("Category created");
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return categoryResponse;
	}

	@Override
	@Transactional
	public CategorySolr updateCategory(CategorySolr categoryRest) throws AdminVicException {
		log.info("Updating category...");
		CategorySolr categoryResponse = modelMapper.map(categoryRepository.findById(categoryRest.getId()).orElse(null),
				CategorySolr.class);
		if (categoryResponse != null) {
			try {
				Category category = modelMapper.map(categoryRest, Category.class);
				category.setUpdatedDate(String.valueOf(new Date()));
				category = categoryRepository.save(category);
				categoryResponse = modelMapper.map(category, CategorySolr.class);
				log.info("Category updated");
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder();
				sb.append(ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(CATEGORY_NOT_FOUND);
		}
		return categoryResponse;
	}

	@Override
	@Transactional
	public CategorySolr deleteCategory(Long id) throws AdminVicException {
		log.info("Deliting category...");
		CategorySolr categoryResponse = null;
		try {
			Category category = categoryRepository.findById(id).orElse(null);
			if (category != null) {
				category.setDeleted(true);
				category = categoryRepository.save(category);
				categoryResponse = modelMapper.map(category, CategorySolr.class);
				log.info("Category deleted");
			} else {
				throw new NotFoundException(CATEGORY_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return categoryResponse;
	}

	@Override
	@Transactional
	public CategorySolr deleteCategoryPhysically(Long id) throws AdminVicException {
		log.info("Deliting category physically...");
		CategorySolr categoryResponse = null;
		try {
			Category category = categoryRepository.findById(id).orElse(null);
			if (category != null) {
				categoryRepository.delete(category);
				categoryResponse = modelMapper.map(category, CategorySolr.class);
				log.info("Category deleted physically");
			} else {
				throw new NotFoundException(CATEGORY_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return categoryResponse;
	}

}

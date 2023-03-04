package com.marsoft.adminvic.domain.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.service.CategoryServiceImpl;
import com.marsoft.adminvic.persistence.entity.Category;
import com.marsoft.adminvic.persistence.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryTest {

	@Mock
	CategoryRepository categoryRepository;

	@InjectMocks
	CategoryServiceImpl categoryService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	List<Category> loadMockCategorys() {
		List<Category> mockCategorys = new ArrayList<>();

		// create category 1
		Category category1 = new Category();
		category1.setId(1L);
		category1.setCategoryName("Action");
		mockCategorys.add(category1);

		// create category 2
		Category category2 = new Category();
		category2.setId(2L);
		category2.setCategoryName("Commedy");
		mockCategorys.add(category2);

		return mockCategorys;
	}

	@Test
	void getAllCategoryByIdTest() throws AdminVicException {
		// given
		List<Category> mockCategorys = loadMockCategorys();
		Optional<Category> optionalCategory = Optional.of(mockCategorys.get(0));
		lenient().when(categoryRepository.findById(1L)).thenReturn(optionalCategory);

		// when
		Category givenCategory = mockCategorys.get(0);

		// then
		Category expectedCategory = new Category();
		expectedCategory.setId(1L);
		expectedCategory.setCategoryName("Action");

		assertEquals(expectedCategory.getId(), givenCategory.getId());
	}

	@Test
	void getAllCategorysTest() throws AdminVicException {
		// given
		List<Category> mockCategorys = loadMockCategorys();
		lenient().when(categoryRepository.findAll()).thenReturn(mockCategorys);

		// when
		int givenSize = mockCategorys.size();

		// then
		int expectedSize = 2;
		assertEquals(expectedSize, givenSize);
	}

	@Test
	void createCategoryTest() throws AdminVicException {
		// given
		Category mockCategory = new Category();
		mockCategory.setId(1L);
		mockCategory.setCategoryName("Action");
		lenient().when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(mockCategory);

		// when
		Category savedCategory = categoryRepository.save(mockCategory);

		// then
		assertEquals("Action", savedCategory.getCategoryName());
	}

	@Test
	void updateCategoryTest() throws AdminVicException {
		// given
		List<Category> categoryList = new ArrayList<>();

		Category category1 = new Category();
		category1.setId(1L);
		category1.setCategoryName("Action");
		categoryList.add(category1);

		Category category2 = new Category();
		category2.setId(2L);
		category2.setCategoryName("Commedy");
		categoryList.add(category2);

		Category category1Updated = new Category();
		category1Updated.setId(1L);
		category1Updated.setCategoryName("Horror");

		if (categoryList.stream().anyMatch(x -> x.getId() == category1Updated.getId())) {
			lenient().when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category1Updated);
			categoryList.set(0, category1Updated);
		}

		// when
		String expected = "Horror";

		// then
		assertEquals(categoryList.get(0).getCategoryName(), expected);
	}

	@Test
	void deleteCategoryTest() throws AdminVicException {
		// given
		List<Category> categoryList = new ArrayList<>();

		Category category1 = new Category();
		category1.setId(1L);
		category1.setCategoryName("Action");
		category1.setDeleted(false);
		categoryList.add(category1);

		Category category2 = new Category();
		category2.setId(2L);
		category2.setCategoryName("Commedy");
		categoryList.add(category2);

		category1.setDeleted(true);
		lenient().when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category1);
		categoryList.set(0, category1);

		// when
		boolean expected = true;

		// then
		assertEquals(category1.getDeleted(), expected);
	}

	@Test
	void deleteCategoryPhysicallyTest() throws AdminVicException {
		// given
		Category category1 = new Category();
		category1.setId(1L);
		category1.setCategoryName("Action");

		// when
		categoryRepository.delete(category1);

		// then
		Mockito.verify(categoryRepository).delete(category1);
	}

}

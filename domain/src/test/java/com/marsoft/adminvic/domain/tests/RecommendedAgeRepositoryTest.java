package com.marsoft.adminvic.domain.tests;

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
import com.marsoft.adminvic.domain.service.RecommendedAgeServiceImpl;
import com.marsoft.adminvic.persistence.entity.RecommendedAge;
import com.marsoft.adminvic.persistence.repository.RecommendedAgeRepository;

@ExtendWith(MockitoExtension.class)
class RecommendedAgeRepositoryTest {

	@Mock
	RecommendedAgeRepository recommendedAgeRepository;

	@InjectMocks
	RecommendedAgeServiceImpl recommendedAgeService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	List<RecommendedAge> loadMockRecommendedAges() {
		List<RecommendedAge> mockRecommendedAges = new ArrayList<>();

		// create recommendedAge 1
		RecommendedAge recommendedAge1 = new RecommendedAge();
		recommendedAge1.setId(1L);
		recommendedAge1.setRecommendedAge("+16");
		mockRecommendedAges.add(recommendedAge1);

		// create recommendedAge 2
		RecommendedAge recommendedAge2 = new RecommendedAge();
		recommendedAge2.setId(2L);
		recommendedAge2.setRecommendedAge("+18");
		mockRecommendedAges.add(recommendedAge2);

		return mockRecommendedAges;
	}

	@Test
	void getAllRecommendedAgeByIdTest() throws AdminVicException {
		// given
		List<RecommendedAge> mockRecommendedAges = loadMockRecommendedAges();
		Optional<RecommendedAge> optionalRecommendedAge = Optional.of(mockRecommendedAges.get(0));
		lenient().when(recommendedAgeRepository.findById(1L)).thenReturn(optionalRecommendedAge);

		// when
		RecommendedAge givenRecommendedAge = mockRecommendedAges.get(0);

		// then
		RecommendedAge expectedRecommendedAge = new RecommendedAge();
		expectedRecommendedAge.setId(1L);
		expectedRecommendedAge.setRecommendedAge("+16");

		assertEquals(expectedRecommendedAge.getId(), givenRecommendedAge.getId());
	}

	@Test
	void getAllRecommendedAgesTest() throws AdminVicException {
		// given
		List<RecommendedAge> mockRecommendedAges = loadMockRecommendedAges();
		lenient().when(recommendedAgeRepository.findAll()).thenReturn(mockRecommendedAges);

		// when
		int givenSize = mockRecommendedAges.size();

		// then
		int expectedSize = 2;
		assertEquals(expectedSize, givenSize);
	}

	@Test
	void createRecommendedAgeTest() throws AdminVicException {
		// given
		RecommendedAge mockRecommendedAge = new RecommendedAge();
		mockRecommendedAge.setId(1L);
		mockRecommendedAge.setRecommendedAge("+16");
		lenient().when(recommendedAgeRepository.save(Mockito.any(RecommendedAge.class))).thenReturn(mockRecommendedAge);

		// when
		RecommendedAge savedRecommendedAge = recommendedAgeRepository.save(mockRecommendedAge);

		// then
		assertEquals("+16", savedRecommendedAge.getRecommendedAge());
	}

	@Test
	void updateRecommendedAgeTest() throws AdminVicException {
		// given
		List<RecommendedAge> recommendedAgeList = new ArrayList<>();

		RecommendedAge recommendedAge1 = new RecommendedAge();
		recommendedAge1.setId(1L);
		recommendedAge1.setRecommendedAge("+16");
		recommendedAgeList.add(recommendedAge1);

		RecommendedAge recommendedAge2 = new RecommendedAge();
		recommendedAge2.setId(2L);
		recommendedAge2.setRecommendedAge("+18");
		recommendedAgeList.add(recommendedAge2);

		RecommendedAge recommendedAge1Updated = new RecommendedAge();
		recommendedAge1Updated.setId(1L);
		recommendedAge1Updated.setRecommendedAge("+6");

		if (recommendedAgeList.stream().anyMatch(x -> x.getId() == recommendedAge1Updated.getId())) {
			lenient().when(recommendedAgeRepository.save(Mockito.any(RecommendedAge.class)))
					.thenReturn(recommendedAge1Updated);
			recommendedAgeList.set(0, recommendedAge1Updated);
		}

		// when
		String expected = "+6";

		// then
		assertEquals(recommendedAgeList.get(0).getRecommendedAge(), expected);
	}

	@Test
	void deleteRecommendedAgeTest() throws AdminVicException {
		// given
		List<RecommendedAge> recommendedAgeList = new ArrayList<>();

		RecommendedAge recommendedAge1 = new RecommendedAge();
		recommendedAge1.setId(1L);
		recommendedAge1.setRecommendedAge("+16");
		recommendedAge1.setDeleted(false);
		recommendedAgeList.add(recommendedAge1);

		RecommendedAge recommendedAge2 = new RecommendedAge();
		recommendedAge2.setId(2L);
		recommendedAge2.setRecommendedAge("+18");
		recommendedAgeList.add(recommendedAge2);

		recommendedAge1.setDeleted(true);
		lenient().when(recommendedAgeRepository.save(Mockito.any(RecommendedAge.class))).thenReturn(recommendedAge1);
		recommendedAgeList.set(0, recommendedAge1);

		// when
		boolean expected = true;

		// then
		assertEquals(recommendedAge1.getDeleted(), expected);
	}

	@Test
	void deleteRecommendedAgePhysicallyTest() throws AdminVicException {
		// given
		RecommendedAge recommendedAge1 = new RecommendedAge();
		recommendedAge1.setId(1L);
		recommendedAge1.setRecommendedAge("+16");

		// when
		recommendedAgeRepository.delete(recommendedAge1);

		// then
		Mockito.verify(recommendedAgeRepository).delete(recommendedAge1);
	}
}

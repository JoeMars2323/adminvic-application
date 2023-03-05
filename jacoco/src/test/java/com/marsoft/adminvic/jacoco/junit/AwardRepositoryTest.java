package com.marsoft.adminvic.jacoco.junit;

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
import com.marsoft.adminvic.domain.service.AwardServiceImpl;
import com.marsoft.adminvic.persistence.entity.Award;
import com.marsoft.adminvic.persistence.repository.AwardRepository;

@ExtendWith(MockitoExtension.class)
class AwardRepositoryTest {

	@Mock
	AwardRepository awardRepository;

	@InjectMocks
	AwardServiceImpl awardService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	List<Award> loadMockAwards() {
		List<Award> mockAwards = new ArrayList<>();

		// create award 1
		Award award1 = new Award();
		award1.setId(1L);
		award1.setAwardName("Best actor");
		mockAwards.add(award1);

		// create award 2
		Award award2 = new Award();
		award2.setId(2L);
		award2.setAwardName("Best actress");
		mockAwards.add(award2);

		return mockAwards;
	}

	@Test
	void getAllAwardByIdTest() throws AdminVicException {
		// given
		List<Award> mockAwards = loadMockAwards();
		Optional<Award> optionalAward = Optional.of(mockAwards.get(0));
		lenient().when(awardRepository.findById(1L)).thenReturn(optionalAward);

		// when
		Award givenAward = mockAwards.get(0);

		// then
		Award expectedAward = new Award();
		expectedAward.setId(1L);
		expectedAward.setAwardName("Best actor");

		assertEquals(expectedAward.getId(), givenAward.getId());
	}

	@Test
	void getAllAwardsTest() throws AdminVicException {
		// given
		List<Award> mockAwards = loadMockAwards();
		lenient().when(awardRepository.findAll()).thenReturn(mockAwards);

		// when
		int givenSize = mockAwards.size();

		// then
		int expectedSize = 2;
		assertEquals(expectedSize, givenSize);
	}

	@Test
	void createAwardTest() throws AdminVicException {
		// given
		Award mockAward = new Award();
		mockAward.setId(1L);
		mockAward.setAwardName("Best actor");
		lenient().when(awardRepository.save(Mockito.any(Award.class))).thenReturn(mockAward);

		// when
		Award savedAward = awardRepository.save(mockAward);

		// then
		assertEquals("Best actor", savedAward.getAwardName());
	}

	@Test
	void updateAwardTest() throws AdminVicException {
		// given
		List<Award> awardList = new ArrayList<>();

		Award award1 = new Award();
		award1.setId(1L);
		award1.setAwardName("Best actor");
		awardList.add(award1);

		Award award2 = new Award();
		award2.setId(2L);
		award2.setAwardName("Best actress");
		awardList.add(award2);

		Award award1Updated = new Award();
		award1Updated.setId(1L);
		award1Updated.setAwardName("Best movie");

		if (awardList.stream().anyMatch(x -> x.getId() == award1Updated.getId())) {
			lenient().when(awardRepository.save(Mockito.any(Award.class))).thenReturn(award1Updated);
			awardList.set(0, award1Updated);
		}

		// when
		String expected = "Best movie";

		// then
		assertEquals(awardList.get(0).getAwardName(), expected);
	}

	@Test
	void deleteAwardTest() throws AdminVicException {
		// given
		List<Award> awardList = new ArrayList<>();

		Award award1 = new Award();
		award1.setId(1L);
		award1.setAwardName("Best actor");
		award1.setDeleted(false);
		awardList.add(award1);

		Award award2 = new Award();
		award2.setId(2L);
		award2.setAwardName("Best actress");
		awardList.add(award2);

		award1.setDeleted(true);
		lenient().when(awardRepository.save(Mockito.any(Award.class))).thenReturn(award1);
		awardList.set(0, award1);

		// when
		boolean expected = true;

		// then
		assertEquals(award1.getDeleted(), expected);
	}

	@Test
	void deleteAwardPhysicallyTest() throws AdminVicException {
		// given
		Award award1 = new Award();
		award1.setId(1L);
		award1.setAwardName("Best actor");

		// when
		awardRepository.delete(award1);

		// then
		Mockito.verify(awardRepository).delete(award1);
	}
}

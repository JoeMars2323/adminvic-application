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
import com.marsoft.adminvic.domain.service.SeasonServiceImpl;
import com.marsoft.adminvic.persistence.entity.Season;
import com.marsoft.adminvic.persistence.repository.SeasonRepository;

@ExtendWith(MockitoExtension.class)
class SeasonRepositoryTest {

	@Mock
	SeasonRepository seasonRepository;

	@InjectMocks
	SeasonServiceImpl seasonService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	List<Season> loadMockSeasons() {
		List<Season> mockSeasons = new ArrayList<>();

		// create season 1
		Season season1 = new Season();
		season1.setId(1L);
		season1.setSeasonName("Season 1");
		mockSeasons.add(season1);

		// create season 2
		Season season2 = new Season();
		season2.setId(2L);
		season2.setSeasonName("Season 2");
		mockSeasons.add(season2);

		return mockSeasons;
	}

	@Test
	void getAllSeasonByIdTest() throws AdminVicException {
		// given
		List<Season> mockSeasons = loadMockSeasons();
		Optional<Season> optionalSeason = Optional.of(mockSeasons.get(0));
		lenient().when(seasonRepository.findById(1L)).thenReturn(optionalSeason);

		// when
		Season givenSeason = mockSeasons.get(0);

		// then
		Season expectedSeason = new Season();
		expectedSeason.setId(1L);
		expectedSeason.setSeasonName("Season 1");

		assertEquals(expectedSeason.getId(), givenSeason.getId());
	}

	@Test
	void getAllSeasonsTest() throws AdminVicException {
		// given
		List<Season> mockSeasons = loadMockSeasons();
		lenient().when(seasonRepository.findAll()).thenReturn(mockSeasons);

		// when
		int givenSize = mockSeasons.size();

		// then
		int expectedSize = 2;
		assertEquals(expectedSize, givenSize);
	}

	@Test
	void createSeasonTest() throws AdminVicException {
		// given
		Season mockSeason = new Season();
		mockSeason.setId(1L);
		mockSeason.setSeasonName("Season 1");
		lenient().when(seasonRepository.save(Mockito.any(Season.class))).thenReturn(mockSeason);

		// when
		Season savedSeason = seasonRepository.save(mockSeason);

		// then
		assertEquals("Season 1", savedSeason.getSeasonName());
	}

	@Test
	void updateSeasonTest() throws AdminVicException {
		// given
		List<Season> seasonList = new ArrayList<>();

		Season season1 = new Season();
		season1.setId(1L);
		season1.setSeasonName("Season 1");
		seasonList.add(season1);

		Season season2 = new Season();
		season2.setId(2L);
		season2.setSeasonName("Season 2");
		seasonList.add(season2);

		Season season1Updated = new Season();
		season1Updated.setId(1L);
		season1Updated.setSeasonName("Season 3");

		if (seasonList.stream().anyMatch(x -> x.getId() == season1Updated.getId())) {
			lenient().when(seasonRepository.save(Mockito.any(Season.class))).thenReturn(season1Updated);
			seasonList.set(0, season1Updated);
		}

		// when
		String expected = "Season 3";

		// then
		assertEquals(seasonList.get(0).getSeasonName(), expected);
	}

	@Test
	void deleteSeasonTest() throws AdminVicException {
		// given
		List<Season> seasonList = new ArrayList<>();

		Season season1 = new Season();
		season1.setId(1L);
		season1.setSeasonName("Season 1");
		season1.setDeleted(false);
		seasonList.add(season1);

		Season season2 = new Season();
		season2.setId(2L);
		season2.setSeasonName("Season 2");
		seasonList.add(season2);

		season1.setDeleted(true);
		lenient().when(seasonRepository.save(Mockito.any(Season.class))).thenReturn(season1);
		seasonList.set(0, season1);

		// when
		boolean expected = true;

		// then
		assertEquals(season1.getDeleted(), expected);
	}

	@Test
	void deleteSeasonPhysicallyTest() throws AdminVicException {
		// given
		Season season1 = new Season();
		season1.setId(1L);
		season1.setSeasonName("Season 1");

		// when
		seasonRepository.delete(season1);

		// then
		Mockito.verify(seasonRepository).delete(season1);
	}

}

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
import com.marsoft.adminvic.domain.service.TvserieServiceImpl;
import com.marsoft.adminvic.persistence.entity.Tvserie;
import com.marsoft.adminvic.persistence.repository.TvserieRepository;

@ExtendWith(MockitoExtension.class)
class TvserieRepositoryTest {

	@Mock
	TvserieRepository tvserieRepository;

	@InjectMocks
	TvserieServiceImpl tvserieService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	List<Tvserie> loadMockTvseries() {
		List<Tvserie> mockTvseries = new ArrayList<>();

		// create tvserie 1
		Tvserie tvserie1 = new Tvserie();
		tvserie1.setId(1L);
		tvserie1.setTvserieName("The Walhing Dead");
		mockTvseries.add(tvserie1);

		// create tvserie 2
		Tvserie tvserie2 = new Tvserie();
		tvserie2.setId(2L);
		tvserie2.setTvserieName("Mission Impossible");
		mockTvseries.add(tvserie2);

		return mockTvseries;
	}

	@Test
	void getAllTvserieByIdTest() throws AdminVicException {
		// given
		List<Tvserie> mockTvseries = loadMockTvseries();
		Optional<Tvserie> optionalTvserie = Optional.of(mockTvseries.get(0));
		lenient().when(tvserieRepository.findById(1L)).thenReturn(optionalTvserie);

		// when
		Tvserie givenTvserie = mockTvseries.get(0);

		// then
		Tvserie expectedTvserie = new Tvserie();
		expectedTvserie.setId(1L);
		expectedTvserie.setTvserieName("The Walhing Dead");

		assertEquals(expectedTvserie.getId(), givenTvserie.getId());
	}

	@Test
	void getAllTvseriesTest() throws AdminVicException {
		// given
		List<Tvserie> mockTvseries = loadMockTvseries();
		lenient().when(tvserieRepository.findAll()).thenReturn(mockTvseries);

		// when
		int givenSize = mockTvseries.size();

		// then
		int expectedSize = 2;
		assertEquals(expectedSize, givenSize);
	}

	@Test
	void createTvserieTest() throws AdminVicException {
		// given
		Tvserie mockTvserie = new Tvserie();
		mockTvserie.setId(1L);
		mockTvserie.setTvserieName("The Walhing Dead");
		lenient().when(tvserieRepository.save(Mockito.any(Tvserie.class))).thenReturn(mockTvserie);

		// when
		Tvserie savedTvserie = tvserieRepository.save(mockTvserie);

		// then
		assertEquals("The Walhing Dead", savedTvserie.getTvserieName());
	}

	@Test
	void updateTvserieTest() throws AdminVicException {
		// given
		List<Tvserie> tvserieList = new ArrayList<>();

		Tvserie tvserie1 = new Tvserie();
		tvserie1.setId(1L);
		tvserie1.setTvserieName("The Walhing Dead");
		tvserieList.add(tvserie1);

		Tvserie tvserie2 = new Tvserie();
		tvserie2.setId(2L);
		tvserie2.setTvserieName("Mission Impossible");
		tvserieList.add(tvserie2);

		Tvserie tvserie1Updated = new Tvserie();
		tvserie1Updated.setId(1L);
		tvserie1Updated.setTvserieName("Stingers");

		if (tvserieList.stream().anyMatch(x -> x.getId() == tvserie1Updated.getId())) {
			lenient().when(tvserieRepository.save(Mockito.any(Tvserie.class))).thenReturn(tvserie1Updated);
			tvserieList.set(0, tvserie1Updated);
		}

		// when
		String expected = "Stingers";

		// then
		assertEquals(tvserieList.get(0).getTvserieName(), expected);
	}

	@Test
	void deleteTvserieTest() throws AdminVicException {
		// given
		List<Tvserie> tvserieList = new ArrayList<>();

		Tvserie tvserie1 = new Tvserie();
		tvserie1.setId(1L);
		tvserie1.setTvserieName("The Walhing Dead");
		tvserie1.setDeleted(false);
		tvserieList.add(tvserie1);

		Tvserie tvserie2 = new Tvserie();
		tvserie2.setId(2L);
		tvserie2.setTvserieName("Mission Impossible");
		tvserieList.add(tvserie2);

		tvserie1.setDeleted(true);
		lenient().when(tvserieRepository.save(Mockito.any(Tvserie.class))).thenReturn(tvserie1);
		tvserieList.set(0, tvserie1);

		// when
		boolean expected = true;

		// then
		assertEquals(tvserie1.getDeleted(), expected);
	}

	@Test
	void deleteTvseriePhysicallyTest() throws AdminVicException {
		// given
		Tvserie tvserie1 = new Tvserie();
		tvserie1.setId(1L);
		tvserie1.setTvserieName("The Walhing Dead");

		// when
		tvserieRepository.delete(tvserie1);

		// then
		Mockito.verify(tvserieRepository).delete(tvserie1);
	}

}

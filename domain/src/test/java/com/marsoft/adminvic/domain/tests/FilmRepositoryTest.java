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
import com.marsoft.adminvic.domain.service.FilmServiceImpl;
import com.marsoft.adminvic.persistence.entity.Film;
import com.marsoft.adminvic.persistence.repository.FilmRepository;

@ExtendWith(MockitoExtension.class)
class FilmRepositoryTest {

	@Mock
	FilmRepository filmRepository;

	@InjectMocks
	FilmServiceImpl filmService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	List<Film> loadMockFilms() {
		List<Film> mockFilms = new ArrayList<>();

		// create film 1
		Film film1 = new Film();
		film1.setId(1L);
		film1.setFilmName("Point Break");
		mockFilms.add(film1);

		// create film 2
		Film film2 = new Film();
		film2.setId(2L);
		film2.setFilmName("Predator");
		mockFilms.add(film2);

		return mockFilms;
	}

	@Test
	void getAllFilmByIdTest() throws AdminVicException {
		// given
		List<Film> mockFilms = loadMockFilms();
		Optional<Film> optionalFilm = Optional.of(mockFilms.get(0));
		lenient().when(filmRepository.findById(1L)).thenReturn(optionalFilm);

		// when
		Film givenFilm = mockFilms.get(0);

		// then
		Film expectedFilm = new Film();
		expectedFilm.setId(1L);
		expectedFilm.setFilmName("Point Break");

		assertEquals(expectedFilm.getId(), givenFilm.getId());
	}

	@Test
	void getAllFilmsTest() throws AdminVicException {
		// given
		List<Film> mockFilms = loadMockFilms();
		lenient().when(filmRepository.findAll()).thenReturn(mockFilms);

		// when
		int givenSize = mockFilms.size();

		// then
		int expectedSize = 2;
		assertEquals(expectedSize, givenSize);
	}

	@Test
	void createFilmTest() throws AdminVicException {
		// given
		Film mockFilm = new Film();
		mockFilm.setId(1L);
		mockFilm.setFilmName("Point Break");
		lenient().when(filmRepository.save(Mockito.any(Film.class))).thenReturn(mockFilm);

		// when
		Film savedFilm = filmRepository.save(mockFilm);

		// then
		assertEquals("Point Break", savedFilm.getFilmName());
	}

	@Test
	void updateFilmTest() throws AdminVicException {
		// given
		List<Film> filmList = new ArrayList<>();

		Film film1 = new Film();
		film1.setId(1L);
		film1.setFilmName("Point Break");
		filmList.add(film1);

		Film film2 = new Film();
		film2.setId(2L);
		film2.setFilmName("Predator");
		filmList.add(film2);

		Film film1Updated = new Film();
		film1Updated.setId(1L);
		film1Updated.setFilmName("Lola Rennt");

		if (filmList.stream().anyMatch(x -> x.getId() == film1Updated.getId())) {
			lenient().when(filmRepository.save(Mockito.any(Film.class))).thenReturn(film1Updated);
			filmList.set(0, film1Updated);
		}

		// when
		String expected = "Lola Rennt";

		// then
		assertEquals(filmList.get(0).getFilmName(), expected);
	}

	@Test
	void deleteFilmTest() throws AdminVicException {
		// given
		List<Film> filmList = new ArrayList<>();

		Film film1 = new Film();
		film1.setId(1L);
		film1.setFilmName("Point Break");
		film1.setDeleted(false);
		filmList.add(film1);

		Film film2 = new Film();
		film2.setId(2L);
		film2.setFilmName("Predator");
		filmList.add(film2);

		film1.setDeleted(true);
		lenient().when(filmRepository.save(Mockito.any(Film.class))).thenReturn(film1);
		filmList.set(0, film1);

		// when
		boolean expected = true;

		// then
		assertEquals(film1.getDeleted(), expected);
	}

	@Test
	void deleteFilmPhysicallyTest() throws AdminVicException {
		// given
		Film film1 = new Film();
		film1.setId(1L);
		film1.setFilmName("Point Break");

		// when
		filmRepository.delete(film1);

		// then
		Mockito.verify(filmRepository).delete(film1);
	}

}

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
import com.marsoft.adminvic.domain.service.ChapterServiceImpl;
import com.marsoft.adminvic.persistence.entity.Chapter;
import com.marsoft.adminvic.persistence.repository.ChapterRepository;

@ExtendWith(MockitoExtension.class)
class ChapterRepositoryTest {

	@Mock
	ChapterRepository chapterRepository;

	@InjectMocks
	ChapterServiceImpl chapterService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	List<Chapter> loadMockChapters() {
		List<Chapter> mockChapters = new ArrayList<>();

		// create chapter 1
		Chapter chapter1 = new Chapter();
		chapter1.setId(1L);
		chapter1.setChapterName("One day in paradise");
		mockChapters.add(chapter1);

		// create chapter 2
		Chapter chapter2 = new Chapter();
		chapter2.setId(2L);
		chapter2.setChapterName("The hollyday");
		mockChapters.add(chapter2);

		return mockChapters;
	}

	@Test
	void getAllChapterByIdTest() throws AdminVicException {
		// given
		List<Chapter> mockChapters = loadMockChapters();
		Optional<Chapter> optionalChapter = Optional.of(mockChapters.get(0));
		lenient().when(chapterRepository.findById(1L)).thenReturn(optionalChapter);

		// when
		Chapter givenChapter = mockChapters.get(0);

		// then
		Chapter expectedChapter = new Chapter();
		expectedChapter.setId(1L);
		expectedChapter.setChapterName("One day in paradise");

		assertEquals(expectedChapter.getId(), givenChapter.getId());
	}

	@Test
	void getAllChaptersTest() throws AdminVicException {
		// given
		List<Chapter> mockChapters = loadMockChapters();
		lenient().when(chapterRepository.findAll()).thenReturn(mockChapters);

		// when
		int givenSize = mockChapters.size();

		// then
		int expectedSize = 2;
		assertEquals(expectedSize, givenSize);
	}

	@Test
	void createChapterTest() throws AdminVicException {
		// given
		Chapter mockChapter = new Chapter();
		mockChapter.setId(1L);
		mockChapter.setChapterName("One day in paradise");
		lenient().when(chapterRepository.save(Mockito.any(Chapter.class))).thenReturn(mockChapter);

		// when
		Chapter savedChapter = chapterRepository.save(mockChapter);

		// then
		assertEquals("One day in paradise", savedChapter.getChapterName());
	}

	@Test
	void updateChapterTest() throws AdminVicException {
		// given
		List<Chapter> chapterList = new ArrayList<>();

		Chapter chapter1 = new Chapter();
		chapter1.setId(1L);
		chapter1.setChapterName("One day in paradise");
		chapterList.add(chapter1);

		Chapter chapter2 = new Chapter();
		chapter2.setId(2L);
		chapter2.setChapterName("The hollyday");
		chapterList.add(chapter2);

		Chapter chapter1Updated = new Chapter();
		chapter1Updated.setId(1L);
		chapter1Updated.setChapterName("The Carpinter");

		if (chapterList.stream().anyMatch(x -> x.getId() == chapter1Updated.getId())) {
			lenient().when(chapterRepository.save(Mockito.any(Chapter.class))).thenReturn(chapter1Updated);
			chapterList.set(0, chapter1Updated);
		}

		// when
		String expected = "The Carpinter";

		// then
		assertEquals(chapterList.get(0).getChapterName(), expected);
	}

	@Test
	void deleteChapterTest() throws AdminVicException {
		// given
		List<Chapter> chapterList = new ArrayList<>();

		Chapter chapter1 = new Chapter();
		chapter1.setId(1L);
		chapter1.setChapterName("One day in paradise");
		chapter1.setDeleted(false);
		chapterList.add(chapter1);

		Chapter chapter2 = new Chapter();
		chapter2.setId(2L);
		chapter2.setChapterName("The hollyday");
		chapterList.add(chapter2);

		chapter1.setDeleted(true);
		lenient().when(chapterRepository.save(Mockito.any(Chapter.class))).thenReturn(chapter1);
		chapterList.set(0, chapter1);

		// when
		boolean expected = true;

		// then
		assertEquals(chapter1.getDeleted(), expected);
	}

	@Test
	void deleteChapterPhysicallyTest() throws AdminVicException {
		// given
		Chapter chapter1 = new Chapter();
		chapter1.setId(1L);
		chapter1.setChapterName("One day in paradise");

		// when
		chapterRepository.delete(chapter1);

		// then
		Mockito.verify(chapterRepository).delete(chapter1);
	}

}

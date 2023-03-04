package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.ChapterRest;

public interface ChapterService {

	ChapterRest getChapterById(Long id) throws AdminVicException;

	List<ChapterRest> getAllChapters() throws AdminVicException;

	ChapterRest createChapter(ChapterRest chapterRest) throws AdminVicException;

	ChapterRest updateChapter(ChapterRest chapterRest) throws AdminVicException;

	ChapterRest deleteChapter(Long id) throws AdminVicException;

	ChapterRest deleteChapterPhysically(Long id) throws AdminVicException;

}

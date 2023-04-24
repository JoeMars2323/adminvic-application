package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.persistence.solr.entity.ChapterSolr;

public interface ChapterService {

	ChapterSolr getChapterById(Long id) throws AdminVicException;

	List<ChapterSolr> getAllChapters() throws AdminVicException;

	ChapterSolr createChapter(ChapterSolr chapterRest) throws AdminVicException;

	ChapterSolr updateChapter(ChapterSolr chapterRest) throws AdminVicException;

	ChapterSolr deleteChapter(Long id) throws AdminVicException;

	ChapterSolr deleteChapterPhysically(Long id) throws AdminVicException;

}

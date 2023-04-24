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
import com.marsoft.adminvic.persistence.entity.Chapter;
import com.marsoft.adminvic.persistence.repository.ChapterRepository;
import com.marsoft.adminvic.persistence.solr.entity.ChapterSolr;

@Service
public class ChapterServiceImpl implements ChapterService {

	private Logger log = LoggerFactory.getLogger(ChapterServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	private static final String ERROR_MESSAGE = "ERROR: ";
	private static final String CHAPTER_NOT_FOUND = "Chapter not found!";
	private static final String CHAPTERS_NOT_FOUND = "Chapters not found!";

	@Autowired
	private ChapterRepository chapterRepository;

	@Override
	public ChapterSolr getChapterById(Long id) throws AdminVicException {
		log.info("Geting chapter...");
		ChapterSolr chapterResponse = null;
		try {
			chapterResponse = modelMapper.map(chapterRepository.findById(id).orElse(null), ChapterSolr.class);
			if (chapterResponse != null) {
				log.info("Chapter found");
			} else {
				throw new NotFoundException(CHAPTER_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return chapterResponse;
	}

	@Override
	public List<ChapterSolr> getAllChapters() throws AdminVicException {
		log.info("Geting all available chapters...");
		List<ChapterSolr> chaptersResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`adminvic`.`dev`.`chapter` USING GSI;
			 */
			chaptersResponseList = chapterRepository.findAll().stream()
					.map(chapter -> modelMapper.map(chapter, ChapterSolr.class)).collect(Collectors.toList());
			if (!chaptersResponseList.isEmpty()) {
				log.info("Chapters found");
			} else {
				throw new NotFoundException(CHAPTERS_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return chaptersResponseList;
	}

	@Override
	@Transactional
	public ChapterSolr createChapter(ChapterSolr chapterRest) throws AdminVicException {
		log.info("Creating chapter...");
		ChapterSolr chapterResponse = null;
		try {
			Chapter chapter = modelMapper.map(chapterRest, Chapter.class);
			chapter.setInsertDate(String.valueOf(new Date()));
			chapterResponse = modelMapper.map(chapterRepository.save(chapter), ChapterSolr.class);
			log.info("Chapter created");
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return chapterResponse;
	}

	@Override
	@Transactional
	public ChapterSolr updateChapter(ChapterSolr chapterRest) throws AdminVicException {
		log.info("Updating chapter...");
		ChapterSolr chapterResponse = modelMapper.map(chapterRepository.findById(chapterRest.getId()).orElse(null),
				ChapterSolr.class);
		if (chapterResponse != null) {
			try {
				Chapter chapter = modelMapper.map(chapterRest, Chapter.class);
				chapter.setUpdatedDate(String.valueOf(new Date()));
				chapter = chapterRepository.save(chapter);
				chapterResponse = modelMapper.map(chapter, ChapterSolr.class);
				log.info("Chapter updated");
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder();
				sb.append(ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(CHAPTER_NOT_FOUND);
		}
		return chapterResponse;
	}

	@Override
	@Transactional
	public ChapterSolr deleteChapter(Long id) throws AdminVicException {
		log.info("Deliting chapter...");
		ChapterSolr chapterResponse = null;
		try {
			Chapter chapter = chapterRepository.findById(id).orElse(null);
			if (chapter != null) {
				chapter.setDeleted(true);
				chapter = chapterRepository.save(chapter);
				chapterResponse = modelMapper.map(chapter, ChapterSolr.class);
				log.info("Chapter deleted");
			} else {
				throw new NotFoundException(CHAPTER_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return chapterResponse;
	}

	@Override
	@Transactional
	public ChapterSolr deleteChapterPhysically(Long id) throws AdminVicException {
		log.info("Deliting chapter physically...");
		ChapterSolr chapterResponse = null;
		try {
			Chapter chapter = chapterRepository.findById(id).orElse(null);
			if (chapter != null) {
				chapterRepository.delete(chapter);
				chapterResponse = modelMapper.map(chapter, ChapterSolr.class);
				log.info("Chapter deleted physically");
			} else {
				throw new NotFoundException(CHAPTER_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return chapterResponse;
	}

}

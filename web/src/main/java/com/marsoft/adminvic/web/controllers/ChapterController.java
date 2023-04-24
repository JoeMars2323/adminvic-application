package com.marsoft.adminvic.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.AdminVicResponse;
import com.marsoft.adminvic.domain.service.ChapterService;
import com.marsoft.adminvic.persistence.solr.entity.ChapterSolr;
import com.marsoft.adminvic.web.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.CHAPTERS)
@CrossOrigin(origins = RestConstants.FRONTEND_URL)
public class ChapterController {

	@Autowired
	private ChapterService chapterService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.CHAPTER_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<ChapterSolr> getChapterById(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				chapterService.getChapterById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<List<ChapterSolr>> getAllChapters() throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				chapterService.getAllChapters());
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<ChapterSolr> createChapter(@RequestBody ChapterSolr chapterRest) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				chapterService.createChapter(chapterRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<ChapterSolr> updateChapter(@RequestBody ChapterSolr chapterRest) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				chapterService.updateChapter(chapterRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.CATEGORY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<ChapterSolr> deleteChapter(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				chapterService.deleteChapter(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.CATEGORY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<ChapterSolr> deleteChapterPhysically(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				chapterService.deleteChapterPhysically(id));
	}

}

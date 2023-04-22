package com.marsoft.adminvic.persistence.solr.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "chapter")
public class ChapterSolr extends AbstractSolrEntity {

	@Id
	@Field
	private Long id;
	@Field
	private Integer seasonId;
	@Field
	private String chapterName;
	@Field
	private Integer chapterNumber;
	@Field
	private String chapterResume;
	@Field
	private Float chaptaerDuration;
	@Field
	private String chapterDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Integer getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(Integer chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

	public String getChapterResume() {
		return chapterResume;
	}

	public void setChapterResume(String chapterResume) {
		this.chapterResume = chapterResume;
	}

	public Float getChaptaerDuration() {
		return chaptaerDuration;
	}

	public void setChaptaerDuration(Float chaptaerDuration) {
		this.chaptaerDuration = chaptaerDuration;
	}

	public String getChapterDate() {
		return chapterDate;
	}

	public void setChapterDate(String chapterDate) {
		this.chapterDate = chapterDate;
	}

}

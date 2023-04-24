package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "chapter")
public class ChapterSolr extends AbstractSolrEntity {

	@Id
	@Indexed(name = "id", type = "long")
	private Long id;

	@Indexed(name = "seasonId", type = "long")
	private Long seasonId;

	@Indexed(name = "chapterName", type = "string")
	private String chapterName;

	@Indexed(name = "chapterNumber", type = "int")
	private Integer chapterNumber;

	@Indexed(name = "chapterResume", type = "string")
	private String chapterResume;

	@Indexed(name = "chaptaerDuration", type = "float")
	private Float chaptaerDuration;

	@Indexed(name = "chapterDate", type = "string")
	private String chapterDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
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

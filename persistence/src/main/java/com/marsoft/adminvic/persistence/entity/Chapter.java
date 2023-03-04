package com.marsoft.adminvic.persistence.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Document
@Scope(value = "dev")
@Collection(value = "chapter")
public class Chapter extends AbstractEntity {

	@Id
	private Long id;

	@Field("seasonId")
	private Integer seasonId;

	@Field("chapterName")
	private String chapterName;

	@Field("chapterNumber")
	private Integer chapterNumber;

	@Field("chapterResume")
	private String chapterResume;

	@Field("chaptaerDuration")
	private Float chaptaerDuration;

	@Field("chapterDate")
	private String chapterDate;

	private List<Integer> actorList;

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

	public List<Integer> getActorList() {
		return actorList;
	}

	public void setActorList(List<Integer> actorList) {
		this.actorList = actorList;
	}

}

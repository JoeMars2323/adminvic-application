package com.marsoft.adminvic.domain.response;

import java.util.List;

public class ChapterRest extends AbstractRest {

	private Long id;

	private Long seasonId;

	private String chapterName;

	private Integer chapterNumber;

	private String chapterResume;

	private Float chaptaerDuration;

	private String chapterDate;

	private List<Integer> actorList;

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

	public List<Integer> getActorList() {
		return actorList;
	}

	public void setActorList(List<Integer> actorList) {
		this.actorList = actorList;
	}

}

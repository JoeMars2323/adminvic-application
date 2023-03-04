package com.marsoft.adminvic.domain.response;

public class SeasonRest extends AbstractRest {

	private Long id;

	private Integer tvserieId;

	private String seasonName;

	private Integer seasonNumber;

	private Integer seasonYear;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTvserieId() {
		return tvserieId;
	}

	public void setTvserieId(Integer tvserieId) {
		this.tvserieId = tvserieId;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public Integer getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(Integer seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public Integer getSeasonYear() {
		return seasonYear;
	}

	public void setSeasonYear(Integer seasonYear) {
		this.seasonYear = seasonYear;
	}

}

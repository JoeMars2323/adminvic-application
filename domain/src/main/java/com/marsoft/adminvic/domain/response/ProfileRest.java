package com.marsoft.adminvic.domain.response;

public class ProfileRest extends AbstractRest {

	private Long id;

	private String profileType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

}

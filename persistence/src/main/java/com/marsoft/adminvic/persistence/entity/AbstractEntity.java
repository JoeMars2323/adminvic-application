package com.marsoft.adminvic.persistence.entity;

import org.springframework.data.couchbase.core.mapping.Document;

@Document
public abstract class AbstractEntity {

	private String insertDate;

	private String updatedDate;

	private Boolean deleted;

	private Boolean changed;

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Boolean getChanged() {
		return changed;
	}

	public void setChanged(Boolean changed) {
		this.changed = changed;
	}

}

package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument
public abstract class AbstractSolrEntity {

	@Indexed(name = "insertDate", type = "string")
	private String insertDate;

	@Indexed(name = "updatedDate", type = "string")
	private String updatedDate;

	@Indexed(name = "deleted", type = "boolean")
	private Boolean deleted;

	@Indexed(name = "changed", type = "boolean")
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

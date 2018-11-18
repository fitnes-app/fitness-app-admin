/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitnessapp.api.entities;

import java.io.Serializable;

/**
 *
 * @author Naluem
 */
public class Survey implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private Integer tagId;

	public Survey() {
	}

	public Survey(Integer id) {
		this.id = id;
	}

        public Survey(Integer id, String desc, Integer tagId) {
		this.id = id;
                this.description = desc;
                this.tagId = tagId;
	}
        
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Survey)) {
			return false;
		}
		Survey other = (Survey) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.fitnessapp.entities.Survey[ id=" + id + " ]";
	}

}

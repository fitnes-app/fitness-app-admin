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
public class BodyType implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String bodyTypeValue;

	public BodyType() {
	}

	public BodyType(Integer id) {
		this.id = id;
	}

	public BodyType(Integer id, String bodyTypeValue) {
		this.id = id;
		this.bodyTypeValue = bodyTypeValue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBodyTypeValue() {
		return bodyTypeValue;
	}

	public void setBodyTypeValue(String bodyTypeValue) {
		this.bodyTypeValue = bodyTypeValue;
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
		if (!(object instanceof BodyType)) {
			return false;
		}
		BodyType other = (BodyType) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.fitnessapp.entities.BodyType[ id=" + id + " ]";
	}

}

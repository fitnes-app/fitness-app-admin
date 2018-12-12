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
public class SpecificTip implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String text;
	private MuscularGroup muscularGroupId;

	public SpecificTip() {
	}

	public SpecificTip(Integer id) {
		this.id = id;
	}

	public SpecificTip(Integer id, String text, MuscularGroup muscularGroupId) {
		this.id = id;
		this.text = text;
		this.muscularGroupId = muscularGroupId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public MuscularGroup getMuscularGroupId() {
		return muscularGroupId;
	}

	public void setMuscularGroupId(MuscularGroup muscularGroupId) {
		this.muscularGroupId = muscularGroupId;
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
		if (!(object instanceof SpecificTip)) {
			return false;
		}
		SpecificTip other = (SpecificTip) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.fitnessapp.entities.SpecificTip[ id=" + id + " ]";
	}

}

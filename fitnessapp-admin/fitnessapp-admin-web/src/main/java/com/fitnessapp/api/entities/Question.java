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
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String text;
	private Survey survey;

	public Question() {
	}

	public Question(Integer id) {
		this.id = id;
	}

	public Question(Integer id, String text) {
		this.id = id;
		this.text = text;
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

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
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
		if (!(object instanceof Question)) {
			return false;
		}
		Question other = (Question) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.fitnessapp.entities.Question[ id=" + id + " ]";
	}

}

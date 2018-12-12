/*
 * Copyright (C) 2018 Jordi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.fitnessapp.views;

import com.fitnessapp.api.client.QuestionClient;
import com.fitnessapp.api.client.SurveyClient;
import com.fitnessapp.api.entities.Question;
import com.fitnessapp.api.entities.Survey;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Jordi
 */
@Named(value = "questionCreateView")
@ViewScoped
public class QuestionCreateView implements Serializable{

    
    private Question question = new Question();
    private String text;
    private QuestionClient questionClient = new QuestionClient();
    
    private SurveyClient surveyClient = new SurveyClient();
    private Survey survey = new Survey();
    private List<Survey> surveys = new ArrayList<Survey>();
    
    @PostConstruct
    public void init() {
        surveys = surveyClient.findAll(new GenericType<List<Survey>>() {
        });
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }


    public void save() {
        
            question.setText(text);
            question.setSurvey(survey);
            questionClient.create(question);
        
        
        addMessage("New Question added");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

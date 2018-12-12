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
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Jordi
 */
@Named(value = "questionEditView")
@ViewScoped
public class QuestionEditView implements Serializable{
    
    private QuestionClient questionClient = new QuestionClient();
    private Question question = new Question();
    private List<Question> questions = new ArrayList<Question>();
    
    private SurveyClient surveyClient = new SurveyClient();
    private Survey survey = new Survey();
    private List<Survey> surveys = new ArrayList<Survey>();
    
    @PostConstruct
    public void init() {
        questions = questionClient.findAll(new GenericType<List<Question>>() {
        });
        surveys = surveyClient.findAll(new GenericType<List<Survey>>(){});
    }
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
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
    
    
    public void onRowEdit(RowEditEvent event) {
        question = (Question) event.getObject();
        question.setSurvey(survey);
        questionClient.edit(question, question.getId().toString());

        FacesMessage msg = new FacesMessage("Question Edited", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
//    public void onRowDelete(Question question) {
//        questionClient.remove(question.getId().toString());
//        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Deleted", "");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
    public void eliminarQuestion() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String idQuestion = (String) facesContext.getExternalContext().getRequestParameterMap().get("idQuestion");

            if (idQuestion != null && !"".equals(idQuestion)) {
                questionClient.remove(idQuestion);
                questions = questionClient.findAll(new GenericType<List<Question>>() {
                });
                surveys = surveyClient.findAll(new GenericType<List<Survey>>() {
                });
            }

            FacesContext.getCurrentInstance().addMessage("llistQuestions", new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletion succeed", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    "llistQuestions",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR when deleting", null));
        }

    }
    
    public void recuperarValorCamp(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idSurvey =  (Integer) ((UIOutput) e.getSource()).getValue();
        survey = surveyClient.find(Survey.class, idSurvey.toString());
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String idQuestion = (String) facesContext.getExternalContext().getRequestParameterMap().get("idQuestion");
        
        for(Question q: questions){
            if(q.getId().equals(idQuestion)){
                q.setSurvey(survey);
                break;
            }
        }

    }

    
}

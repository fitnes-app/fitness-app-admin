/*
 * Copyright (C) 2018 Erox
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

import com.fitnessapp.api.client.SurveyClient;
import com.fitnessapp.api.client.TagClient;
import com.fitnessapp.api.entities.Survey;
import com.fitnessapp.api.entities.Tag;
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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Erox
 */
@Named(value = "surveyAddRowView")
@ViewScoped
public class SurveyAddRowView implements Serializable {

    private List<Survey> surveys = new ArrayList<Survey>();
    private List<Tag> tags = getTags();
    private Tag tag = new Tag();
    private Survey survey = new Survey();
    private TagClient tagClient = new TagClient();
    private SurveyClient surveyClient = new SurveyClient();
    
    private boolean tagHasChanged = false;
    
    @PostConstruct
    public void init() {
            surveys = surveyClient.findAll(new GenericType<List<Survey>>() {});
    }
    public List<Tag> getTags(){
        TagClient client = new TagClient();
        List<Tag> tmpTags = client.findAll(new GenericType<List<Tag>>() {});
        return tmpTags;
    }
    
    public void setTags(List<Tag> tags){
        this.tags = tags;
    }
    
    public void onRowEdit(RowEditEvent event) {
        survey = (Survey)event.getObject();
        if(tagHasChanged){
            survey.setTagId(tag);
        }
        SurveyClient client = new SurveyClient();
        client.edit(survey, survey.getId().toString());
        FacesMessage msg = new FacesMessage("Survey Edited", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        FacesMessage msg = new FacesMessage("New Survey added", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

//    public void delete(String id) {
//        SurveyClient surveyClient = new SurveyClient();
//        surveyClient.remove(id);
//        surveyClient.close();
//        FacesMessage msg = new FacesMessage("Data Deleted", "");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
    public void recuperarValorCamp(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idTag = (Integer) ((UIOutput) e.getSource()).getValue();
        tag = tagClient.find(Tag.class, idTag.toString());

        FacesContext facesContext = FacesContext.getCurrentInstance();
        int idSurvey = Integer.parseInt(facesContext.getExternalContext().getRequestParameterMap().get("idSurvey"));

        for (Survey q : surveys) {
            if (q.getId().equals(idSurvey)) {
                q.setTagId(tag);
                tagHasChanged=true;
                break;
            }
        }

    }
    public void setTag(Tag tag){
        this.tag = tag;
    }
    
    public Tag getTag(){
        return this.tag;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public boolean isTagHasChanged() {
        return tagHasChanged;
    }

    public void setTagHasChanged(boolean tagHasChanged) {
        this.tagHasChanged = tagHasChanged;
    }
    
}

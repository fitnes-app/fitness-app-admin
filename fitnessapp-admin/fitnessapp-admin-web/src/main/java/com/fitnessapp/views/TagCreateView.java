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

import com.fitnessapp.api.client.TagClient;
import com.fitnessapp.api.entities.Tag;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Erox
 */
@Named(value = "tagCreateView")
@ViewScoped
public class TagCreateView implements Serializable {
    private String description;
    private TagClient client;
    
    @PostConstruct
    public void init() {
        client = new TagClient();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void save() {
        Tag tag = new Tag();
        tag.setTagName(description);
        this.client.create(tag);
        addMessage("Data saved");
    }

    public void addMessage(String summary) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
    }
        
}

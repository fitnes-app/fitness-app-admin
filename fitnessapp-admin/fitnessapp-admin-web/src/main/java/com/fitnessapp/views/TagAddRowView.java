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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Erox
 */
@Named(value = "tagsAddRowView")
@ViewScoped
public class TagAddRowView implements Serializable{
    
    private List<Tag> tags;
    @PostConstruct
    public void init() {
        tags = new ArrayList<>();
        tags = getTags();
    }
    
    public List<Tag> getTags() {
        TagClient tagClient = new TagClient();
        List<Tag> tmpTag = tagClient.findAll(new GenericType<List<Tag>>(){});
        tagClient.close();
        return tmpTag;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("TagsEdited", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        FacesMessage msg = new FacesMessage("New Tag added", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void delete(String id) {
        TagClient client = new TagClient();
        client.remove(id);
        client.close();
        FacesMessage msg = new FacesMessage("Data Deleted", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}

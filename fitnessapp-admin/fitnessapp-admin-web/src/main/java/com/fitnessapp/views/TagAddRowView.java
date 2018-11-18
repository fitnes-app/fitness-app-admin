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


import com.fitnessapp.api.entities.Tag;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
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
        Tag tag1 = new Tag(1,"Description for this tag");
        Tag tag2 = new Tag(2,"Description for this tag");
        Tag tag3 = new Tag(3,"Description for this tag");
        Tag tag4 = new Tag(4,"Description for this tag");
        Tag tag5 = new Tag(5,"Description for this tag");
        Tag tag6 = new Tag(6,"Description for this tag");
        Tag tag7 = new Tag(7,"Description for this tag");
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tags.add(tag4);
        tags.add(tag5);
        tags.add(tag6);
        tags.add(tag7);
    }
    public List<Tag> getTags() {
		return tags;
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
}

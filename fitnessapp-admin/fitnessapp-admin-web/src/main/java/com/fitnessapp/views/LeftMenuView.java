/*
 * Copyright (C) 2018 Naluem
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

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Naluem
 */
@Named(value = "leftMenuView")
@ViewScoped
public class LeftMenuView implements Serializable{

	private MenuModel model;

	@PostConsruct
	public void init() {
		model = new DefaultMenuModel();

		//First submenu
		DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");

		DefaultMenuItem item = new DefaultMenuItem("External");
		item.setUrl("http://www.primefaces.org");
		item.setIcon("pi pi-home");
		firstSubmenu.addElement(item);

		model.addElement(firstSubmenu);

		//Second submenu
		DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");

		item = new DefaultMenuItem("Save");
		item.setIcon("pi pi-save");
		item.setCommand("#{menuView.save}");
		item.setUpdate("messages");
		secondSubmenu.addElement(item);

		item = new DefaultMenuItem("Delete");
		item.setIcon("pi pi-times");
		item.setCommand("#{menuView.delete}");
		item.setAjax(false);
		secondSubmenu.addElement(item);

		item = new DefaultMenuItem("Redirect");
		item.setIcon("pi pi-search");
		item.setCommand("#{menuView.redirect}");
		secondSubmenu.addElement(item);

		model.addElement(secondSubmenu);
	}

	public MenuModel getModel() {
		return model;
	}

	public void save() {
		addMessage("Success", "Data saved");
	}

	public void update() {
		addMessage("Success", "Data updated");
	}

	public void delete() {
		addMessage("Success", "Data deleted");
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}

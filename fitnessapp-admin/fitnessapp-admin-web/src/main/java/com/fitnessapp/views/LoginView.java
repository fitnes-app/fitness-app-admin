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

import com.fitnessapp.api.client.AdministratorClient;
import com.fitnessapp.api.entities.Administrator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Jordi
 */
@Named(value = "loginView")
@ViewScoped
public class LoginView implements Serializable{
    
    
    private String username;
    private String password;
    private AdministratorClient administratorClient = new AdministratorClient();
    private Administrator admin = new Administrator();

    public Administrator getAdmin() {
        return admin;
    }

    public String validateCredentials(){

        if(null!=username && !"".equals(username) && null!=password && !"".equals(password)){
            try{
                admin = administratorClient.findByUsername(Administrator.class, username);
                if (admin != null) {
                    if (admin.getUserPassword().equals(password)) {
                        return "menu.xhtml?faces-redirect=true";
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong Password", null));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong Username", null));
                }
            }catch(Exception e){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nonexistant Username", null));
            }    
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Empty fields", null));
        }
        return null;
    }
    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}

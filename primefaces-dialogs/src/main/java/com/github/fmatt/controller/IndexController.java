package com.github.fmatt.controller;

import java.io.Serializable;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class IndexController implements Serializable {

    private String name;

    private ScheduleModel scheduleModel = new DefaultScheduleModel();

    public void openDialog() {
        PrimeFaces.current().executeScript("PF('dialog').show()");
    }

    public void showMessage() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hello " + name + "!", ""));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScheduleModel getScheduleModel() {
        return scheduleModel;
    }

    public void setScheduleModel(ScheduleModel scheduleModel) {
        this.scheduleModel = scheduleModel;
    }
    
}

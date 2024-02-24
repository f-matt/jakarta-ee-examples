package com.github.fmatt;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexController implements Serializable {

	private String name;

	private String radio;

	private String extra;

	public void save() {
		FacesContext.getCurrentInstance().addMessage(null, 
		 	new FacesMessage(FacesMessage.SEVERITY_INFO, String.format("Name = %s. Extra = %s", name, extra), ""));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

}

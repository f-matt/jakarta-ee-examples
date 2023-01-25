package com.github.fmatt.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class MainController {
	
	public String getClientIp() {
		HttpServletRequest request = 
			(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		return request.getRemoteAddr();
	}

}

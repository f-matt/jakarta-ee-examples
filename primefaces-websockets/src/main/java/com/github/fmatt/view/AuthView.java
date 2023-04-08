package com.github.fmatt.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;

@Named
@RequestScoped
public class AuthView {

    private static final Logger logger = Logger.getLogger("AuthView");

	@Inject
	private FacesContext facesContext;
	
	private String username;
	
	private String password;
		
	public String login() {
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

		try {
			request.login(username, password);
			PrimeFaces.current().executeScript("jsf.push.open('mainWebSocket')");
			return "/index.jsf?faces-redirect=true";
		} catch(ServletException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username/password", null));
			return "/login.jsf";
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown error on login", null));
			return "login.jsf";
		}
	}
		
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		
		try {
			request.logout();
			return "/login.jsf?faces-redirect=true&logout=true";
		} catch (ServletException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown error on logout", null));
			return "/login.jsf?faces-redirect=true&error=true";
		}
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

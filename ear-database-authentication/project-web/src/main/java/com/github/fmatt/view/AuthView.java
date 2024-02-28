package com.github.fmatt.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.fmatt.util.AuthInfo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class AuthView {

    private static final Logger logger = Logger.getLogger("AuthView");

	@Inject
	private FacesContext facesContext;

	@Inject
	private AuthInfo authInfo;
	
	private String username;
	
	private String password;
		
	public String login() {
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

		try {
			request.login(username, password);
			authInfo.setUsername(username.toUpperCase());
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

package com.github.fmatt.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class AuthView {

    private static final Logger logger = Logger.getLogger("AuthView");

	@Inject
	private FacesContext facesContext;

	@Inject
	private SecurityContext securityContext;

	@NotNull
	private String username;

	@NotNull
	private String password;

	public void login() {
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		try {
			AuthenticationStatus status = securityContext.authenticate(request, response,
					AuthenticationParameters.withParams()
							.credential(new UsernamePasswordCredential(username, new Password(password))));

			switch (status) {
				case SUCCESS -> facesContext.getExternalContext().redirect("admin.jsf");
                case SEND_CONTINUE -> facesContext.responseComplete();
				case SEND_FAILURE -> {
					return;
				}
				default ->
						logger.severe("Error performing authentication, status = " + String.valueOf(status) + ".");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public String logout() {
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

		try {
			request.logout();
			return "login.jsf?faces-redirect=true&logout=true";
		} catch (ServletException e) {
			return "login.jsf?faces-redirect=true&logout-fail=true";
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

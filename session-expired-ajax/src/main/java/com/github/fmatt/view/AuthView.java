package com.github.fmatt.view;

import jakarta.enterprise.context.RequestScoped;
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

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class AuthView {

    private static final Logger logger = Logger.getAnonymousLogger();

	@Inject
	private FacesContext facesContext;

	@Inject
	private SecurityContext securityContext;

	@NotNull
	private String username;

	@NotNull
	private String password;

	private LocalDateTime lastUpdate =  LocalDateTime.now();

	public void login() {
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		try {
			AuthenticationStatus status = securityContext.authenticate(request, response,
					AuthenticationParameters.withParams()
							.credential(new UsernamePasswordCredential(username, new Password(password))));

			switch (status) {
				case SUCCESS -> {
					facesContext.getExternalContext().getFlash().clear();

					String redirectUrl = facesContext.getExternalContext().getRequestContextPath() + "/admin.jsf";
					facesContext.getExternalContext().redirect(redirectUrl);

					facesContext.responseComplete();
				}
                case SEND_CONTINUE -> facesContext.responseComplete();
				case SEND_FAILURE -> { }
				default ->
						logger.severe("Error performing authentication, status = " + status + ".");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public String logout() {
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

		try {
			request.logout();
			facesContext.getExternalContext().invalidateSession();
			return "login.jsf?faces-redirect=true&logout=true";
		} catch (ServletException e) {
			return "login.jsf?faces-redirect=true&logout-fail=true";
		}
	}

	public void ajaxUpdate() {
		this.lastUpdate = LocalDateTime.now();
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

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}

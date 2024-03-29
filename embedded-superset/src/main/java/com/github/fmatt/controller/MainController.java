package com.github.fmatt.controller;

import com.github.fmatt.service.SupersetService;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class MainController {

	@Inject
	private SupersetService supersetService;

	private String message = "Hello world!";

	private String accessToken;

	@PostConstruct
	public void init() {
		try {
			accessToken = supersetService.getAccessToken();
			if (accessToken == null)
				System.err.println("Error getting access token.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}

package com.github.fmatt.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SessionDataView implements Serializable {

    private Boolean authenticated = false;

    private String token = null;

    @PostConstruct
    public void init() {
        System.out.println("Initializing SessionDataView...");
        System.out.println("Authenticated: " + authenticated);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying SessionDataView....");
        System.out.println("Authenticated: " + authenticated);
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}

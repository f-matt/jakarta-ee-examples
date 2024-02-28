package com.github.fmatt.util;

import java.io.Serializable;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.SessionScoped;

@Stateless
@SessionScoped
public class AuthInfo implements Serializable {

    private String username = "ANONYMOUS";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}

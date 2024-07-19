package com.github.fmatt.service;

import com.github.fmatt.model.audit.UserService;

import jakarta.ejb.Stateless;

@Stateless
public class UserServiceImpl implements UserService {

    public String getUsername() {
        return "user";
    }
    
}

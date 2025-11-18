package com.github.fmatt.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DatabaseMockService {

    private String status = "";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

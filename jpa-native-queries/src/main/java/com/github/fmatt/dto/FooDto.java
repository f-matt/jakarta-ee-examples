package com.github.fmatt.dto;

import java.time.LocalDateTime;

public class FooDto {

    private Integer id;

    private String description;

    private String status;

    private LocalDateTime createdAt;

    public FooDto(Integer id, String description, String status, LocalDateTime createdAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    public FooDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

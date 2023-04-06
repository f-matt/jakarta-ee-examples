package com.github.fmatt.model;

import java.io.Serializable;

public class Product implements Serializable {
    
    private Integer id;

    private String name;

    public Product(Integer id) {
        this.id = id;
        this.name = "Product" + id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

package com.github.fmatt.model;

import java.util.ArrayList;
import java.util.List;

public class Foo {

    private Integer id;

    private String description;

    private List<Bar> bars = new ArrayList<>();

    public Foo() {

    }

    public Foo(Integer id, String description, List<Bar> bars) {
        this.id = id;
        this.description = description;
        this.bars = bars;
    }

    @Override
    public String toString() {
        return "Foo [id=" + id + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Foo other = (Foo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
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

    public List<Bar> getBars() {
        return bars;
    }

    public void setBars(List<Bar> bars) {
        this.bars = bars;
    }



}

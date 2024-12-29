package com.github.fmatt.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "table_b")
@NamedQuery(name = "EntityB.findAll", query = "SELECT e FROM EntityB e ORDER BY e.description")
public class EntityB implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EntityB entityB)) return false;
        return Objects.equals(id, entityB.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EntityB{" +
                "id=" + id +
                '}';
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
    
}

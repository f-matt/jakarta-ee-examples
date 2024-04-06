package com.github.fmatt.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.github.fmatt.dto.FooDto;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;

@Entity
@Table(name = "foos")
@NamedQuery(name = "Foo.findAll",
    query = "SELECT f FROM Foo f ORDER BY f.id")
@NamedNativeQuery(name = "Foo.nativeFindAll",
    resultSetMapping = "FooDto",
    query = "SELECT id, description, created_at FROM foos ORDER BY id")
@SqlResultSetMapping(name = "FooDto",
    classes = {
        @ConstructorResult(
            targetClass = FooDto.class,
            columns = {
                @ColumnResult(name = "id"),
                @ColumnResult(name = "description"),
                @ColumnResult(name = "created_at", type = LocalDateTime.class)
            }
        )
    }
)
public class Foo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}

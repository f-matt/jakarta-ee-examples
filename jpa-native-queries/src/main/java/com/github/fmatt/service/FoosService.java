package com.github.fmatt.service;

import java.util.List;

import com.github.fmatt.dto.FooDto;
import com.github.fmatt.model.Foo;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class FoosService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Foo> findEntities() {
        return entityManager.createNamedQuery("Foo.findAll", Foo.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<FooDto> findDtos() {
        return (List<FooDto>) entityManager.createNamedQuery("Foo.nativeFindAll").getResultList();
    }
    
}

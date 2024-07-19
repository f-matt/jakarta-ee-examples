package com.github.fmatt.service;

import com.github.fmatt.model.domain.MyEntity;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class MyService {

    @PersistenceContext
    private EntityManager entityManager;

    public MyEntity getEntity() {
        return entityManager.find(MyEntity.class, 1);
    }

    public void saveEntity(MyEntity myEntity) {
        if (myEntity.getId() == null)
            entityManager.persist(myEntity);
        else
            entityManager.merge(myEntity);
    }

}
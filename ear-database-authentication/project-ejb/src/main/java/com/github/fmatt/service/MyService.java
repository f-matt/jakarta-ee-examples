package com.github.fmatt.service;

import com.github.fmatt.domain.MyEntity;

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

    public void save(MyEntity myEntity) {
        entityManager.merge(myEntity);
    }

}
package com.github.fmatt.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.fmatt.domain.MyEntity;

@Stateless
public class MyService {

    @PersistenceContext
    private EntityManager entityManager;

    public MyEntity getEntity() {
        return entityManager.find(MyEntity.class, 1);
    }

}
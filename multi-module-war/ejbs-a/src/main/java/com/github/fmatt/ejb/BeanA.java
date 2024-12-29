package com.github.fmatt.ejb;

import com.github.fmatt.domain.EntityA;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class BeanA {

    @PersistenceContext
    private EntityManager entityManager;

    public List<EntityA> getEntitiesA() {
        return entityManager.createNamedQuery("EntityA.findAll", EntityA.class).getResultList();
    }

    public void save(EntityA entityA) {
        entityManager.persist(entityA);
    }

}

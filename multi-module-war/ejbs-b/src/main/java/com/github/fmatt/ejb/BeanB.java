package com.github.fmatt.ejb;

import com.github.fmatt.domain.EntityB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class BeanB {

    @PersistenceContext(unitName = "persistenceUnitB")
    private EntityManager entityManager;

    public List<EntityB> getEntitiesB() {
        return entityManager.createNamedQuery("EntityB.findAll", EntityB.class).getResultList();
    }

    public void save(EntityB entityB) {
        entityManager.persist(entityB);
    }

}

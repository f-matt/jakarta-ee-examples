package com.github.fmatt.view;

import com.github.fmatt.domain.EntityA;
import com.github.fmatt.domain.EntityB;
import com.github.fmatt.repository.a.RepositoryA;
import com.github.fmatt.repository.b.RepositoryB;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class IndexView {

    @Inject
    private RepositoryA repositoryA;

    @Inject
    private RepositoryB repositoryB;

    private String newEntityA;

    private String newEntityB;

    private List<EntityA> entitiesA;

    private List<EntityB> entitiesB;

    @PostConstruct
    public void init() {
        entitiesA = repositoryA.getEntitiesA();
        entitiesB = repositoryB.getEntitiesB();
    }

    public void addEntityA() {
        EntityA entityA = new EntityA();
        entityA.setDescription(newEntityA);
        repositoryA.save(entityA);
        entitiesA = repositoryA.getEntitiesA();
    }

    public void addEntityB() {
        EntityB entityB = new EntityB();
        entityB.setDescription(newEntityB);
        repositoryB.save(entityB);
        entitiesB = repositoryB.getEntitiesB();
    }

    public String getNewEntityA() {
        return newEntityA;
    }

    public void setNewEntityA(String newEntityA) {
        this.newEntityA = newEntityA;
    }

    public String getNewEntityB() {
        return newEntityB;
    }

    public void setNewEntityB(String newEntityB) {
        this.newEntityB = newEntityB;
    }

    public List<EntityA> getEntitiesA() {
        return entitiesA;
    }

    public void setEntitiesA(List<EntityA> entitiesA) {
        this.entitiesA = entitiesA;
    }

    public List<EntityB> getEntitiesB() {
        return entitiesB;
    }

    public void setEntitiesB(List<EntityB> entitiesB) {
        this.entitiesB = entitiesB;
    }
}

package com.github.fmatt.model.audit;

import org.hibernate.envers.RevisionListener;

import jakarta.enterprise.inject.spi.CDI;

public class CustomRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        UserService userService = CDI.current().select(UserService.class).get();
        if (userService == null)
            throw new RuntimeException("[DBG1]: null security context");
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
        customRevisionEntity.setUsername(userService.getUsername());
    }
    
}

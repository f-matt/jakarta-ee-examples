package com.github.fmatt.audit;

import org.hibernate.envers.RevisionListener;

import com.github.fmatt.util.AuthInfo;

import jakarta.inject.Inject;

public class CustomRevisionEntityListener implements RevisionListener {

    @Inject
    private AuthInfo authInfo;

    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
        customRevisionEntity.setUsername(authInfo.getUsername());
    }
    
}

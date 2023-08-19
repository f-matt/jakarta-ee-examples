package com.github.fmatt.view;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.fmatt.domain.MyEntity;
import com.github.fmatt.service.MyService;

@Named
@RequestScoped
public class IndexView {

    @Inject
    private MyService myService;

    private MyEntity myEntity;

    @PostConstruct
    public void init() {
        myEntity = myService.getEntity();
    }

    public MyEntity getMyEntity() {
        return myEntity;
    }

    public void setMyEntity(MyEntity myEntity) {
        this.myEntity = myEntity;
    }

}

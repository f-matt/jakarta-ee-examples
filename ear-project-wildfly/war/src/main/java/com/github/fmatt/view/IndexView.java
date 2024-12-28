package com.github.fmatt.view;

import com.github.fmatt.domain.MyEntity;
import com.github.fmatt.service.MyService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

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

package com.github.fmatt.view;

import com.github.fmatt.domain.MyEntity;
import com.github.fmatt.service.MyService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class IndexView implements Serializable {

    @Inject
    private MyService myService;

    private MyEntity myEntity;

    @PostConstruct
    public void init() {
        System.out.println("Test");
        myEntity = myService.getEntity();
        System.out.println("MyEntity: " + myEntity);
    }

    public MyEntity getMyEntity() {
        return myEntity;
    }

    public void setMyEntity(MyEntity myEntity) {
        this.myEntity = myEntity;
    }

}

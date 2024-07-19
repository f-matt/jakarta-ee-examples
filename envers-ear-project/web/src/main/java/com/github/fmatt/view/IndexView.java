package com.github.fmatt.view;

import java.util.Random;

import com.github.fmatt.model.domain.MyEntity;
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

    public void change() {
        Random random = new Random();

        String s = "";
        for (int i = 0; i < 4; ++i) 
            s += String.valueOf(random.nextInt(10));

        myEntity.setDescription(s);
        myService.saveEntity(myEntity);
    }

    public MyEntity getMyEntity() {
        return myEntity;
    }

    public void setMyEntity(MyEntity myEntity) {
        this.myEntity = myEntity;
    }

}

package com.github.fmatt.view;

import java.io.Serializable;

import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexView implements Serializable {

    @Inject
    @Push
    private PushContext mainChannel;

    public void sendMessage() {
        System.out.println("Sending message:");
        mainChannel.send("Hello from WebSocket!");
    }

    public String getText() {
        return "Status: online";
    }
    
}

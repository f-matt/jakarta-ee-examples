package com.github.fmatt.view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;


@Named
@ViewScoped
public class IndexView implements Serializable {

    @Inject
    @Push
    private PushContext mainChannel;

    public void sendMessage() {
        String message = "Hello from WebSocket!";
        System.out.println("Sending message: " + message);
        mainChannel.send(message);
    }

    public String getText() {
        return "Status: online";
    }
    
}

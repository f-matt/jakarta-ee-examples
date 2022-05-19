package com.github.fmatt.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.github.fmatt.model.Bar;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@RequestScoped
public class DownloadController {

    public StreamedContent downloadImage(Bar bar) {
		return DefaultStreamedContent.builder()
			.name(String.format("img-%d.png", bar.getId()))
			.contentType("image/png")
			.stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/java.png"))
			.build();
    }
    
}

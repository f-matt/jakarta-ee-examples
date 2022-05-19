package com.github.fmatt.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.fmatt.model.Bar;
import com.github.fmatt.model.Foo;
import com.github.fmatt.service.FooService;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@ViewScoped
public class IndexController implements Serializable {

	@Inject
	private FooService fooService;

	private List<Foo> foos;

	@PostConstruct
	public void init() {
		foos = fooService.getFoos(); 
	}

	public void showFooDetails(Foo foo) {
		System.out.println("foo details");
		showInfo(String.format("Foo - {id : %d, desc : %s}", foo.getId(), foo.getDescription()));
	}

	public void showBarDetails(Bar bar) {
		System.out.println("bar details");
		showInfo(String.format("Bar - {id : %d, desc : %s}", bar.getId(), bar.getDescription()));
	}

	public void showInfo(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	public List<Foo> getFoos() {
		return foos;
	}

	public void setFoos(List<Foo> foos) {
		this.foos = foos;
	}

}

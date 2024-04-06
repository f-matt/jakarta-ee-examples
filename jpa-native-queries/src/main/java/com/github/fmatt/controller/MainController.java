package com.github.fmatt.controller;

import java.util.List;

import com.github.fmatt.dto.FooDto;
import com.github.fmatt.model.Foo;
import com.github.fmatt.service.FoosService;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class MainController {

	@Inject
	private FoosService foosService;

	private List<Foo> foos;

	private List<FooDto> dtos;

	@PostConstruct
	public void init() {
		foos = foosService.findEntities();
		dtos = foosService.findDtos();
	}

	public List<Foo> getFoos() {
		return foos;
	}

	public void setFoos(List<Foo> foos) {
		this.foos = foos;
	}

	public List<FooDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<FooDto> dtos) {
		this.dtos = dtos;
	}

}

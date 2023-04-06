package com.github.fmatt.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.fmatt.model.Product;
import com.github.fmatt.service.ProductsService;

@Named
@RequestScoped
public class MainController {

	@Inject
	private ProductsService productsService;

	private List<Product> products;

	@PostConstruct
	public void init() {
		products = productsService.getProducts();
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}

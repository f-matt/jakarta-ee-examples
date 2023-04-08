package com.github.fmatt.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.fmatt.model.Product;
import com.github.fmatt.service.ProductsService;

@Named
@ViewScoped
public class IndexController implements Serializable {

	@Inject
	private ProductsService productsService;

	private List<Product> products;

	private Integer page = 0;

	@PostConstruct
	public void init() {
		products = productsService.getProducts(page++);
	}

	public void refresh() {
		products = productsService.getProducts(page++);
		if (products.isEmpty()) {
			page = 0;
			products = productsService.getProducts(page++);
		}
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}

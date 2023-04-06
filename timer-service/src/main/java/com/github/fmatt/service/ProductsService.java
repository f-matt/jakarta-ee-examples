package com.github.fmatt.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import com.github.fmatt.model.Product;

@Singleton
public class ProductsService {

	@Resource
	private TimerService timerService;

    private List<Product> products;

    private Integer page = 0;

    @PostConstruct
    public void init() {
        timerService.createIntervalTimer(5000, 5000, new TimerConfig());
    }

	@Timeout
	public void productsTimerTimeout() {
		System.out.println("Products timer timeout...");
        System.out.println(this);
		products = refreshProducts(++page);
		if (products.isEmpty()) {
			page = 0;
			products = refreshProducts(page);
		}
	}

    public List<Product> refreshProducts(Integer page) {
        List<Product> products = new ArrayList<>();

        if (page > 4)
            return products;

        for (int i = 10 * page; i < 10 * page + 10; ++i) {
            products.add(new Product(i));
        }

        return products;
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

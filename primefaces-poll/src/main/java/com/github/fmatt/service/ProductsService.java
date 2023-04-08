package com.github.fmatt.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import com.github.fmatt.model.Product;

@Singleton
public class ProductsService {

    public List<Product> getProducts(Integer page) {
        List<Product> products = new ArrayList<>();

        if (page > 4)
            return products;

        for (int i = 10 * page; i < 10 * page + 10; ++i) {
            products.add(new Product(i));
        }

        return products;
    }

}

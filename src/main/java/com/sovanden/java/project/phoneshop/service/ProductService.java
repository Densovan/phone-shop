package com.sovanden.java.project.phoneshop.service;

import com.sovanden.java.project.phoneshop.entity.Product;

public interface ProductService {

    Product create(Product product);

    Product getById(Long id);
}
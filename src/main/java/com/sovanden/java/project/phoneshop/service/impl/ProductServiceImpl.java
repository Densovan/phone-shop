package com.sovanden.java.project.phoneshop.service.impl;

import org.springframework.stereotype.Service;

import com.sovanden.java.project.phoneshop.entity.Product;
import com.sovanden.java.project.phoneshop.exception.ResourceNotFoundException;
import com.sovanden.java.project.phoneshop.repository.ProductRepository;
import com.sovanden.java.project.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        String name = "%s %s"
                .formatted(product.getModel().getName(), product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

}

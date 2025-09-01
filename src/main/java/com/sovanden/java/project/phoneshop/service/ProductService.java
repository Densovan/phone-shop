package com.sovanden.java.project.phoneshop.service;

import java.math.BigDecimal;

import com.sovanden.java.project.phoneshop.dto.ProductImportDTO;
import com.sovanden.java.project.phoneshop.entity.Product;

public interface ProductService {

    Product create(Product product);

    Product getById(Long id);

    void importProduct(ProductImportDTO productImportDTO);

    void setSalePrice(Long productId, BigDecimal price);
}
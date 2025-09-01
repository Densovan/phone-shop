package com.sovanden.java.project.phoneshop.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.sovanden.java.project.phoneshop.dto.PriceDTO;
import com.sovanden.java.project.phoneshop.dto.ProductImportDTO;
import com.sovanden.java.project.phoneshop.entity.Product;
import com.sovanden.java.project.phoneshop.entity.ProductImportHistory;
import com.sovanden.java.project.phoneshop.exception.ResourceNotFoundException;
import com.sovanden.java.project.phoneshop.mapper.ProductMapper;
import com.sovanden.java.project.phoneshop.repository.ProductImportHistoryRepository;
import com.sovanden.java.project.phoneshop.repository.ProductRepository;
import com.sovanden.java.project.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;

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

    @Override
    public void importProduct(ProductImportDTO importDTO) {
        // update available unit of product
        Product product = this.getById(importDTO.getProductId());
        // Integer availableUnit = product.getAvailableUnit() +
        // importDTO.getImportUnit();
        // product.setAvailableUnit(availableUnit);
        // productRepository.save(product);
        Integer availableUnit = 0;
        if (product.getAvailableUnit() != null) {
            availableUnit = product.getAvailableUnit();
        }
        product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
        productRepository.save(product);
        // save product import history
        ProductImportHistory importHistory = productMapper.toProductImportHistory(importDTO, product);
        productImportHistoryRepository.save(importHistory);
    }

    @Override
    public void setSalePrice(Long productId, BigDecimal price) {
        Product product = this.getById(productId); // check if product exists
        product.setSalePrice(price);
        productRepository.save(product);
    }

}

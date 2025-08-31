package com.sovanden.java.project.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sovanden.java.project.phoneshop.dto.ProductDTO;
import com.sovanden.java.project.phoneshop.entity.Product;
import com.sovanden.java.project.phoneshop.mapper.ProductMapper;
import com.sovanden.java.project.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // Lombok annotation for logger
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
        log.info("Received ProductDTO: {}", productDTO);
        // Validate required fields
        if (productDTO.getModelId() == null) {
            log.error("Model ID is null in ProductDTO: {}", productDTO);
            throw new IllegalArgumentException("Model ID is required");
        }
        if (productDTO.getColorId() == null) {
            log.error("Color ID is null in ProductDTO: {}", productDTO);
            throw new IllegalArgumentException("Color ID is required");
        }
        Product product = productMapper.toProduct(productDTO);
        product = productService.create(product);
        return ResponseEntity.ok(product);
    }
}

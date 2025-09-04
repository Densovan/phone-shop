package com.sovanden.java.project.phoneshop.service;

import java.math.BigDecimal;

import com.sovanden.java.project.phoneshop.dto.ProductImportDTO;
import com.sovanden.java.project.phoneshop.entity.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    Product create(Product product);

    Product getById(Long id);

    void importProduct(ProductImportDTO productImportDTO);

    void setSalePrice(Long productId, BigDecimal price);

    void uploadProduct(MultipartFile file);

    Product getByModelIdAndColorId(Long modelId, Long colorId);
}
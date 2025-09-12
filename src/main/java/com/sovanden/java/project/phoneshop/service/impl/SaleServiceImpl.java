package com.sovanden.java.project.phoneshop.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.sovanden.java.project.phoneshop.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sovanden.java.project.phoneshop.dto.ProductSoldDTO;
import com.sovanden.java.project.phoneshop.dto.SaleDTO;
import com.sovanden.java.project.phoneshop.entity.Product;
import com.sovanden.java.project.phoneshop.entity.Sale;
import com.sovanden.java.project.phoneshop.entity.SaleDetail;
import com.sovanden.java.project.phoneshop.exception.ApiException;
import com.sovanden.java.project.phoneshop.repository.ProductRepository;
import com.sovanden.java.project.phoneshop.repository.SaleDetailRepository;
import com.sovanden.java.project.phoneshop.repository.SaleRepository;
import com.sovanden.java.project.phoneshop.service.ProductService;
import com.sovanden.java.project.phoneshop.service.SaleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    @Override
    public void sell(SaleDTO saleDTO) {
        List<Long> productIds = saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId)
                .toList();
        // List<Long> productIds = saleDTO.getProducts().stream()
        // .map(product -> product.getProductId())
        // .toList();
        // validate product
        productIds.forEach(productService::getById);

        saleDTO.getProducts().forEach(ps -> {
            Product product = productService.getById(ps.getProductId());
            if (product.getAvailableUnit() < ps.getNumberOfUnit()) {
                throw new ApiException(HttpStatus.BAD_REQUEST,
                        "Product [%s] is not enough in stock".formatted(product.getName()));
            }
        });

        // Sale
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);

        // Sale Detail
        saleDTO.getProducts().forEach(ps -> {
            Product product = productService.getById(ps.getProductId());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setSale(sale);
            saleDetail.setProduct(product);
            saleDetail.setAmount(product.getSalePrice());
            saleDetail.setUnit(ps.getNumberOfUnit());
            saleDetailRepository.save(saleDetail);

            // cut stock
            Integer availableUnit = product.getAvailableUnit() - ps.getNumberOfUnit();
            product.setAvailableUnit(availableUnit);
            productRepository.save(product);
        });

    }

    @Override
    public Sale getById(Long saleId) {
        return saleRepository.findById(saleId).orElseThrow(() -> new ResourceNotFoundException("Sale", saleId));
    }

    @Override
    public void cancelSale(Long saleId) {
        //update sale status
        Sale sale = this.getById(saleId);
        sale.setActive(false);
        saleRepository.save(sale);

        //update stock or return stock back after cancel
        List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(saleId);

        List<Long> productIds = saleDetails.stream()
                .map(sd -> sd.getProduct().getId())
                .toList();

        List<Product> products = productRepository.findAllById(productIds);

        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        saleDetails.forEach(sd -> {
            Product product = productMap.get(sd.getProduct().getId());
            product.setAvailableUnit(product.getAvailableUnit() + sd.getUnit());
            productRepository.save(product);
        });

    }

    @SuppressWarnings("unused")
    private void validate(SaleDTO saleDTO) {
        saleDTO.getProducts().forEach(ps -> {
            Product product = productService.getById(ps.getProductId());
            if (product.getAvailableUnit() < ps.getNumberOfUnit()) {
                throw new ApiException(HttpStatus.BAD_REQUEST,
                        "Product [%s] is not enough in stock".formatted(product.getName()));
            }
        });
    }

    @SuppressWarnings("unused")
    private void validateV2(SaleDTO saleDTO) {
        List<Long> productIds = saleDTO.getProducts().stream().map(product -> product.getProductId()).toList(); // lambda
        // expression
        // explicit
        // List<Long> productIds =
        // saleDTO.getProducts().stream().map(ProductSoldDTO::getProductId).toList();
        // method reference (shorthand)

        // Validate if product exists
        productIds.forEach(id -> productService.getById(id)); // lambda expression explicit
        // productIds.forEach(productService::getById); // method reference (shorthand)

        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        // validate stock of each products
        saleDTO.getProducts().forEach(ps -> {
            Product product = productMap.get(ps.getProductId());
            if (product.getAvailableUnit() < ps.getNumberOfUnit()) {
                throw new ApiException(HttpStatus.BAD_REQUEST,
                        "Product [%s] is not enough in stock".formatted(product.getName()));
            }
        });

    }

}

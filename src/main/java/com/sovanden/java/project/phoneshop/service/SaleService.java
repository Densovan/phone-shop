package com.sovanden.java.project.phoneshop.service;

import com.sovanden.java.project.phoneshop.dto.SaleDTO;
import com.sovanden.java.project.phoneshop.entity.Sale;

public interface SaleService {
    void sell(SaleDTO saleDTO);
    Sale getById(Long saleId);
    void cancelSale(Long saleId);
}

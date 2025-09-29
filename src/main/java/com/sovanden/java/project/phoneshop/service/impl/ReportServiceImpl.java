package com.sovanden.java.project.phoneshop.service.impl;

import com.sovanden.java.project.phoneshop.dto.ProductReportDTO;
import com.sovanden.java.project.phoneshop.projection.ProductSold;
import com.sovanden.java.project.phoneshop.repository.SaleRepository;
import com.sovanden.java.project.phoneshop.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SaleRepository saleRepository;

    @Override
    public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate, endDate);
    }

    @Override
    public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
        return List.of();
    }
}

package com.sovanden.java.project.phoneshop.service;

import com.sovanden.java.project.phoneshop.dto.ProductReportDTO;
import com.sovanden.java.project.phoneshop.projection.ProductSold;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);

    List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate);

}

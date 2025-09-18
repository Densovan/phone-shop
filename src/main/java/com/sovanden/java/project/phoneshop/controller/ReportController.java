package com.sovanden.java.project.phoneshop.controller;

import com.sovanden.java.project.phoneshop.projection.ProductSold;
import com.sovanden.java.project.phoneshop.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("reports")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("{startDate}/{endDate}")
    public ResponseEntity<?> productSold(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {
        List<ProductSold> productSold = reportService.getProductSold(startDate, endDate);
        return ResponseEntity.ok(productSold);
    }
}

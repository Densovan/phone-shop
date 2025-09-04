package com.sovanden.java.project.phoneshop.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sovanden.java.project.phoneshop.exception.ApiException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sovanden.java.project.phoneshop.dto.ProductImportDTO;
import com.sovanden.java.project.phoneshop.entity.Product;
import com.sovanden.java.project.phoneshop.entity.ProductImportHistory;
import com.sovanden.java.project.phoneshop.exception.ResourceNotFoundException;
import com.sovanden.java.project.phoneshop.mapper.ProductMapper;
import com.sovanden.java.project.phoneshop.repository.ProductImportHistoryRepository;
import com.sovanden.java.project.phoneshop.repository.ProductRepository;
import com.sovanden.java.project.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product create(Product product) {
        String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", id));
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

    @Override
    public void uploadProduct(MultipartFile file) {
        Map<Integer, String> map = new HashMap<>();
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("products");
            Iterator<Row> rowIterator = sheet.iterator();

            rowIterator.next(); //: @TODO improve checking error

            while (rowIterator.hasNext()) {
                Integer rowNumber = 0;
                try {
                    Row row = rowIterator.next();
                    int cellIndex = 0;

                    Cell cellNo = row.getCell(cellIndex++);
                    rowNumber = (int) cellNo.getNumericCellValue();

                    Cell cellModelId = row.getCell(cellIndex++);
                    Long modelId = (long) cellModelId.getNumericCellValue();

                    Cell cellColorId = row.getCell(cellIndex++);
                    Long colorId = (long) cellColorId.getNumericCellValue();

                    Cell cellImportPrice = row.getCell(cellIndex++);
                    Double importPrice = cellImportPrice.getNumericCellValue();

                    Cell cellImportUnit = row.getCell(cellIndex++);
                    Integer importUnit = (int) cellImportUnit.getNumericCellValue();
                    if (importUnit < 1) {
                        throw new ApiException(HttpStatus.BAD_REQUEST, "Unit must be greater than 0");
                    }

                    Cell cellImportDate = row.getCell(cellIndex++);
                    LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();

                    Product product = getByModelIdAndColorId(modelId, colorId);


                    //System.out.println(modelId);
                    Integer availableUnit = 0;
                    if (product.getAvailableUnit() != null) {
                        availableUnit = product.getAvailableUnit();
                    }
                    product.setAvailableUnit(availableUnit + importUnit);
                    productRepository.save(product);

                    // save product import history
                    //ProductImportHistory importHistory = productMapper.toProductImportHistory(importDTO, product);
                    ProductImportHistory importHistory = new ProductImportHistory();
                    importHistory.setDateImport(importDate);
                    importHistory.setImportUnit(importUnit);
                    importHistory.setPricePerUnit(BigDecimal.valueOf(importPrice));
                    importHistory.setProduct(product);
                    productImportHistoryRepository.save(importHistory);


                } catch (Exception e) {
                    map.put(rowNumber, e.getMessage());
                }
                Row row = rowIterator.next();
                Cell cellModelId = row.getCell(0);
                Long modelId = (long) cellModelId.getNumericCellValue();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getByModelIdAndColorId(Long modelId, Long colorId) {
        String text = "Product with model id =%s and color id = %d was not found";
        return productRepository.findByModelIdAndColorId(modelId, colorId).orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, text.formatted(modelId, colorId)));
    }

}

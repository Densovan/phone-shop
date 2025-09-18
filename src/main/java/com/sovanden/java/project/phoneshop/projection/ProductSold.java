package com.sovanden.java.project.phoneshop.projection;

import java.math.BigDecimal;

public interface ProductSold {
    Long getProductId();
    String productName();
    Integer productUnit();
    BigDecimal getTotalAmount();
}

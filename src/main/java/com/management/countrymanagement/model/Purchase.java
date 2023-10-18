package com.management.countrymanagement.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Purchase {

    private ProductPurchase product;
    private BigDecimal amount;
    private BigDecimal unitPrice;
    private String unitName;
}

package com.management.countrymanagement.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Purchase {

    private ProductDocument product;
    private BigDecimal amount;
}

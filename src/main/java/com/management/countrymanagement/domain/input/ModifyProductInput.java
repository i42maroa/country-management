package com.management.countrymanagement.domain.input;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ModifyProductInput {
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private String exchangeTypePredefined;
}

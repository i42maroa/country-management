package com.management.countrymanagement.model.input;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ModifyProductInput {
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private String exchangeTypePredefined;
    private String unitName;
}

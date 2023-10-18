package com.management.countrymanagement.model.input;

import com.management.countrymanagement.domain.enums.ExchangeType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewProductInput {
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private String exchangeTypePredefined;
    private String unitName;
}

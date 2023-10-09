package com.management.countrymanagement.domain.input;

import com.management.countrymanagement.domain.enums.ExchangeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInputQuery {
    private String name;
    private ExchangeType exchangeTypePredifined;
}

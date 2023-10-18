package com.management.countrymanagement.model.input;

import com.management.countrymanagement.domain.enums.ExchangeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInputQuery {
    private String name;
    private ExchangeType exchangeTypePredifined;
    private OffsetDateTime createdProductDateGte;
    private OffsetDateTime createdProductDateLte;
    private OffsetDateTime lastModificationDateGte;
    private OffsetDateTime lastModificationDateLte;
}

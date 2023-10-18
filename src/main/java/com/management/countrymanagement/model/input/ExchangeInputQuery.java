package com.management.countrymanagement.model.input;


import com.management.countrymanagement.domain.enums.ExchangeType;
import com.management.countrymanagement.domain.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeInputQuery {

    private ExchangeType exchangeType;
    private OperationType operationType;
    private OffsetDateTime creationDateGte;
    private OffsetDateTime creationProductDateLte;
    private OffsetDateTime lastModificationDateGte;
    private OffsetDateTime lastModificationDateLte;
}

package com.management.countrymanagement.domain.input;

import com.management.countrymanagement.domain.Purchase;
import com.management.countrymanagement.domain.enums.ExchangeType;
import com.management.countrymanagement.domain.enums.OperationType;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class NewExchangeInput {

    private String operationType;
    private String exchangeType;
    private String description;
    private List<Purchase> purchases;

}

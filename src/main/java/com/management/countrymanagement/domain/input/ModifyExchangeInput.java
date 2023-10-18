package com.management.countrymanagement.domain.input;

import com.management.countrymanagement.domain.Purchase;
import com.management.countrymanagement.domain.enums.ExchangeType;
import com.management.countrymanagement.domain.enums.OperationType;
import lombok.Data;

import java.util.List;

@Data
public class ModifyExchangeInput {
    private String operationType;
    private String exchangeType;
    private String description;
    private List<Purchase> purchases;

}

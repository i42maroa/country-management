package com.management.countrymanagement.model.input;

import com.management.countrymanagement.model.Purchase;
import lombok.Data;

import java.util.List;

@Data
public class ModifyExchangeInput {
    private String operationType;
    private String exchangeType;
    private String description;
    private List<Purchase> purchases;

}

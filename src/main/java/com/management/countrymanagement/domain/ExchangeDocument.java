package com.management.countrymanagement.domain;

import com.management.countrymanagement.domain.enums.ExchangeType;
import com.management.countrymanagement.domain.enums.OperationType;
import com.management.countrymanagement.domain.input.ModifyExchangeInput;
import com.management.countrymanagement.domain.input.NewExchangeInput;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Document(collection = "exchanges")
@Data
public class ExchangeDocument {

    @MongoId
    public ObjectId id;
    private OperationType operationType;
    private ExchangeType exchangeType;
    private OffsetDateTime creationDate;
    private OffsetDateTime lastModificationDate;
    private String description;
    private List<Purchase> purchases;

    public ExchangeDocument(NewExchangeInput productInput){
        var currentDate = OffsetDateTime.now();
        Optional.ofNullable(productInput.getDescription()).map(v -> this.description = v);
        Optional.ofNullable(productInput.getOperationType()).map(v -> this.operationType = OperationType.valueOf(v));
        Optional.ofNullable(productInput.getExchangeType()).map(v -> this.exchangeType = ExchangeType.valueOf(v));
        this.creationDate = currentDate;
        this.lastModificationDate = currentDate;
        Optional.ofNullable(productInput.getPurchases()).map(v ->  purchases.addAll(v));
    }

    public ExchangeDocument(ModifyExchangeInput productInput){
        Optional.ofNullable(productInput.getDescription()).map(v -> this.description = v);
        Optional.ofNullable(productInput.getOperationType()).map(v -> this.operationType = OperationType.valueOf(v));
        Optional.ofNullable(productInput.getExchangeType()).map(v -> this.exchangeType = ExchangeType.valueOf(v));
        this.lastModificationDate = OffsetDateTime.now();
        Optional.ofNullable(productInput.getPurchases()).map(v ->  purchases.addAll(v));
    }

}
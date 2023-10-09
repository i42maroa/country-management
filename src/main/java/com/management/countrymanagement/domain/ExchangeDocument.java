package com.management.countrymanagement.domain;

import com.management.countrymanagement.domain.enums.ExchangeType;
import com.management.countrymanagement.domain.enums.OperationType;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;
import java.util.List;

@Document(collection = "exchanges")
@Data
public class ExchangeDocument {

    @Id
    @Field("_id")
    public ObjectId id;
    private OperationType operationType;
    private ExchangeType exchangeType;
    private OffsetDateTime date;
    private String description;
    private List<Purchase> purchases;

}
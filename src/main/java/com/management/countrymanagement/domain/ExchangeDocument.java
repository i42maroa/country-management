package com.management.countrymanagement.domain;

import com.management.countrymanagement.domain.enums.ExchangeType;
import com.management.countrymanagement.domain.enums.OperationType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Document(collection = "exchanges")
public class ExchangeDocument {

    @Id
    @Field("_id")
    public ObjectId id;

    private OperationType operationType;
    private OffsetDateTime date;
    private String description;
    private ExchangeType exchangeType;
    private BigDecimal amount;

}
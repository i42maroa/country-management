package com.management.countrymanagement.domain;

import com.management.countrymanagement.domain.enums.ExchangeType;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Document(collection = "products")
@Data
public class ProductDocument {
    @Id
    @Field("_id")
    private ObjectId id;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private ExchangeType exchangeTypePredefined;
    private OffsetDateTime createdProductDate;
    private OffsetDateTime lastModificationDate;
}

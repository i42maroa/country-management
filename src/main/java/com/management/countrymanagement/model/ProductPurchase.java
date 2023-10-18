package com.management.countrymanagement.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
public class ProductPurchase {

    @MongoId
    private ObjectId id;
    private String name;
}

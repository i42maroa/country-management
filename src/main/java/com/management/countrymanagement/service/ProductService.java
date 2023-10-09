package com.management.countrymanagement.service;

import com.management.countrymanagement.domain.ProductDocument;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductDocument> getProduct(ObjectId id);
}

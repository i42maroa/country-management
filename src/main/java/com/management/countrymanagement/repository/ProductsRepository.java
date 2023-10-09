package com.management.countrymanagement.repository;

import com.management.countrymanagement.domain.ProductDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductsRepository extends ReactiveMongoRepository<ProductDocument, ObjectId> {

}
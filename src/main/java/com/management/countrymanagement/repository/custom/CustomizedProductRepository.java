package com.management.countrymanagement.repository.custom;

import com.management.countrymanagement.domain.ProductDocument;
import com.management.countrymanagement.domain.input.ModifyProductInput;
import com.management.countrymanagement.domain.input.NewProductInput;
import com.management.countrymanagement.domain.input.ProductInputQuery;
import com.mongodb.bulk.BulkWriteResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomizedProductRepository extends CustomizedBaseRepository {

    Flux<ProductDocument> productsByFilters(ProductInputQuery filters, Pageable pageable);

    Mono<Long> countProducts(ProductInputQuery filters);


    Mono<BulkWriteResult> modifyProductById(ObjectId id, ModifyProductInput productInput);

}

package com.management.countrymanagement.service;

import com.management.countrymanagement.domain.ProductDocument;
import com.management.countrymanagement.model.input.ModifyProductInput;
import com.management.countrymanagement.model.input.NewProductInput;
import com.management.countrymanagement.model.input.ProductInputQuery;
import com.management.countrymanagement.model.output.Pagination;
import com.mongodb.bulk.BulkWriteResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductDocument> getProduct(ObjectId id);

    Mono<Pagination<ProductDocument>> getProductsPage(ProductInputQuery filters, Pageable pageable);

    Mono<ProductDocument> createProduct(NewProductInput productInput);

    Mono<BulkWriteResult> modifyProduct(ObjectId id, ModifyProductInput productInput);

    Mono<Void> deleteProduct(ObjectId id);
}

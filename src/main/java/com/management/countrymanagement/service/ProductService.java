package com.management.countrymanagement.service;

import com.management.countrymanagement.domain.ProductDocument;
import com.management.countrymanagement.domain.input.ProductInputQuery;
import com.management.countrymanagement.domain.output.Pagination;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductDocument> getProduct(ObjectId id);

    Mono<Pagination<ProductDocument>> getProductsPage(ProductInputQuery filters, Pageable pageable);


}

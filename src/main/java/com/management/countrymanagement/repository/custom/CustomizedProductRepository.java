package com.management.countrymanagement.repository.custom;

import com.management.countrymanagement.domain.ProductDocument;
import com.management.countrymanagement.domain.input.ProductInputQuery;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomizedProductRepository extends CustomizedBaseRepository {

    Flux<ProductDocument> productsByFilters(ProductInputQuery filters, Pageable pageable);

    Mono<Long> countProducts(ProductInputQuery filters);
}

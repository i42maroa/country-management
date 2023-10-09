package com.management.countrymanagement.repository.custom.impl;

import com.management.countrymanagement.constant.GraphqlApiConstant;
import com.management.countrymanagement.domain.ProductDocument;
import com.management.countrymanagement.domain.input.ProductInputQuery;
import com.management.countrymanagement.repository.custom.CustomizedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class CustomizedProductRepositoryImpl implements CustomizedProductRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<ProductDocument> productsByFilters(ProductInputQuery filters, Pageable pageable) {
        var criteria = getCriteria(filters, this::generateCriteriaProductFilter);
        var query = new Query()
                .with(pageable)
                .addCriteria(criteria);
        return reactiveMongoTemplate.find(query, ProductDocument.class);
    }

    @Override
    @Cacheable(value = GraphqlApiConstant.COUNT_QUERY_CACHE)
    public Mono<Long> countProducts(ProductInputQuery filters) {
        var criteria = getCriteria(filters, this::generateCriteriaProductFilter);
        var query = new Query()
                .addCriteria(criteria);
        return reactiveMongoTemplate.count(query, ProductDocument.class);
    }


    private List<Criteria> generateCriteriaProductFilter(ProductInputQuery filters) {
        return Stream.of(
                        equalsCriteria("name", filters.getName()),
                        equalsCriteria("exchangeTypePredifined", filters.getExchangeTypePredifined())
                ).flatMap(Function.identity())
                .collect(Collectors.toList());
    }
}

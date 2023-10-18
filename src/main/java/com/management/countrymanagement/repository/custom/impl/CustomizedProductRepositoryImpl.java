package com.management.countrymanagement.repository.custom.impl;

import com.management.countrymanagement.constant.GraphqlApiConstant;
import com.management.countrymanagement.constant.MongoCollections;
import com.management.countrymanagement.domain.ProductDocument;
import com.management.countrymanagement.model.input.ModifyProductInput;
import com.management.countrymanagement.model.input.ProductInputQuery;
import com.management.countrymanagement.repository.BulkWriteRepository;
import com.management.countrymanagement.repository.custom.CustomizedProductRepository;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.model.UpdateOneModel;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class CustomizedProductRepositoryImpl implements CustomizedProductRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final BulkWriteRepository bulkWriteRepository;

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

    @Override
    public Mono<BulkWriteResult> modifyProductById(ObjectId id, ModifyProductInput productInput) {
        var updateOne = new UpdateOneModel<Document>(generateIdFilter(id), generateListUpdate(productInput));
        return bulkWriteRepository.bulkWriteCollection(MongoCollections.PRODUCTS, List.of(updateOne));
    }


    private List<Criteria> generateCriteriaProductFilter(ProductInputQuery filters) {
        return Stream.of(
                        equalsCriteria("name", filters.getName()),
                        equalsCriteria("exchangeTypePredefined", filters.getExchangeTypePredifined()),
                        rangeCriteria("lastModificationDate", filters.getLastModificationDateGte(), filters.getLastModificationDateLte()),
                        rangeCriteria("createdProductDate", filters.getCreatedProductDateGte(), filters.getCreatedProductDateLte())
                ).flatMap(Function.identity())
                .collect(Collectors.toList());
    }

    private List<Bson> generateListUpdate(ModifyProductInput modifyProductInput) {
        return Stream.of(
                        equalsUpdate("name", modifyProductInput.getName()),
                        equalsUpdate("description", modifyProductInput.getDescription()),
                        equalsUpdate("unitPrice", modifyProductInput.getUnitPrice()),
                        equalsUpdate("unitName", modifyProductInput.getUnitName()),
                        equalsUpdate("exchangeTypePredefined", modifyProductInput.getExchangeTypePredefined()),
                        equalsUpdate("lastModificationDate", OffsetDateTime.now())
                ).flatMap(Function.identity())
                .collect(Collectors.toList());
    }
}

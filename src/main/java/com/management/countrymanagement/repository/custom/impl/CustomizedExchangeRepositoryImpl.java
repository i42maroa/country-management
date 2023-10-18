package com.management.countrymanagement.repository.custom.impl;

import com.management.countrymanagement.constant.MongoCollections;
import com.management.countrymanagement.domain.ExchangeDocument;
import com.management.countrymanagement.domain.ProductDocument;
import com.management.countrymanagement.domain.input.ExchangeInputQuery;
import com.management.countrymanagement.domain.input.ModifyExchangeInput;
import com.management.countrymanagement.repository.BulkWriteRepository;
import com.management.countrymanagement.repository.custom.CustomizedExchangeRepository;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.model.UpdateOneModel;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
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
public class CustomizedExchangeRepositoryImpl implements CustomizedExchangeRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final BulkWriteRepository bulkWriteRepository;

    @Override
    public Flux<ExchangeDocument> exchangesByFilters(ExchangeInputQuery filters, Pageable pageable) {
        var criteria = getCriteria(filters, this::generateCriteriaExchangeFilter);
        var query = new Query()
                .with(pageable)
                .addCriteria(criteria);
        return reactiveMongoTemplate.find(query, ExchangeDocument.class);
    }

    @Override
    public Mono<Long> countExchanges(ExchangeInputQuery filters) {
        var criteria = getCriteria(filters, this::generateCriteriaExchangeFilter);
        var query = new Query()
                .addCriteria(criteria);
        return reactiveMongoTemplate.count(query, ExchangeDocument.class);
    }

    @Override
    public Mono<BulkWriteResult> modifyExchangeById(ObjectId id, ModifyExchangeInput productInput) {
        var updateOne = new UpdateOneModel<Document>(generateIdFilter(id), generateListUpdate(productInput));
        return bulkWriteRepository.bulkWriteCollection(MongoCollections.EXCHANGE, List.of(updateOne));
    }

    private List<Criteria> generateCriteriaExchangeFilter(ExchangeInputQuery filters) {
        return Stream.of(
                        equalsCriteria("exchangeType", filters.getExchangeType()),
                        equalsCriteria("operationType", filters.getOperationType()),
                        rangeCriteria("lastModificationDate", filters.getLastModificationDateGte(), filters.getLastModificationDateLte()),
                        rangeCriteria("creationDate", filters.getCreationDateGte(), filters.getCreationProductDateLte())
                ).flatMap(Function.identity())
                .collect(Collectors.toList());
    }

    private List<Bson> generateListUpdate(ModifyExchangeInput modifyProductInput) {
        return Stream.of(
                        equalsUpdate("description", modifyProductInput.getDescription()),
                        equalsUpdate("exchangeType", modifyProductInput.getExchangeType()),
                        equalsUpdate("operationType", modifyProductInput.getOperationType()),
                        equalsUpdate("lastModificationDate", OffsetDateTime.now()),
                        equalsUpdate("purchases", modifyProductInput.getPurchases())

                ).flatMap(Function.identity())
                .collect(Collectors.toList());
    }
}

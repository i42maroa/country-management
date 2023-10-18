package com.management.countrymanagement.repository.custom;

import com.management.countrymanagement.domain.ExchangeDocument;
import com.management.countrymanagement.model.input.ExchangeInputQuery;
import com.management.countrymanagement.model.input.ModifyExchangeInput;
import com.mongodb.bulk.BulkWriteResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomizedExchangeRepository extends CustomizedBaseRepository {

    Flux<ExchangeDocument> exchangesByFilters(ExchangeInputQuery filters, Pageable pageable);

    Mono<Long> countExchanges(ExchangeInputQuery filters);

    Mono<BulkWriteResult> modifyExchangeById(ObjectId id, ModifyExchangeInput productInput);

}
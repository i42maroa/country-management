package com.management.countrymanagement.service;

import com.management.countrymanagement.domain.ExchangeDocument;
import com.management.countrymanagement.model.input.ExchangeInputQuery;
import com.management.countrymanagement.model.input.ModifyExchangeInput;
import com.management.countrymanagement.model.input.NewExchangeInput;
import com.management.countrymanagement.model.output.Pagination;
import com.mongodb.bulk.BulkWriteResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface ExchangeService {

    Mono<ExchangeDocument> getExchange(ObjectId id);

    Mono<Pagination<ExchangeDocument>> getExchangePage(ExchangeInputQuery filters, Pageable pageable);

    Mono<ExchangeDocument> createExchange(NewExchangeInput productInput);

    Mono<BulkWriteResult> modifyExchange(ObjectId id, ModifyExchangeInput productInput);

    Mono<Void> deleteExchange(ObjectId id);
}

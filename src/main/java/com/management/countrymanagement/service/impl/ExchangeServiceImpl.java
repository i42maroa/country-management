package com.management.countrymanagement.service.impl;

import com.management.countrymanagement.domain.ExchangeDocument;
import com.management.countrymanagement.domain.input.ExchangeInputQuery;
import com.management.countrymanagement.domain.input.ModifyExchangeInput;
import com.management.countrymanagement.domain.input.NewExchangeInput;
import com.management.countrymanagement.domain.output.Pagination;
import com.management.countrymanagement.repository.ExchangesRepository;
import com.management.countrymanagement.service.ExchangeService;
import com.mongodb.bulk.BulkWriteResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangesRepository exchangesRepository;

    @Override
    public Mono<ExchangeDocument> getExchange(ObjectId id) {
        return exchangesRepository.findById(id);
    }

    @Override
    public Mono<Pagination<ExchangeDocument>> getExchangePage(ExchangeInputQuery filters, Pageable pageable) {
        var pageMono = exchangesRepository.exchangesByFilters(filters, pageable).collectList();
        var countMono = exchangesRepository.countExchanges(filters);

        return Mono.zip(pageMono, countMono, (items, count) -> new Pagination<>(items, pageable, count));
    }

    @Override
    public Mono<ExchangeDocument> createExchange(NewExchangeInput productInput) {
        var product = new ExchangeDocument(productInput);
        //TODO Check already exits
        return exchangesRepository.save(product);
    }

    @Override
    public Mono<BulkWriteResult> modifyExchange(ObjectId id, ModifyExchangeInput exchangeInput) {
        return exchangesRepository.modifyExchangeById(id, exchangeInput);
    }

    @Override
    public Mono<Void> deleteExchange(ObjectId id) {
        return exchangesRepository.deleteById(id);
    }
}

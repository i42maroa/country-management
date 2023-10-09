package com.management.countrymanagement.service.impl;

import com.management.countrymanagement.domain.ExchangeDocument;
import com.management.countrymanagement.repository.ExchangesRepository;
import com.management.countrymanagement.service.ExchangeService;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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
}

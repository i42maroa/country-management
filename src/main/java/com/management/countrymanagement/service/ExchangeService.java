package com.management.countrymanagement.service;

import com.management.countrymanagement.domain.ExchangeDocument;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

public interface ExchangeService {

    Mono<ExchangeDocument> getExchange(ObjectId id);
}

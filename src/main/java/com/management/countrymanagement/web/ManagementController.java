package com.management.countrymanagement.web;

import com.management.countrymanagement.domain.ExchangeDocument;
import com.management.countrymanagement.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Controller
@RequiredArgsConstructor
public class ManagementController {

    private final ExchangeService exchangeService;

    @QueryMapping
    public Mono<ExchangeDocument> exchange(@Argument ObjectId id) {
        return exchangeService.getExchange(id).subscribeOn(Schedulers.boundedElastic());
    }
}

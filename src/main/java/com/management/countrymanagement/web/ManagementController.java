package com.management.countrymanagement.web;

import com.management.countrymanagement.domain.ExchangeDocument;
import com.management.countrymanagement.model.input.ExchangeInputQuery;
import com.management.countrymanagement.model.input.ModifyExchangeInput;
import com.management.countrymanagement.model.input.NewExchangeInput;
import com.management.countrymanagement.model.output.Pagination;
import com.management.countrymanagement.service.ExchangeService;
import com.mongodb.bulk.BulkWriteResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Controller
@RequiredArgsConstructor
public class ManagementController implements BaseController {

    private final ExchangeService exchangeService;

    @QueryMapping
    public Mono<ExchangeDocument> exchange(@Argument ObjectId id) {
        return exchangeService.getExchange(id).subscribeOn(Schedulers.boundedElastic());
    }

    @QueryMapping
    public Mono<Pagination<ExchangeDocument>> exchangePage(@Argument ExchangeInputQuery input, @Argument Integer page, @Argument Integer size) {
        return exchangeService.getExchangePage(input, getPageRequest(page, size)).subscribeOn(Schedulers.boundedElastic());
    }

    @MutationMapping
    public Mono<ExchangeDocument> createExchange(@Argument NewExchangeInput exchangeInput) {
        return exchangeService.createExchange(exchangeInput).subscribeOn(Schedulers.boundedElastic());
    }

    @MutationMapping
    public Mono<BulkWriteResult> modifyExchange(@Argument ObjectId id, @Argument ModifyExchangeInput exchangeInput) {
        return exchangeService.modifyExchange(id, exchangeInput).subscribeOn(Schedulers.boundedElastic());
    }

    @MutationMapping
    public Mono<Void> deleteExchange(@Argument ObjectId id) {
        return exchangeService.deleteExchange(id).subscribeOn(Schedulers.boundedElastic());
    }
}

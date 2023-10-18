package com.management.countrymanagement.repository;

import com.management.countrymanagement.domain.ExchangeDocument;
import com.management.countrymanagement.repository.custom.CustomizedExchangeRepository;
import com.management.countrymanagement.repository.custom.CustomizedProductRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ExchangesRepository extends ReactiveMongoRepository<ExchangeDocument, ObjectId>, CustomizedExchangeRepository {

}

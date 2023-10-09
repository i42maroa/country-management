package com.management.countrymanagement.repository;

import com.management.countrymanagement.domain.ExchangeDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ExchangesRepository extends ReactiveMongoRepository<ExchangeDocument, ObjectId> {

}

package com.management.countrymanagement.repository.impl;

import com.management.countrymanagement.repository.BulkWriteRepository;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.model.WriteModel;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BulkWriteRepositoryImpl implements BulkWriteRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<BulkWriteResult> bulkWriteCollection(String collectionName, List<WriteModel<Document>> writeModels) {
        return reactiveMongoTemplate.getCollection(collectionName)
                .flatMap(collection -> Mono.from(collection.bulkWrite(writeModels)));
    }

}

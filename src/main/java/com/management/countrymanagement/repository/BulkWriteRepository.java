package com.management.countrymanagement.repository;

import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.model.WriteModel;
import org.bson.Document;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BulkWriteRepository {

     Mono<BulkWriteResult> bulkWriteCollection(String collectionName, List<WriteModel<Document>> writeModels);
}

package com.management.countrymanagement.repository.custom;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface CustomizedBaseRepository {

    default <T> Criteria getCriteria(T filters, Function<T, List<Criteria>> criteriaList) {
        return Optional.ofNullable(filters)
                .map(criteriaList)
                .filter(Predicate.not(List::isEmpty))
                .map(criteria -> new Criteria().andOperator(criteria))
                .orElseGet(Criteria::new);
    }

    default <T> Stream<Criteria> rangeCriteria(String field, T gte, T lte) {
        return Optional.of(field)
                .filter(f -> Objects.nonNull(gte) && Objects.nonNull(lte))
                .map(range -> new Criteria().andOperator(new Criteria(field).gte(gte), new Criteria(field).lte(lte)))
                .or(() -> Optional.ofNullable(gte).map(value -> new Criteria(field).gte(value)))
                .or(() -> Optional.ofNullable(lte).map(value -> new Criteria(field).lte(value)))
                .stream();
    }

    default <T> Stream<Criteria> equalsCriteria(String field, T value) {
        return Stream.ofNullable(value)
                .map(filter -> new Criteria(field).is(filter));
    }

    default <T> Stream<Criteria> inCriteria(String field, List<T> value) {
        return Stream.ofNullable(value)
                .filter(Predicate.not(Collection::isEmpty))
                .map(filter -> new Criteria(field).in(filter));
    }


    default <T> Stream<Bson> equalsUpdate(String field, T value) {
        return Stream.ofNullable(value)
                .map(fieldToUpdate -> Updates.set(field, value));
    }

    default Stream<Bson> equalsUpdate(String field, OffsetDateTime value) {
        return Stream.ofNullable(value)
                .map(fieldToUpdate -> new Document().append("$set", Document.parse("{\""+field+"\":{\"$date\":\"" +value.toString()+"\"}}")));
    }

    default Bson generateIdFilter(ObjectId id) {
        return Filters.eq("_id", id);
    }
}

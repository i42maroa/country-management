package com.management.countrymanagement.service.impl;

import com.management.countrymanagement.domain.ProductDocument;
import com.management.countrymanagement.domain.input.ModifyProductInput;
import com.management.countrymanagement.domain.input.NewProductInput;
import com.management.countrymanagement.domain.input.ProductInputQuery;
import com.management.countrymanagement.domain.output.Pagination;
import com.management.countrymanagement.repository.ProductsRepository;
import com.management.countrymanagement.service.ProductService;
import com.mongodb.bulk.BulkWriteResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;

    @Override
    public Mono<ProductDocument> getProduct(ObjectId id) {
        return productsRepository.findById(id);
    }


    @Override
    public Mono<Pagination<ProductDocument>> getProductsPage(ProductInputQuery filters, Pageable pageable) {
        var pageMono = productsRepository.productsByFilters(filters, pageable).collectList();
        var countMono = productsRepository.countProducts(filters);

        return Mono.zip(pageMono, countMono, (items, count) -> new Pagination<>(items, pageable, count));
    }

    @Override
    public Mono<ProductDocument> createProduct(NewProductInput productInput) {
        var product = new ProductDocument(productInput);
        //TODO Check already exits
        return productsRepository.save(product);
    }

    @Override
    public Mono<BulkWriteResult> modifyProduct(ObjectId id, ModifyProductInput productInput) {
        return productsRepository.modifyProductById(id,productInput);
    }

    @Override
    public Mono<Void> deleteProduct(ObjectId id) {
        return productsRepository.deleteById(id);
    }
}


package com.management.countrymanagement.service.impl;

import com.management.countrymanagement.domain.ProductDocument;
import com.management.countrymanagement.domain.input.ProductInputQuery;
import com.management.countrymanagement.domain.output.Pagination;
import com.management.countrymanagement.repository.ProductsRepository;
import com.management.countrymanagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
}


package com.management.countrymanagement.web;

import com.management.countrymanagement.domain.ProductDocument;
import com.management.countrymanagement.domain.input.ProductInputQuery;
import com.management.countrymanagement.domain.output.Pagination;
import com.management.countrymanagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Controller
@RequiredArgsConstructor
public class ProductController implements BaseController{
    private final ProductService productService;

    @QueryMapping
    public Mono<ProductDocument> product(@Argument ObjectId id) {
        return productService.getProduct(id).subscribeOn(Schedulers.boundedElastic());
    }

    @QueryMapping
    public Mono<Pagination<ProductDocument>> productsPage(@Argument ProductInputQuery input, @Argument Integer page, @Argument Integer size) {
        return productService.getProductsPage(input, getPageRequest(page, size)).subscribeOn(Schedulers.boundedElastic());
    }
}

package com.management.countrymanagement.web;

import com.management.countrymanagement.domain.ProductDocument;
import com.management.countrymanagement.model.input.ModifyProductInput;
import com.management.countrymanagement.model.input.NewProductInput;
import com.management.countrymanagement.model.input.ProductInputQuery;
import com.management.countrymanagement.model.output.Pagination;
import com.management.countrymanagement.service.ProductService;
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

    @MutationMapping
    public Mono<ProductDocument> createProduct(@Argument NewProductInput productInput) {
        return productService.createProduct(productInput).subscribeOn(Schedulers.boundedElastic());
    }

    @MutationMapping
    public Mono<BulkWriteResult> modifyProduct(@Argument ObjectId id, @Argument ModifyProductInput productInput) {
        return productService.modifyProduct(id, productInput).subscribeOn(Schedulers.boundedElastic());
    }

    @MutationMapping
    public Mono<Void> deleteProduct(@Argument ObjectId id) {
        return productService.deleteProduct(id).subscribeOn(Schedulers.boundedElastic());
    }
}

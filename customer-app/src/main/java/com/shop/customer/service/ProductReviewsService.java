package com.shop.customer.service;

import com.shop.customer.entity.ProductReview;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductReviewsService {

    Mono<ProductReview> createProductReview(int productId, int rating, String review);

    Flux<ProductReview> findProductReviewsByProduct(int productId);
}

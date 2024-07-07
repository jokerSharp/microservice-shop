package com.shop.feedback.repository;

import com.shop.feedback.entity.ProductReview;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface ProductReviewsRepository extends ReactiveCrudRepository<ProductReview, UUID> {

    Flux<ProductReview> findAllByProductId(int productId);
}

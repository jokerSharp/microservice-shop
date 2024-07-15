package com.shop.feedback.service;

import com.shop.feedback.entity.ProductReview;
import com.shop.feedback.repository.ProductReviewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultProductReviewsService implements ProductReviewsService {

    private final ProductReviewsRepository productReviewsRepository;

    @Override
    public Mono<ProductReview> createProductReview(int productId, int rating, String review, String userId) {
        Mono<ProductReview> productReviewMono = this.productReviewsRepository.save(
                new ProductReview(UUID.randomUUID(), productId, rating, review, userId));
        productReviewMono.doOnNext(mono -> log.info("createProductReview method saving productReviewMono{}: ", mono)).subscribe();
        return productReviewMono;
    }

    @Override
    public Flux<ProductReview> findProductReviewsByProduct(int productId) {
        log.info("findProductReviewsByProduct method called with productId {}", productId);
        return this.productReviewsRepository.findAllByProductId(productId);
    }
}

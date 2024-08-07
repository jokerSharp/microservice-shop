package com.shop.feedback.repository;

import com.shop.feedback.entity.FavouriteProduct;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface FavouriteProductsRepository extends ReactiveCrudRepository<FavouriteProduct, UUID> {

    Flux<FavouriteProduct> findAllByUserId(String userId);

    Mono<Void> deleteByProductIdAndUserId(int productId, String userId);

    Mono<FavouriteProduct> findByProductIdAndUserId(int productId, String userId);
}

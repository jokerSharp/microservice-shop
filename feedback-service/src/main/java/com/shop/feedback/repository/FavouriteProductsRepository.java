package com.shop.feedback.repository;

import com.shop.feedback.entity.FavouriteProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FavouriteProductsRepository {


    Mono<FavouriteProduct> save(FavouriteProduct favouriteProduct);

    Mono<Void> deletebyproductid(int productId);

    Mono<FavouriteProduct> findByProductId(int productId);

    Flux<FavouriteProduct> findAll();
}

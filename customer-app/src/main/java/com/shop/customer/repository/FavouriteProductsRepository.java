package com.shop.customer.repository;

import com.shop.customer.entity.FavouriteProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FavouriteProductsRepository {


    Mono<FavouriteProduct> save(FavouriteProduct favouriteProduct);

    Mono<Void> deletebyproductid(int productId);

    Mono<FavouriteProduct> findByProductId(int productId);

    Flux<FavouriteProduct> findAll();
}

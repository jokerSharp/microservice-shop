package com.shop.customer.client;

import com.shop.customer.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductsClient {

    Flux<Product> findAllProducts(String title, String details);

    Mono<Product> findProduct(int productId);
}

package com.shop.customer.client;

import com.shop.customer.entity.Product;
import reactor.core.publisher.Flux;

public interface ProductsClient {

    Flux<Product> findAllProducts(String title, String details);
}

package com.shop.customer.client;

import com.shop.customer.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class WebClientProductsClient implements ProductsClient {

    private final WebClient webClient;

    @Override
    public Flux<Product> findAllProducts(String title, String details) {
        return this.webClient.get()
                .uri("catalogue-api/products?title={title}&details={details}", title, details)
                .retrieve()
                .bodyToFlux(Product.class);
    }
}

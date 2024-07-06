package com.shop.customer.config;

import com.shop.customer.client.WebClientFavouriteProductsClient;
import com.shop.customer.client.WebClientProductReviewsClient;
import com.shop.customer.client.WebClientProductsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Bean
    public WebClientProductsClient webClientProductsClient(
            @Value("${webshop.services.catalogue.uri:http://localhost:8181}") String catalogueBaseUrl) {
        return new WebClientProductsClient(WebClient.builder()
                .baseUrl(catalogueBaseUrl)
                .build());
    }

    @Bean
    public WebClientFavouriteProductsClient webClientFavouriteProductsClient(
            @Value("${webshop.services.feedback.uri:http://localhost:8182}") String feedbackBaseUrl) {
        return new WebClientFavouriteProductsClient(WebClient.builder()
                .baseUrl(feedbackBaseUrl)
                .build());
    }

    @Bean
    public WebClientProductReviewsClient webClientProductsReviewsClient(
            @Value("${webshop.services.feedback.uri:http://localhost:8182}") String feedbackBaseUrl) {
        return new WebClientProductReviewsClient(WebClient.builder()
                .baseUrl(feedbackBaseUrl)
                .build());
    }
}

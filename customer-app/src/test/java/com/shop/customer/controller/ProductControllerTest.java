package com.shop.customer.controller;

import com.shop.customer.client.FavouriteProductsClient;
import com.shop.customer.client.ProductReviewsClient;
import com.shop.customer.client.ProductsClient;
import com.shop.customer.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductsClient productsClient;

    @Mock
    FavouriteProductsClient favouriteProductsClient;

    @Mock
    ProductReviewsClient productReviewsClient;

    @InjectMocks
    ProductController controller;

    @Test
    void loadProduct_ProductExists_returnsNotEmptyMono() {
        //given
        var product = new Product(1, "First product", "First description");
        doReturn(Mono.just(product)).when(this.productsClient).findProduct(1);

        //when
        StepVerifier.create(this.controller.loadProduct(1))
        //then
                .expectNext(new Product(1, "First product", "First description"))
                .expectComplete()
                .verify();

        verify(this.productsClient).findProduct(1);
        verifyNoMoreInteractions(this.productsClient);
        verifyNoInteractions(this.favouriteProductsClient, this.productReviewsClient);
    }

    @Test
    void loadProduct_ProductDoesNotExists_returnsMonoWithNoSuchElementException() {
        //given
        doReturn(Mono.empty()).when(this.productsClient).findProduct(1);

        //when
        StepVerifier.create(this.controller.loadProduct(1))
        //then
                .expectErrorMatches(exception -> exception instanceof NoSuchElementException e &&
                        e.getMessage().equals("customer.products.error.not_found"))
                .verify();

        verify(this.productsClient).findProduct(1);
        verifyNoMoreInteractions(this.productsClient);
        verifyNoInteractions(this.favouriteProductsClient, this.productReviewsClient);
    }

    @Test
    @DisplayName("NoSuchElementException should be given to the page errors/404")
    void handleNoSuchElementException_ReturnsErrors404() {
        //given
        var exception = new NoSuchElementException("Product is not found");
        var model = new ConcurrentModel();

        //when
        var result = this.controller.handleNoSuchElementException(exception, model);

        //then
        assertEquals("errors/404", result);
        assertEquals("Product is not found", model.getAttribute("error"));
    }

}
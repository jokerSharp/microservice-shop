package com.shop.manager.controller;

import com.shop.manager.client.BadRequestException;
import com.shop.manager.client.ProductsRestClient;
import com.shop.manager.controller.payload.NewProductPayload;
import com.shop.manager.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductsController unit tests")
class ProductsControllerTest {

    @Mock
    ProductsRestClient productsRestClient;

    @InjectMocks
    ProductsController productsController;

    @Test
    void createProduct_RequestIsValid_ReturnsRedirectionToProductPage() {
        //given
        var payload = new NewProductPayload("New product", "Default description");
        var model = new ConcurrentModel();

        doReturn(new Product(1, "New product", "Default description"))
                .when(this.productsRestClient)
                .createProduct("New product", "Default description");

        //when
        var result = this.productsController.createProduct(payload, model);

        //then
        assertEquals("redirect:/catalogue/products/1", result);

        verify(this.productsRestClient).createProduct("New product", "Default description");
        verifyNoMoreInteractions(this.productsRestClient);
    }

    @Test
    void createProduct_RequestIsInvalid_ReturnsErrorPage() {
        //given
        var payload = new NewProductPayload("Ne", null);
        var model = new ConcurrentModel();

        doThrow(new BadRequestException(List.of("First error", "Second Error")))
                .when(this.productsRestClient)
                .createProduct("Ne", null);

        //when
        var result = this.productsController.createProduct(payload, model);

        //then
        assertEquals("catalogue/products/new_product", result);
        assertEquals(payload, model.getAttribute("payload"));
        assertEquals(List.of("First error", "Second Error"), model.getAttribute("errors"));

        verify(this.productsRestClient).createProduct("Ne", null);
        verifyNoMoreInteractions(this.productsRestClient);
    }

}
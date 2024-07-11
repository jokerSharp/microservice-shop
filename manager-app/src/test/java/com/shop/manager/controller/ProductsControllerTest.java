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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.ConcurrentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductsController unit tests")
@AutoConfigureMockMvc
class ProductsControllerTest {

    @Mock
    ProductsRestClient productsRestClient;

    @InjectMocks
    ProductsController productsController;

    MockMvc mockMvc;

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

    @Test
    void getProductsList_ReturnsProductsListPage() {
        // given
        var model = new ConcurrentModel();
        var title = "product";

        var products = IntStream.range(1, 4)
                .mapToObj(i -> new Product(i, "Product №%d".formatted(i),
                        "Product description №%d".formatted(i)))
                .toList();

        doReturn(products).when(this.productsRestClient).findAllProducts(title, null);

        // when
        var result = this.productsController.getProductsList(model, title, null);

        // then
        assertEquals("catalogue/products/list", result);
        assertEquals(title, model.getAttribute("title"));
        assertEquals(products, model.getAttribute("products"));
    }

}
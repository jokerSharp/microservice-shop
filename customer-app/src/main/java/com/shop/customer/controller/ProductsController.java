package com.shop.customer.controller;

import com.shop.customer.client.ProductsClient;
import com.shop.customer.entity.FavouriteProduct;
import com.shop.customer.service.FavouriteProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@RequestMapping("customer/products")
public class ProductsController {

    private final ProductsClient productsClient;

    private final FavouriteProductsService favouriteProductsService;

    @GetMapping("list")
    public Mono<String> getProductsListPage(Model model,
                                            @RequestParam(name = "title", required = false) String title,
                                            @RequestParam(name = "details", required = false) String details) {
        return this.productsClient.findAllProducts(title, details)
                .collectList()
                .doOnNext(products -> model.addAttribute("products", products))
                .thenReturn("customer/products/list");

    }

    @GetMapping("favourites")
    public Mono<String> getFavouriteProductsPage(Model model,
                                                 @RequestParam(name = "title", required = false) String title,
                                                 @RequestParam(name = "details", required = false) String details) {
        model.addAttribute("title", title);
        model.addAttribute("details", details);
        return this.favouriteProductsService.findFavouriteProducts()
                .map(FavouriteProduct::getProductId)
                .collectList()
                .flatMap(favouriteProducts -> this.productsClient.findAllProducts(title, details)
                        .filter(product -> favouriteProducts.contains(product.id()))
                        .collectList()
                        .doOnNext(products -> model.addAttribute("products", products)))
                .thenReturn("customer/products/favourites");
    }
}

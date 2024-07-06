package com.shop.feedback.controller.payload;

import jakarta.validation.constraints.NotNull;

public record NewFavouriteProductPayload(
        @NotNull(message = "{feedback.product.favourites.create.errors.product_id_is_null}")
        Integer productId) {
}

package com.shop.feedback.controller.payload;

public record NewProductReviewPayload(Integer productId,
                                      Integer rating,
                                      String review) {
}

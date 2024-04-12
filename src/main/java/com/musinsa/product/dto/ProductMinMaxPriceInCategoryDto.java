package com.musinsa.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;


@Getter
@AllArgsConstructor
@Builder
@ToString
public class ProductMinMaxPriceInCategoryDto {
    private String categoryName;
    private Product minProduct;
    private Product maxProduct;



    @Getter
    @AllArgsConstructor
    @Builder
    public static class Product {
        private String brandName;
        private long price;
    }
}

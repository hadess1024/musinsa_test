package com.musinsa.product.dto;

import lombok.*;

import java.util.Collection;


@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class ProductMinPriceInBrandDto {
    private ProductPriceInBrand minPrice;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductPriceInBrand {
        private Collection<ProductInfoDto> category;
        private String brandName;
        private long totalPrice;
    }
}

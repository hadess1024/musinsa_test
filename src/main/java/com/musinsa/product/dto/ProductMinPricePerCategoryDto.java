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
public class ProductMinPricePerCategoryDto {
    private Collection<ProductInfoDto> productPerCategory;
    private long totalPrice;
}

package com.musinsa.product.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductPriceRangeDto {
    private Long brandId;
    private Long categoryId;
    private Long minPrice;
    private Long maxPrice;
}

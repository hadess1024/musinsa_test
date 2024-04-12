package com.musinsa.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductInfoDto {
    private Long id;
    private Long brandId;
    private String brandName;
    private Long categoryId;
    private String categoryName;
    private String name;
    private Long price;
}

package com.musinsa.product.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
@ToString
public class ProductCreateDto {
    @NotNull(message = "브랜드 ID가 없습니다")
    private Long brandId;
    @NotNull(message = "카테고리 ID가 없습니다")
    private Long categoryId;
    @NotEmpty(message = "상품 이름이 없습니다")
    private String name;
    @NotNull(message = "가격 정보가 없습니다")
    private Long price;
}

package com.musinsa.brand.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BrandUpdateDto {
    @NotNull(message = "브랜드 ID가 없습니다")
    private Long id;
    @NotEmpty(message = "이름이 없습니다")
    private String name;
}

package com.musinsa.brand.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BrandCreateDto {
    @NotEmpty(message = "이름이 없습니다")
    private String name;
}

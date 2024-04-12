package com.musinsa.category.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDto {
    @NotEmpty(message = "이름이 없습니다")
    private String name;
}

package com.musinsa.category.service;


import com.musinsa.category.dto.CategoryCreateDto;

public interface CategoryCommand {
    void register(CategoryCreateDto categoryCreate);
}

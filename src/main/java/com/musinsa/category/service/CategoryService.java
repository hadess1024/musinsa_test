package com.musinsa.category.service;


import com.musinsa.category.domain.entity.Category;
import com.musinsa.category.domain.repository.CategoryRepository;
import com.musinsa.category.dto.CategoryCreateDto;
import com.musinsa.category.exception.DuplicatedCategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryCommand {

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public void register(CategoryCreateDto categoryCreate) {
        if(categoryRepository.findByName(categoryCreate.getName()).isPresent()){
            throw new DuplicatedCategoryException(categoryCreate.getName());
        }
        categoryRepository.save(Category.create(categoryCreate.getName()));
    }
}

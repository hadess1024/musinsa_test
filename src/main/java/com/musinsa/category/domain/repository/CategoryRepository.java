package com.musinsa.category.domain.repository;


import com.musinsa.category.domain.entity.Category;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(Long id);
    Optional<Category> findByName(String name);
    List<Category> findAllByIds(Collection<Long> categoryIds);
    void save(Category category);
}

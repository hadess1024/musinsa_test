package com.musinsa.category.infra;

import com.musinsa.category.domain.entity.Category;
import com.musinsa.category.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryJpaRepository.findById(id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryJpaRepository.findByCategoryName(name);
    }

    @Override
    public List<Category> findAllByIds(Collection<Long> categoryIds) {
        return categoryJpaRepository.findAllById(categoryIds);
    }

    @Override
    public void save(Category category) {
        categoryJpaRepository.save(category);
    }
}

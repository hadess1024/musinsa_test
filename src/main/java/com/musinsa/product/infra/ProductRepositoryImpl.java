package com.musinsa.product.infra;

import com.musinsa.product.domain.entity.Product;
import com.musinsa.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public void save(Product product) {
        productJpaRepository.save(product);
    }

    @Override
    public Optional<Product> findById(long id) {
        return productJpaRepository.findById(id);
    }

    @Override
    public Optional<Product> findByBrandAndCategoryAndName(long brandId, long categoryId,String name) {
        return productJpaRepository.findByBrandIdAndCategoryIdAndName(brandId, categoryId, name);
    }

    @Override
    public void delete(Product product) {
        productJpaRepository.delete(product);
    }
}

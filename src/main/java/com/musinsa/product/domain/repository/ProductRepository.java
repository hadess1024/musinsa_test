package com.musinsa.product.domain.repository;


import com.musinsa.product.domain.entity.Product;

import java.util.Optional;

public interface ProductRepository  {

    void save(Product product);
    Optional<Product> findById(long id);
    Optional<Product> findByBrandAndCategoryAndName(long brandId,long categoryId, String name);
    void delete(Product product);
}

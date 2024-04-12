package com.musinsa.brand.domain.repository;


import com.musinsa.brand.domain.entity.Brand;

import java.util.Optional;

public interface BrandRepository {
    Optional<Brand> findById(long id);
    Optional<Brand> findByName(String name);
    void save(Brand brand);
    void delete(Brand brand);
}

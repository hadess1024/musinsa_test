package com.musinsa.brand.infra;

import com.musinsa.brand.domain.entity.Brand;
import com.musinsa.brand.domain.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class BrandRepositoryImpl implements BrandRepository {

    private final BrandJpaRepository brandJpaRepository;

    @Override
    public Optional<Brand> findById(long id) {
        return brandJpaRepository.findById(id);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandJpaRepository.findByBrandName(name);
    }

    @Override
    public void save(Brand brand) {
        brandJpaRepository.save(brand);
    }

    @Override
    public void delete(Brand brand) {
        brandJpaRepository.delete(brand);
    }
}

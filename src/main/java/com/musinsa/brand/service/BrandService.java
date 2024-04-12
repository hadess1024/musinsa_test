package com.musinsa.brand.service;


import com.musinsa.brand.domain.entity.Brand;
import com.musinsa.brand.domain.repository.BrandRepository;
import com.musinsa.brand.dto.BrandCreateDto;
import com.musinsa.brand.dto.BrandUpdateDto;
import com.musinsa.brand.exception.DuplicatedBrandException;
import com.musinsa.common.exception.DomainEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Slf4j
@Service
@RequiredArgsConstructor
public class BrandService implements BrandCommand{

    private final BrandRepository brandRepository;

    @Transactional
    @Override
    public void register(BrandCreateDto brandCreate) {
        if(brandRepository.findByName(brandCreate.getName()).isPresent()){
            throw new DuplicatedBrandException(brandCreate.getName());
        }
        brandRepository.save(Brand.create(brandCreate.getName()));
    }

    @Transactional
    @Override
    public void delete(long brandId) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new DomainEntityNotFoundException(String.valueOf(brandId),"요청하신 브랜드가 없습니다"));
        brandRepository.delete(brand);
    }

    @Transactional
    @Override
    public void update(BrandUpdateDto brandUpdate) {
        if(brandRepository.findByName(brandUpdate.getName()).isPresent()){
            throw new DuplicatedBrandException(brandUpdate.getName());
        }
        Brand brand = brandRepository.findById(brandUpdate.getId()).orElseThrow(() -> new DomainEntityNotFoundException(String.valueOf(brandUpdate.getId()),"요청하신 브랜드가 없습니다"));
        brand.updateName(brandUpdate.getName());
    }
}

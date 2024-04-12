package com.musinsa.brand.service;


import com.musinsa.brand.dto.BrandCreateDto;
import com.musinsa.brand.dto.BrandUpdateDto;

public interface BrandCommand {
    void register(BrandCreateDto brandCreate);
    void delete(long brandId);
    void update(BrandUpdateDto brandUpdate);
}

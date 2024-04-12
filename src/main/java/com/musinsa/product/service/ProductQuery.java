package com.musinsa.product.service;


import com.musinsa.product.dto.ProductMinMaxPriceInCategoryDto;
import com.musinsa.product.dto.ProductMinPriceInBrandDto;
import com.musinsa.product.dto.ProductMinPricePerCategoryDto;

public interface ProductQuery {

    ProductMinPricePerCategoryDto getProductMinimumPriceInEachCategory();

    ProductMinPriceInBrandDto getProductMinPriceAllCategoryOfBrand();
    ProductMinMaxPriceInCategoryDto getProductMinAndMaxPriceInCategory(String categoryName);
}

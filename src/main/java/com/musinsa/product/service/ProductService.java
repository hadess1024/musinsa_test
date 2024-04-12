package com.musinsa.product.service;


import com.musinsa.brand.domain.repository.BrandRepository;
import com.musinsa.category.domain.entity.Category;
import com.musinsa.category.domain.repository.CategoryRepository;
import com.musinsa.common.exception.DomainEntityNotFoundException;
import com.musinsa.product.domain.entity.Product;
import com.musinsa.product.domain.repository.ProductQueryRepository;
import com.musinsa.product.domain.repository.ProductRepository;
import com.musinsa.product.dto.*;
import com.musinsa.product.exception.DuplicatedProductException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements ProductCommand , ProductQuery {

    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public void register(ProductCreateDto productCreate) {
        brandRepository.findById(productCreate.getBrandId()).orElseThrow(()-> new DomainEntityNotFoundException(String.valueOf(productCreate.getBrandId()),"브랜드가 없습니다"));
        categoryRepository.findById(productCreate.getCategoryId()).orElseThrow(()-> new DomainEntityNotFoundException(String.valueOf(productCreate.getCategoryId()),"카테고리가 없습니다"));
        if(productRepository.findByBrandAndCategoryAndName(productCreate.getBrandId(),productCreate.getCategoryId(),productCreate.getName()).isPresent()){
            throw new DuplicatedProductException(productCreate.getName());
        }
        productRepository.save(Product.create(productCreate));
    }

    @Transactional
    @Override
    public void delete(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new DomainEntityNotFoundException(String.valueOf(productId),"상품이 없습니다"));
        productRepository.delete(product);
    }

    @Transactional
    @Override
    public void update(ProductUpdateDto productUpdate) {
        Product product = productRepository.findById(productUpdate.getProductId()).orElseThrow(() -> new DomainEntityNotFoundException(String.valueOf(productUpdate.getProductId()),"상품이 없습니다"));
        if(productRepository.findByBrandAndCategoryAndName(productUpdate.getBrandId(),productUpdate.getCategoryId(),productUpdate.getName()).isPresent()){
            throw new DuplicatedProductException(productUpdate.getName());
        }
        product.update(productUpdate.getCategoryId(),productUpdate.getName(),productUpdate.getPrice());
    }

    @Transactional
    @Override
    public ProductMinPricePerCategoryDto getProductMinimumPriceInEachCategory() {
        List<Product> list = productQueryRepository.findAllProductMinimumPriceInEachCategory();
        return ProductMinPricePerCategoryDto.builder()
                .productPerCategory(list.stream().map( product -> ProductInfoDto.builder()
                        .categoryName(categoryRepository.findById(product.getCategoryId()).get().getCategoryName())
                        .brandName(brandRepository.findById(product.getBrandId()).get().getBrandName())
                        .price(product.getPrice())
                        .build()).collect(Collectors.toList()))
                .totalPrice(list.stream().mapToLong(Product::getPrice).sum())
                .build();
    }


    @Transactional
    @Override
    public ProductMinPriceInBrandDto getProductMinPriceAllCategoryOfBrand() {
        Pair<Long, List<ProductPriceRangeDto>> pair = productQueryRepository.findProductMinPriceAllCategoryOfBrand();
        List<ProductPriceRangeDto> productPriceRangeList = pair.getSecond();
        Map<Long,Category> categoryMap = categoryRepository.findAllByIds(productPriceRangeList.stream().map(ProductPriceRangeDto::getCategoryId).collect(Collectors.toList()))
                .stream().collect(Collectors.toMap(Category::getId,category -> category));
        return ProductMinPriceInBrandDto.builder()
                .minPrice(ProductMinPriceInBrandDto.ProductPriceInBrand.builder()
                        .brandName(brandRepository.findById(productPriceRangeList.get(0).getBrandId()).get().getBrandName())
                        .category(productPriceRangeList.stream().map( productPriceRangeDto -> {
                            return ProductInfoDto.builder()
                                    .categoryName(categoryMap.get(productPriceRangeDto.getCategoryId()).getCategoryName())
                                    .price(productPriceRangeDto.getMinPrice())
                                    .build();
                        }).collect(Collectors.toList()))
                        .totalPrice(pair.getFirst())
                        .build())
                .build();
    }

    @Transactional
    @Override
    public ProductMinMaxPriceInCategoryDto getProductMinAndMaxPriceInCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow(()-> new DomainEntityNotFoundException(categoryName,"카테고리가 없습니다"));
        List<Product> list = productQueryRepository.findProductMinAndMaxPriceInCategory(category.getId());
        Product maxProduct = list.get(0).getPrice().longValue() > list.get(1).getPrice().longValue() ? list.get(0) : list.get(1);
        Product minProduct = list.get(0).getPrice().longValue() > list.get(1).getPrice().longValue() ? list.get(1) : list.get(0);
        return ProductMinMaxPriceInCategoryDto.builder()
                .categoryName(category.getCategoryName())
                .maxProduct(ProductMinMaxPriceInCategoryDto.Product.builder()
                        .brandName(brandRepository.findById(maxProduct.getBrandId()).get().getBrandName())
                        .price(maxProduct.getPrice())
                        .build())
                .minProduct(ProductMinMaxPriceInCategoryDto.Product.builder()
                        .brandName(brandRepository.findById(minProduct.getBrandId()).get().getBrandName())
                        .price(minProduct.getPrice())
                        .build())
                .build();
    }
}

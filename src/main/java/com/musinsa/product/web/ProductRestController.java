package com.musinsa.product.web;

import com.musinsa.product.dto.*;
import com.musinsa.product.service.ProductCommand;
import com.musinsa.product.service.ProductQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductRestController {

    private final ProductCommand productCommand;
    private final ProductQuery productQuery;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/api/v1/products" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct( @RequestBody @Valid ProductCreateDto productCreate){
        productCommand.register(productCreate);
    }

    @PutMapping(value="/api/v1/products" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateProduct( @RequestBody @Valid ProductUpdateDto productUpdate){
        productCommand.update(productUpdate);
    }

    @DeleteMapping(value="/api/v1/products/{productId}" , consumes = MediaType.ALL_VALUE)
    public void deleteProduct( @PathVariable("productId") Long productId){
        productCommand.delete(productId);
    }

    @GetMapping(value="/api/v1/products/each-category/brand/price-min" , consumes = MediaType.ALL_VALUE)
    public ProductMinPricePerCategoryDto getProductMinimumPriceInEachCategory(){
        return productQuery.getProductMinimumPriceInEachCategory();
    }

    @GetMapping(value="/api/v1/products/brand-all-category/price-min" , consumes = MediaType.ALL_VALUE)
    public ProductMinPriceInBrandDto getProductMinPriceAllCategoryOfBrand(){
        return productQuery.getProductMinPriceAllCategoryOfBrand();
    }

    @GetMapping(value="/api/v1/products/price-min-max" , consumes = MediaType.ALL_VALUE)
    public ProductMinMaxPriceInCategoryDto getProductMinAndMaxPriceInCategory(@RequestParam("categoryName") String categoryName){
        return productQuery.getProductMinAndMaxPriceInCategory(categoryName);
    }

}

package com.musinsa.product.service;

import com.musinsa.product.dto.ProductCreateDto;
import com.musinsa.product.dto.ProductUpdateDto;

public interface ProductCommand {
    void register(ProductCreateDto productCreate);
    void delete(long productId);
    void update(ProductUpdateDto productUpdate);
}

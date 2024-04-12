package com.musinsa.product.domain.repository;


import com.musinsa.product.domain.entity.Product;
import com.musinsa.product.dto.ProductPriceRangeDto;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ProductQueryRepository {
    List<Product> findAllProductMinimumPriceInEachCategory();
    Pair<Long, List<ProductPriceRangeDto>> findProductMinPriceAllCategoryOfBrand();
    List<Product> findProductMinAndMaxPriceInCategory(long categoryId);
}

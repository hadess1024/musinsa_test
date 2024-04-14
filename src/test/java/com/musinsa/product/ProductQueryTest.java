package com.musinsa.product;


import com.musinsa.category.domain.entity.Category;
import com.musinsa.category.domain.repository.CategoryRepository;
import com.musinsa.product.domain.entity.Product;
import com.musinsa.product.domain.repository.ProductQueryRepository;
import com.musinsa.product.dto.ProductPriceRangeDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Slf4j
@SpringBootTest(properties = {"spring.profiles.active=default"})
public class ProductQueryTest {

    @Autowired
    private ProductQueryRepository productQueryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void findAllProductMinimumPriceInEachCategory_test() {
        log.info("findAllProductMinimumPriceInEachCategory_test start");
        List<Product> products = productQueryRepository.findAllProductMinimumPriceInEachCategory();
        products.forEach( product -> log.info("Product {}",product.toString()));
    }

    @Test
    void findProductMinAndMaxPriceInCategory_test() {
        log.info("findProductMinAndMaxPriceInCategory_test start");
        Category category = categoryRepository.findByName("아우터").orElseThrow(EntityNotFoundException::new);
        List<Product> products = productQueryRepository.findProductMinAndMaxPriceInCategory(category.getId());
        products.forEach( product -> log.info("Product {}",product.toString()));
    }


    @Test
    void findProductMinPriceAllCategoryOfBrand_test() {
        log.info("findProductMinPriceAllCategoryOfBrand_test start");
        Pair<Long, List<ProductPriceRangeDto>> pair = productQueryRepository.findProductMinPriceAllCategoryOfBrand();
        pair.getSecond().forEach( product -> log.info("Product {}",product.toString()));
    }
}

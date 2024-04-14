package com.musinsa.product;


import com.musinsa.product.dto.ProductMinMaxPriceInCategoryDto;
import com.musinsa.product.dto.ProductMinPriceInBrandDto;
import com.musinsa.product.dto.ProductMinPricePerCategoryDto;
import com.musinsa.product.service.ProductQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest(properties = {"spring.profiles.active=default"})
public class ProductQueryServiceTest {

    @Autowired
    private ProductQuery productQuery;


    @Test
    void findAllProductMinimumPriceInEachCategory_test() {
        log.info("findAllProductMinimumPriceInEachCategory_test start");
        ProductMinPricePerCategoryDto productMinPricePerCategory = productQuery.getProductMinimumPriceInEachCategory();
        assertThat(productMinPricePerCategory)
                .extracting(ProductMinPricePerCategoryDto::getTotalPrice).isEqualTo(43100L);
    }

    @Test
    void findProductMinAndMaxPriceInCategory_test() {
        log.info("findProductMinAndMaxPriceInCategory_test start");
        ProductMinMaxPriceInCategoryDto productMinMaxPriceInCategory = productQuery.getProductMinAndMaxPriceInCategory("스니커즈");
        assertThat(productMinMaxPriceInCategory)
                .extracting(p -> p.getMaxProduct().getBrandName(),p -> p.getMaxProduct().getPrice(),
                        p -> p.getMinProduct().getBrandName(),p -> p.getMinProduct().getPrice()
                ) .containsExactly("E", 9900L,"A", 9000L);
    }


    @Test
    void findProductMinPriceAllCategoryOfBrand_test() {
        log.info("findProductMinPriceAllCategoryOfBrand_test start");
        ProductMinPriceInBrandDto productMinPriceInBrand = productQuery.getProductMinPriceAllCategoryOfBrand();
        assertThat(productMinPriceInBrand)
                .extracting(productMinPriceInBrandDto -> productMinPriceInBrandDto.getMinPrice().getBrandName(), productMinPriceInBrandDto -> productMinPriceInBrandDto.getMinPrice().getTotalPrice())
                .containsExactly("A",37700L);
    }
}

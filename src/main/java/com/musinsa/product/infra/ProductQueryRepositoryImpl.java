package com.musinsa.product.infra;

import com.musinsa.product.domain.entity.Product;
import com.musinsa.product.domain.repository.ProductQueryRepository;
import com.musinsa.product.dto.ProductPriceRangeDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

import static com.musinsa.product.domain.entity.QProduct.*;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public List<Product> findAllProductMinimumPriceInEachCategory() {
        Query query = entityManager.createNativeQuery("SELECT p.* FROM Product p INNER JOIN  " +
                " ( SELECT CategoryId, MIN(Price) AS min_price FROM Product GROUP BY CategoryId ) ct " +
                " ON ct.CategoryId=p.CategoryId AND p.Price=ct.min_price ORDER BY CategoryId ASC ",Product.class);
        return query.getResultList();
    }


    @Override
    public Pair<Long,List<ProductPriceRangeDto>> findProductMinPriceAllCategoryOfBrand() {
        List<ProductPriceRangeDto> productPriceRangeList = jpaQueryFactory.from(product).select(Projections.fields(ProductPriceRangeDto.class,
                        product.brandId,product.categoryId,product.price.min().as("minPrice")))
                .groupBy(product.brandId,product.categoryId).fetch();
        TreeMap<Long,Long> maxMap = new TreeMap<>();
        Map<Long,List<ProductPriceRangeDto>> productMap = new HashMap<>();
        for(ProductPriceRangeDto productPriceRange : productPriceRangeList){
            long brandId = productPriceRange.getBrandId();
            long totalPrice = maxMap.getOrDefault(brandId,0L);
            totalPrice+=productPriceRange.getMinPrice();
            maxMap.put(brandId,totalPrice);
            List<ProductPriceRangeDto> list =productMap.getOrDefault(brandId,new LinkedList<>());
            list.add(productPriceRange);
            productMap.put(brandId,list);
        }
        return Pair.of(maxMap.get(maxMap.firstKey()),productMap.get(maxMap.firstKey()));
    }

    @Override
    public List<Product> findProductMinAndMaxPriceInCategory(long categoryId) {
        ProductPriceRangeDto productPriceRange = jpaQueryFactory.from(product).select(Projections.fields(ProductPriceRangeDto.class,
                product.categoryId,product.price.min().as("minPrice"),product.price.max().as("maxPrice")
        )).groupBy(product.categoryId).having(product.categoryId.eq(categoryId)).fetchOne();
        return jpaQueryFactory.selectFrom(product).where(product.categoryId.eq(categoryId)
                .and(product.price.eq(productPriceRange.getMaxPrice()).or(product.price.eq(productPriceRange.getMinPrice())))).fetch();
    }
}

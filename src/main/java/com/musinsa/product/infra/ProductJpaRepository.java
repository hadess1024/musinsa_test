package com.musinsa.product.infra;

import com.musinsa.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductJpaRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByBrandIdAndCategoryIdAndName(long brandId,long categoryId, String name);

}

package com.musinsa.product.domain.entity;

import com.musinsa.product.dto.ProductCreateDto;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Table(name = "Product")
@Entity
@ToString
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id", columnDefinition = "BIGINT UNSIGNED",updatable = false)
    private Long id;

    @Column(name = "CategoryId",nullable = false)
    private Long categoryId;

    @Column(name = "BrandId",nullable = false)
    private Long brandId;

    @Column(name = "Name",nullable = false)
    private String name;

    @Column(name = "Price",nullable = false)
    private Long price;

    @CreationTimestamp
    @Column(name="RegisteredAt",nullable = false,updatable = false)
    private LocalDateTime registeredAt;

    public static Product create(ProductCreateDto productCreate){
        return Product.builder()
                .brandId(productCreate.getBrandId())
                .categoryId(productCreate.getCategoryId())
                .name(productCreate.getName())
                .price(productCreate.getPrice())
                .build();
    }

    public void update(long categoryId, String name, long price){
        this.categoryId=categoryId;
        this.name=name;
        this.price=price;
    }
}

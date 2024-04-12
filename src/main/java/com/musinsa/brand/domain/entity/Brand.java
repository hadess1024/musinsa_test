package com.musinsa.brand.domain.entity;

import lombok.*;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Getter
@Table(name = "Brand")
@Entity
@ToString
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id", columnDefinition = "BIGINT UNSIGNED",updatable = false)
    private Long id;

    @Column(name = "BrandName",nullable = false,length = 64,unique = true)
    private String brandName;

    @CreationTimestamp
    @Column(name="RegisteredAt",nullable = false,updatable = false)
    private LocalDateTime registeredAt;

    public static Brand create(String name){
        return Brand.builder()
                .brandName(name)
                .build();
    }

    public void updateName(String name){
        this.brandName=name;
    }
}

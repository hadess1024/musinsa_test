package com.musinsa.category.domain.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Getter
@Table(name = "Category")
@Entity
@ToString
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id", columnDefinition = "BIGINT UNSIGNED",updatable = false)
    private Long id;

    @Column(name = "CategoryName",nullable = false,length = 64,unique = true)
    private String categoryName;

    @CreationTimestamp
    @Column(name="RegisteredAt",nullable = false,updatable = false)
    private LocalDateTime registeredAt;

    public static Category create(String name){
        return Category.builder()
                .categoryName(name)
                .build();
    }
}

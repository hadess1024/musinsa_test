package com.musinsa.category.web;

import com.musinsa.category.dto.CategoryCreateDto;
import com.musinsa.category.service.CategoryCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RequiredArgsConstructor
@RestController
public class CategoryRestController {

    private final CategoryCommand categoryCommand;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/api/v1/categories" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct( @RequestBody @Valid CategoryCreateDto categoryCreate){
        categoryCommand.register(categoryCreate);
    }
}

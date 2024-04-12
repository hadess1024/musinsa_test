package com.musinsa.brand.web;

import com.musinsa.brand.dto.BrandCreateDto;
import com.musinsa.brand.dto.BrandUpdateDto;
import com.musinsa.brand.service.BrandCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RequiredArgsConstructor
@RestController
public class BrandRestController {

    private final BrandCommand brandCommand;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/api/v1/brands" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerBrand( @RequestBody @Valid BrandCreateDto brandCreate ){
        brandCommand.register(brandCreate);
    }

    @PutMapping(value="/api/v1/brands" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateBrand( @RequestBody @Valid BrandUpdateDto brandUpdate ){
        brandCommand.update(brandUpdate);
    }

    @DeleteMapping(value="/api/v1/brands/{brandId}" , consumes = MediaType.ALL_VALUE)
    public void deleteBrand(@PathVariable("brandId") Long brandId){
        brandCommand.delete(brandId);
    }

}

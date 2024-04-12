package com.musinsa.category.exception.handler;


import com.musinsa.category.exception.DuplicatedCategoryException;
import com.musinsa.common.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice("com.musinsa.category")
public class CategoryExceptionHandler {

	@ExceptionHandler(DuplicatedCategoryException.class)
	public ResponseEntity<Response> handleDuplicatedCategoryException(DuplicatedCategoryException exception) {
		log.error("",exception);
		return ResponseEntity.badRequest()
				.body(Response.builder()
						.cause(exception.getMessage())
						.message("동일한 카테고리 이름이 존재합니다.")
						.build());
	}
}

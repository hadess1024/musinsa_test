package com.musinsa.product.exception.handler;


import com.musinsa.common.dto.Response;
import com.musinsa.product.exception.DuplicatedProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice("com.musinsa.product")
public class ProductExceptionHandler {

	@ExceptionHandler(DuplicatedProductException.class)
	public ResponseEntity<Response> handleDuplicatedProductException(DuplicatedProductException exception) {
		log.error("",exception);
		return ResponseEntity.badRequest()
				.body(Response.builder()
						.cause(exception.getMessage())
						.message("동일한 상품 이름이 존재합니다.")
						.build());
	}
}

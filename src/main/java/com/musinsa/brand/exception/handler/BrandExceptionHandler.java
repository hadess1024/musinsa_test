package com.musinsa.brand.exception.handler;


import com.musinsa.brand.exception.DuplicatedBrandException;
import com.musinsa.common.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice("com.musinsa.brand")
public class BrandExceptionHandler {

	@ExceptionHandler(DuplicatedBrandException.class)
	public ResponseEntity<Response> handleDuplicatedBrandException(DuplicatedBrandException exception) {
		log.error("",exception);
		return ResponseEntity.badRequest()
				.body(Response.builder()
						.cause(exception.getMessage())
						.message("동일한 브랜드 이름이 존재합니다.")
						.build());
	}
}

package com.musinsa.common.exception.handler;


import com.musinsa.common.dto.Response;
import com.musinsa.common.exception.DomainEntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;


@Slf4j
@ControllerAdvice("com.musinsa")
public class CommonExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Response> handleEntityNotFoundException(EntityNotFoundException exception) {
		log.error("",exception);
		return ResponseEntity.badRequest()
				.body(Response.builder()
						.message(exception.getMessage())
						.build());
	}

	@ExceptionHandler(DomainEntityNotFoundException.class)
	public ResponseEntity<Response> handleDomainEntityNotFoundException(DomainEntityNotFoundException exception) {
		log.error("",exception);
		return ResponseEntity.badRequest()
				.body(Response.builder()
						.cause(exception.getCauseValue())
						.message(exception.getMessage())
						.build());
	}

	@ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class})
	public ResponseEntity<Response> handleHttpMessageNotReadableException(RuntimeException exception) {
		log.error("",exception);
		return ResponseEntity.badRequest()
				.body(Response.builder()
						.message(exception.getMessage())
						.build());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleHttpMessageNotReadableException(MethodArgumentNotValidException exception) {
		log.error("",exception);
		String errorDetails = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(FieldError::getDefaultMessage)
				.collect(Collectors.joining(", "));
		return ResponseEntity.badRequest()
				.body(Response.builder()
						.message(errorDetails)
						.build());
	}


	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Response> handleDefaultFailException(Throwable exception) {
		log.error("",exception);
		return ResponseEntity.internalServerError()
				.body(Response.builder()
						.message(exception.getMessage())
						.build());
	}

}

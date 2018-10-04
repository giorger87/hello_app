package com.globant.cartService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
class CartNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(CartNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	void cartNotFoundHandler(CartNotFoundException ex) {
		log.info("Cart with id "+  ex.getCartId()  + " not found on ShoppingCartRepository" );
	}
}
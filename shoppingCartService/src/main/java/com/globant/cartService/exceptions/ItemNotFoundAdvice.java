package com.globant.cartService.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice

public class ItemNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(ItemNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	void itemNotFoundHandler(ItemNotFoundException ex) {
		log.info("Item with id "+  ex.getItemId()  + " not found on ItemRepository" );
	}
	
}
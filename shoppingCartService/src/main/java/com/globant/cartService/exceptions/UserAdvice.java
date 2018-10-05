package com.globant.cartService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class UserAdvice {
	@ResponseBody
	@ExceptionHandler(UserUnauthorizedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	void userUnathorized(UserUnauthorizedException ex) {
		log.info("Forbidden access for user " +  ex.getUserId()   );
	}
	
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	void userNotFound(UserNotFoundException ex) {
		log.info("User with id "+  ex.getUserId()  + " not found" );
	}
}

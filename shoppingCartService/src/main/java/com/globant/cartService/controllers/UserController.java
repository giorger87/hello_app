package com.globant.cartService.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globant.cartService.entities.ShoppingCart;
import com.globant.cartService.exceptions.CartNotFoundException;
import com.globant.cartService.exceptions.UserNotFoundException;
import com.globant.cartService.exceptions.UserUnauthorizedException;
import com.globant.cartService.services.UserService;
@RestController
public class UserController {
	private final UserService userService;
	
	UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("users/{userId}/carts")
	List <ShoppingCart> getCarts(@PathVariable Long userId) throws CartNotFoundException, UserUnauthorizedException, UserNotFoundException {
		return userService.findAllCarts(userId);
	}
	
	
	@GetMapping("users/{userId}/carts/{cartId}")
	ShoppingCart getCart(@PathVariable Long userId, @PathVariable Long cartId) throws CartNotFoundException, UserUnauthorizedException {
		return userService.findCart(cartId, userId);
	}
	
	@PostMapping("users/{userId}/carts")
	@ResponseStatus(HttpStatus.CREATED)
	ShoppingCart createCart(@PathVariable Long userId, @RequestBody ShoppingCart newCart) throws CartNotFoundException {
		return userService.addCart(userId,newCart);
	}
	
	@DeleteMapping("users/{userId}/carts/{cartId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCart(@PathVariable Long userId, @PathVariable Long cartId) throws CartNotFoundException, UserNotFoundException, UserUnauthorizedException {
		userService.deleteCart(userId, cartId);
	}
	
	@PutMapping("users/{userId}/carts/{cartId}")
	ShoppingCart replaceCart(@RequestBody ShoppingCart updatedCart, @PathVariable Long userId, @PathVariable Long cartId) throws CartNotFoundException, UserUnauthorizedException, UserNotFoundException {
	    userService.updateCart(userId, cartId, updatedCart);
		return userService.findCart(cartId, userId); //Build cart on demand with external data as title and price of items
	}
}

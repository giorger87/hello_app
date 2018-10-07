package com.globant.cartService.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globant.cartService.dto.ShoppingCartDTO;
import com.globant.cartService.entities.ShoppingCart;
import com.globant.cartService.exceptions.CartNotFoundException;
import com.globant.cartService.exceptions.UserNotFoundException;
import com.globant.cartService.exceptions.UserUnauthorizedException;
import com.globant.cartService.services.UserService;
@RestController
public class UserController {
	private final UserService userService;
	private ModelMapper modelMapper = new ModelMapper();
	
	UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("users/{userId}/carts")
	List <ShoppingCartDTO> getCarts(@PathVariable Long userId) throws CartNotFoundException, UserUnauthorizedException, UserNotFoundException {
		List<ShoppingCart> listCarts =  userService.findAllCarts(userId);
		List<ShoppingCartDTO> listCartsDTO = new ArrayList<>();
		listCarts.forEach((cart) ->{
			listCartsDTO.add(modelMapper.map(cart , ShoppingCartDTO.class));
		});
		return listCartsDTO;

	}
	
	
	@GetMapping("users/{userId}/carts/{cartId}")
	ShoppingCartDTO getCart(@PathVariable Long userId, @PathVariable Long cartId) throws CartNotFoundException, UserUnauthorizedException {
		ShoppingCart cart = userService.findCart(cartId, userId);
		return modelMapper.map(cart , ShoppingCartDTO.class);
	}
	
	@PostMapping("users/{userId}/carts")
	@ResponseStatus(HttpStatus.CREATED)
	ShoppingCartDTO createCart(@PathVariable Long userId, @RequestBody ShoppingCartDTO newCartDTO) throws CartNotFoundException {
		ShoppingCart newCart = modelMapper.map(newCartDTO , ShoppingCart.class);
		ShoppingCart createdCart = userService.addCart(userId,newCart);
		return modelMapper.map(createdCart , ShoppingCartDTO.class);
	}
	
	@DeleteMapping("users/{userId}/carts/{cartId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCart(@PathVariable Long userId, @PathVariable Long cartId) throws CartNotFoundException, UserNotFoundException, UserUnauthorizedException {
		userService.deleteCart(userId, cartId);
	}
	
	@PutMapping("users/{userId}/carts/{cartId}")
	ShoppingCartDTO replaceCart(@RequestBody ShoppingCartDTO cartDTO, @PathVariable Long userId, @PathVariable Long cartId) throws CartNotFoundException, UserUnauthorizedException, UserNotFoundException {
		ShoppingCart cart = modelMapper.map(cartDTO , ShoppingCart.class);
		userService.updateCart(userId, cartId, cart);
		ShoppingCart updatedCart = userService.findCart(cartId, userId); //Build cart on demand with external data as title and price of items
		return modelMapper.map(updatedCart, ShoppingCartDTO.class);
	}
}

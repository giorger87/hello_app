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
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	 /**
	  * Get all the shopping carts of one user
	  * @param userId --> id of the user
	  * @throws UserNotFoundException If user does not exist
	  * @throws UserUnauthorizedException If the cart with cart id does not belongs to the user
	  */
	
	@GetMapping("users/{userId}/carts")
	List <ShoppingCartDTO> getCarts(@PathVariable Long userId) throws UserUnauthorizedException, UserNotFoundException {
		List<ShoppingCart> listCarts =  userService.findAllCarts(userId);
		List<ShoppingCartDTO> listCartsDTO = new ArrayList<>();
		listCarts.forEach((cart) ->{
			listCartsDTO.add(modelMapper.map(cart , ShoppingCartDTO.class));
		});
		return listCartsDTO;

	}
	
	 /**
	  * Get one shopping cart
	  * @param userId --> id of the user
	  * @param cartId --> id of the cart
	  * @throws UserNotFoundException If user does not exist
	  * @throws UserUnauthorizedException If the cart with cart id does not belongs to the user
	  * @throws CartNotFoundException If the cart does not exist
	  */
	
	@GetMapping("users/{userId}/carts/{cartId}")
	ShoppingCartDTO getCart(@PathVariable Long userId, @PathVariable Long cartId) throws CartNotFoundException, UserUnauthorizedException {
		ShoppingCart cart = userService.findCart(cartId, userId);
		return modelMapper.map(cart , ShoppingCartDTO.class);
	}
	
	
	 /**
	  * Create one shopping cart for one user
	  * @param userId --> id of the user
	  * @param newCartDTO --> shopping cart that will be created 
	  * @throws CartNotFoundException If the cart does not exist
	  */
	@PostMapping("users/{userId}/carts")
	@ResponseStatus(HttpStatus.CREATED)
	public
	ShoppingCartDTO createCart(@PathVariable Long userId, @RequestBody ShoppingCartDTO newCartDTO) throws CartNotFoundException {
		ShoppingCart newCart = modelMapper.map(newCartDTO , ShoppingCart.class);
		ShoppingCart createdCart = userService.createCart(userId,newCart);
		return modelMapper.map(createdCart , ShoppingCartDTO.class);
	}
	
	
	 /**
	  * Delete one shopping cart 
	  * @param userId --> id of the user
	  * @param cartId --> id of the cart
	  * @throws UserNotFoundException If user does not exist
	  * @throws UserUnauthorizedException If the cart with cart id does not belongs to the user
	  * @throws CartNotFoundException If the cart does not exist
	  */
	@DeleteMapping("users/{userId}/carts/{cartId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCart(@PathVariable Long userId, @PathVariable Long cartId) throws CartNotFoundException, UserNotFoundException, UserUnauthorizedException {
		userService.deleteCart(userId, cartId);
	}
	
	
	 /**
	  * Update one shopping cart
	  * @param userId --> id of the user
	  * @param cartId --> id of the cart
	  * @throws UserNotFoundException If user does not exist
	  * @throws UserUnauthorizedException If the cart with cart id does not belongs to the user
	  * @throws CartNotFoundException If the cart does not exist
	  */
	@PutMapping("users/{userId}/carts/{cartId}")
	ShoppingCartDTO replaceCart(@RequestBody ShoppingCartDTO cartDTO, @PathVariable Long userId, @PathVariable Long cartId) throws CartNotFoundException, UserUnauthorizedException, UserNotFoundException {
		ShoppingCart cart = modelMapper.map(cartDTO , ShoppingCart.class);
		userService.updateCart(userId, cartId, cart);
		ShoppingCart updatedCart = userService.findCart(cartId, userId); //Build cart on demand with external data as title and price of items
		return modelMapper.map(updatedCart, ShoppingCartDTO.class);
	}
	
	 /**
	  * Update shopping cart state to checkout
	  * @param userId --> id of the user
	  * @param cartId --> id of the cart
	  * @throws UserNotFoundException If user does not exist
	  * @throws UserUnauthorizedException If the cart with does not belongs to the user
	  * @throws CartNotFoundException If the cart does not exist
	  */
	@PutMapping("users/{userId}/carts/{cartId}/checkout")
	ShoppingCartDTO checkout(@PathVariable Long userId, @PathVariable Long cartId) throws CartNotFoundException, UserUnauthorizedException, UserNotFoundException {
		userService.checkout(userId, cartId);
		ShoppingCart updatedCart = userService.findCart(cartId, userId); //Build cart on demand with external data as title and price of items
		return modelMapper.map(updatedCart, ShoppingCartDTO.class);
	}
}

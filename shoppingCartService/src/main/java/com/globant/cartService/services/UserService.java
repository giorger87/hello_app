package com.globant.cartService.services;


import java.util.List;


import org.springframework.stereotype.Service;

import com.globant.cartService.entities.ShoppingCart;
import com.globant.cartService.entities.User;
import com.globant.cartService.exceptions.CartNotFoundException;
import com.globant.cartService.exceptions.UserNotFoundException;
import com.globant.cartService.exceptions.UserUnauthorizedException;
import com.globant.cartService.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final ShoppingCartService shoppingCartService;
	public UserService(UserRepository userRepoistory, ShoppingCartService shoppingCartService) {
		this.userRepository = userRepoistory;
		this.shoppingCartService = shoppingCartService;
	}
	
	public List<ShoppingCart> findAllCarts(Long userId) throws UserNotFoundException {
		if (userRepository.existsById(userId)) {
			return shoppingCartService.findAll(userId);
		} 
		else {
			throw new UserNotFoundException(userId);
		}
	
	}
	
	public ShoppingCart findCart(Long cartId, Long userId) throws CartNotFoundException, UserUnauthorizedException {
		ShoppingCart cart = shoppingCartService.findById(cartId);
		if (cart.getUser().getUserId() == userId) {
			return cart;
		}
		else
			throw new UserUnauthorizedException(userId);		
	}
	
	public ShoppingCart addCart(Long userId,ShoppingCart newCart) throws CartNotFoundException {
		newCart.setUser(this.getUser(userId));
		newCart.setState("Pending");
		return shoppingCartService.create(newCart);

	}
	
	
	public User getUser(Long userId) {
		return userRepository.findById(userId)
		.map(user -> {
		      return user;
		})
		.orElse(this.createNewUser(userId));
	}
	
	public User createNewUser(Long userId) {
		return userRepository.save(new User(userId));

	}

	public void deleteCart(Long userId, Long cartId)
			throws UserNotFoundException, UserUnauthorizedException, CartNotFoundException {
		ShoppingCart cart = shoppingCartService.findById(cartId);
		if (userRepository.existsById(userId)) {
			if (cart.getUser().getUserId() == userId) {
				shoppingCartService.deleteById(cartId);
			} else {
				throw new UserUnauthorizedException(userId);
			}
		} else {
			throw new UserNotFoundException(userId);
		}

	}

	public void updateCart(Long userId, Long cartId, ShoppingCart updatedCart) throws CartNotFoundException, UserUnauthorizedException, UserNotFoundException {
		ShoppingCart cart = shoppingCartService.findById(cartId);
		if (userRepository.existsById(userId)) {
			if (cart.getUser().getUserId() == userId) {
				shoppingCartService.update(cartId, updatedCart);
			}else {
				throw new UserUnauthorizedException(userId);
			}
		}else {
			throw new UserNotFoundException(userId);
		}
		
		
	}


	
	
}

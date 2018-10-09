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
	
	
	 /**
	  * Retrieve all the shopping carts of one user from the shoppingCartService
	  * @param userId --> id of the user
	  * @throws UserNotFoundException If user does not exist
	  */
	public List<ShoppingCart> findAllCarts(Long userId) throws UserNotFoundException {
		if (userRepository.existsById(userId)) {
			return shoppingCartService.findAllCarts(userId);
		} 
		else {
			throw new UserNotFoundException(userId);
		}
	
	}
	
	
	
	
	 /**
	  * Retrieve one shopping cart by id from the shoppingCartService
	  * @param userId --> id of the user
	  *	@param cartId --> id of the shopping cart
	  * @throws UserUnauthorizedException If the cart does not belongs to the user
	  * @throws CartNotFoundException If the cart does not exist
	  */
	public ShoppingCart findCart(Long cartId, Long userId) throws CartNotFoundException, UserUnauthorizedException {
		ShoppingCart cart = shoppingCartService.findById(cartId);
		if (cart.getUser().getUserId() == userId) {
			return cart;
		}
		else
			throw new UserUnauthorizedException(userId);		
	}
	
	
	 /**
	  * Delegate to shoppingCartService the creation of a new shopping cart 
	  * and return the shopping cart already created.
	  * @param userId --> id of the user
	  *	@param newCart --> shopping cart that will be created
	  * @throws CartNotFoundException If the cart does not exist after creation
	  */
	public ShoppingCart createCart(Long userId,ShoppingCart newCart) throws CartNotFoundException {
		newCart.setUser(this.getUser(userId));
		newCart.setState("Pending");
		return shoppingCartService.create(newCart);

	}
	
	
	 /**
	  * Find the user in the userRepository or create a new user if not exist.
	  * @param userId --> id of the user
	  */
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

	
	 /**
	  * Delegate the removal of one shopping cart to the shoppingCartService
	  * @param userId --> id of the user
	  *	@param cartId --> id of the shopping cart
	  *	@throws UserNotFoundException If user does not exist
	  * @throws UserUnauthorizedException If the cart does not belongs to the user
	  * @throws CartNotFoundException If the cart does not exist
	  */
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

	
	 /**
	  * Delegate the update of one shopping cart to the shoppingCartService
	  * and return the shopping cart updated.
	  * @param userId --> id of the user
	  *	@param cartId --> id of the shopping cart
	  * @param updatedCart --> The shopping cart that will be update
	  *	@throws UserNotFoundException If user does not exist
	  * @throws UserUnauthorizedException If the cart does not belongs to the user
	  * @throws CartNotFoundException If the cart does not exist
	  */
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

	
	 /**
	  * Delegate the checkout of one shopping cart to the shoppingCartService
	  * and return the shopping cart updated.
	  * @param userId --> id of the user
	  *	@param cartId --> id of the shopping cart
	  *	@throws UserNotFoundException If user does not exist
	  * @throws UserUnauthorizedException If the cart does not belongs to the user
	  * @throws CartNotFoundException If the cart does not exist
	  */
	public void checkout(Long userId, Long cartId) throws CartNotFoundException, UserUnauthorizedException, UserNotFoundException {
		ShoppingCart cart = shoppingCartService.findById(cartId);
		if (userRepository.existsById(userId)) {
			if (cart.getUser().getUserId() == userId) {
				shoppingCartService.checkout(cartId);
			}else {
				throw new UserUnauthorizedException(userId);
			}
		}else {
			throw new UserNotFoundException(userId);
		}
		
	}


	
	
}

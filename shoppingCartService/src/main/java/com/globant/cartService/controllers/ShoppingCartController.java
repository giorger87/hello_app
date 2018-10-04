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

import com.globant.cartService.repositories.ShoppingCartRepository;


@RestController
public class ShoppingCartController {

	
	private final ShoppingCartRepository repository;

	ShoppingCartController(ShoppingCartRepository repository) {
		this.repository = repository;
	}
	

	@GetMapping("/carts")
	List<ShoppingCart> all() {
		return repository.findAll();
	}
	

	@GetMapping("/carts/{id}")
	ShoppingCart one(@PathVariable Long id) throws  CartNotFoundException {

		return repository.findById(id)
			.orElseThrow(() -> new CartNotFoundException(id));
	}
	
	
	@PostMapping("/carts")
	@ResponseStatus(HttpStatus.CREATED)
	ShoppingCart newCart(@RequestBody ShoppingCart newCart) {
		return repository.save(newCart);
	}
	
	@PutMapping("/carts/{id}")
	ShoppingCart replaceCart(@RequestBody ShoppingCart newCart, @PathVariable Long id) throws CartNotFoundException {

		return repository.findById(id)
			.map(cart -> {
				cart.setItems(newCart.getItems());
				cart.setState(newCart.getState());
				return repository.save(cart);
			})
			.orElseThrow(() -> new CartNotFoundException(id));
	}
	
	@DeleteMapping("/carts/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCart(@PathVariable Long id) throws CartNotFoundException {
		if (repository.existsById(id))
			repository.deleteById(id);
		else
			throw new CartNotFoundException(id);
			
	}
	
	

}

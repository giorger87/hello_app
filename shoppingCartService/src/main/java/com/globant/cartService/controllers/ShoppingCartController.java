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


import com.globant.cartService.services.ShoppingCartService;


@RestController
public class ShoppingCartController {

	
	private final ShoppingCartService shoppingCartService;

	ShoppingCartController(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}
	

/*	@GetMapping("/carts")
	List<ShoppingCart> all() {
		return shoppingCartService.findAll();
	}*/
	

	@GetMapping("/carts/{id}")
	ShoppingCart getCart(@PathVariable Long id) throws CartNotFoundException {
		return shoppingCartService.findById(id);
	}
	
	@PostMapping("/carts")
	@ResponseStatus(HttpStatus.CREATED)
	ShoppingCart createCart(@RequestBody ShoppingCart newCart) throws CartNotFoundException {
		return shoppingCartService.create(newCart);
	}
	
	
	@PutMapping("/carts/{id}")
	ShoppingCart replaceCart(@RequestBody ShoppingCart updatedCart, @PathVariable Long id) throws CartNotFoundException {
	    shoppingCartService.update(id, updatedCart);
		return shoppingCartService.findById(id); //Build cart on demand with external data as title and price of items
	}
	
	
	
	@DeleteMapping("/cartsNew/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCart(@PathVariable Long id) throws CartNotFoundException {
		shoppingCartService.deleteById(id);
	}
/*	
	@PutMapping("/carts/{cartId}/items/{itemId}")
	ShoppingCart addItem(@PathVariable Long itemId, @PathVariable Long cartId) throws CartNotFoundException {
	
		return repository.findById(cartId)
				.map(cart -> {
					cart.getItems().add(itemService.getItemById(itemId));
					return repository.save(cart);
				})
				.orElseThrow(() -> new CartNotFoundException(cartId));

	}*/
	
/*	@DeleteMapping("/carts/{cartId}/items/{itemId}")
	ShoppingCart removeItem(@PathVariable Long cartId , @PathVariable Long itemId) throws CartNotFoundException {
		return repository.findById(cartId)
				.map(cart -> {
				      Iterator iter =  cart.getItems().iterator();
				      while (iter.hasNext()) {
				         if(((Item) iter.next()).getItemId().longValue() == itemId) 
				        	 iter.remove();
				         
				      }
					return repository.save(cart);
				})
				.orElseThrow(() -> new CartNotFoundException(cartId));
			
	}*/

}

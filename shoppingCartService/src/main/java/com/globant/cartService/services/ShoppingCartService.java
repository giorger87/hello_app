

package com.globant.cartService.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.globant.cartService.entities.Item;
import com.globant.cartService.entities.ShoppingCart;
import com.globant.cartService.exceptions.CartNotFoundException;
import com.globant.cartService.repositories.ItemRepository;

import com.globant.cartService.repositories.ShoppingCartRepository;


@Service
public class ShoppingCartService {
	private final ShoppingCartRepository shoppingCartRepository;
	private final ItemRepository itemRepository;
	private final ItemService itemService;
	
	ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ItemService itemService, ItemRepository itemRepository) {
		this.shoppingCartRepository = shoppingCartRepository;
		this.itemRepository = itemRepository;
		this.itemService = itemService;
	}

	public ShoppingCart findById(Long id) throws CartNotFoundException {
		
		return shoppingCartRepository.findById(id)
			.map(cart -> {
				   List<Item> items = new ArrayList<Item>();
				     cart.getItems().forEach((item) ->{
				    	 items.add(itemService.getItemById(item.getId()));
				     });
				      cart.setItems(items);
				      return cart;
			})
			.orElseThrow(() -> new CartNotFoundException(id));
		
	}
	
	public ShoppingCart create(ShoppingCart newCart) throws CartNotFoundException {
		itemRepository.saveAll(newCart.getItems()); //Save item's ids
	    shoppingCartRepository.save(newCart);
	    return this.findById(newCart.getCartId()); 
	}

	public void deleteById(Long id) throws CartNotFoundException {
		if (shoppingCartRepository.existsById(id))
			shoppingCartRepository.deleteById(id);
		else
			throw new CartNotFoundException(id);	
	}

	public ShoppingCart update(Long id, ShoppingCart updatedCart) throws CartNotFoundException {
		return shoppingCartRepository.findById(id)
				.map(cart -> {
					cart.setItems(updatedCart.getItems());
					itemRepository.saveAll(cart.getItems());
					return shoppingCartRepository.save(cart);
					
				})
				.orElseThrow(() -> new CartNotFoundException(id));
	}

	public List<ShoppingCart> findAll(Long userId) {
		List<ShoppingCart> carts = shoppingCartRepository.findAll();
		shoppingCartRepository.findAll();
		List<ShoppingCart> userCarts = new ArrayList<ShoppingCart>();
		carts.forEach((cart) ->{
			try {
				if (cart.getUser().getUserId() == userId) {
					userCarts.add(this.findById(cart.getCartId()));
				}

			} catch (CartNotFoundException e) {
				// log
			}
		});
		return userCarts;


	}
	
	
	
}

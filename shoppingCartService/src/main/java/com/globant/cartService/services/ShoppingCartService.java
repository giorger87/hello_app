

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
	private  double total;
	ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ItemService itemService, ItemRepository itemRepository) {
		this.shoppingCartRepository = shoppingCartRepository;
		this.itemRepository = itemRepository;
		this.itemService = itemService;
	}

	 /**
	  * Retrieve one shopping cart from the shoppingCartRepository.
	  * Data of each item is retrieved on demand from itemService
	  * Total also is calculated on demand retrieving prices from itemService.
	  *	@param cartId --> id of the shopping cart
	  * @throws CartNotFoundException If the cart does not exist
	  */
	public ShoppingCart findById(Long id) throws CartNotFoundException {
		
		return shoppingCartRepository.findById(id)
			.map(cart -> {
				   List<Item> items = new ArrayList<Item>();
				   	 total = 0;
				     cart.getItems().forEach((item) ->{
				    	 Item tempItem = itemService.getItemById(item.getId());
				    	 items.add(tempItem);
				    	 total+=tempItem.getPrice();
				     });
				      cart.setItems(items);
				      cart.setTotal(total);
				      return cart;
			})
			.orElseThrow(() -> new CartNotFoundException(id));
		
	}
	
	 /**
	  * Create a new shopping cart and return the created shopping cart .
	  *	@param newCart --> shopping cart that will be created
	  * @throws CartNotFoundException If the cart does not exist after creation
	  */
	public ShoppingCart create(ShoppingCart newCart) throws CartNotFoundException {
		itemRepository.saveAll(newCart.getItems()); //Save item's ids
	    shoppingCartRepository.save(newCart);
	    return this.findById(newCart.getCartId()); 
	}

	
	 /**
	  * Delete one shopping cart from shoppingCartRepository
	  *	@param cartId --> id of the shopping cart
	  * @throws CartNotFoundException If the cart does not exist
	  */
	public void deleteById(Long id) throws CartNotFoundException {
		if (shoppingCartRepository.existsById(id))
			shoppingCartRepository.deleteById(id);
		else
			throw new CartNotFoundException(id);	
	}

	
	 /**
	  * Update one shopping cart from shoppingCartRepository and return the updated shopping cart .
	  *	@param cartId --> id of the shopping cart
	  * @throws CartNotFoundException If the cart does not exist
	  */
	public ShoppingCart update(Long id, ShoppingCart updatedCart) throws CartNotFoundException {
		return shoppingCartRepository.findById(id)
				.map(cart -> {
					cart.setItems(updatedCart.getItems());
					itemRepository.saveAll(cart.getItems());
					return shoppingCartRepository.save(cart);
					
				})
				.orElseThrow(() -> new CartNotFoundException(id));
	}

	
	
	 /**
	  * Retrieve all the shopping carts of one user from the shoppingCartRepository.
	  * Data of shopping cart's items is retrieved on demand from itemService.
	  * @param userId --> id of the user
	  */
	public List<ShoppingCart> findAllCarts(Long userId) {
		List<ShoppingCart> userCarts = shoppingCartRepository.findShoppingCartByUser_userId(userId);
		List<ShoppingCart> resultList = new ArrayList<>();
		userCarts.forEach((cart) ->{// Generate Shopping cart list with all data of items (price, title, etc)
			try {
				resultList.add(this.findById(cart.getCartId()));
			} catch (CartNotFoundException e) {
				
			}
		});

		return userCarts;
	}

	
	
	 /**
	  * Change state to "checkout" of one shopping cart in the shoppingCartRepository
	  * and return the  updated shopping cart
	  *	@param cartId --> id of the shopping cart

	  * @throws CartNotFoundException If the cart does not exist
	  */
	public ShoppingCart checkout(Long cartId) throws CartNotFoundException {
		return shoppingCartRepository.findById(cartId)
				.map(cart -> {
					cart.setState("Checkout");
					return shoppingCartRepository.save(cart);
					
				})
				.orElseThrow(() -> new CartNotFoundException(cartId));
	}
	
	
	
}

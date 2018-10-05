package com.globant.cartService.repositories;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.globant.cartService.entities.*;
import com.globant.cartService.services.ItemService;

@Configuration
@Slf4j
class LoadDatabase {

/*	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository,ItemRepository itemRepository, ShoppingCartRepository shoppingCartRepository) {
	//ADD COUNTER
		User mockUser = new User(1);
		userRepository.save(mockUser);
		ItemService itemService = new ItemService();

		List<Item> items = new ArrayList<Item>();
		items.add(itemService.getItemById((long) 1));
		items.add(itemService.getItemById((long) 2));

		itemRepository.saveAll(items); //Just the itemId
		
		List<Item> items2 = new ArrayList<Item>();
		items2.add(itemService.getItemById((long) 2));
		items2.add(itemService.getItemById((long) 3));
		itemRepository.saveAll(items2); //Just the itemId

	    //Create a mock shopping cart with all the items
	     ShoppingCart cart =  shoppingCartRepository.save(new ShoppingCart("Pending", items, mockUser));
	     ShoppingCart cart1 =  shoppingCartRepository.save(new ShoppingCart("Pending", items2, mockUser));
		return args -> {
			log.info("Creating Shopping Cart with the next items -> " + cart );
			log.info("Creating Shopping Cart with the next items -> " + cart1 );
			log.info("Creating Shopping Cart with the next items -> " + userRepository.findById((long) 1) );
		};
	}*/
}
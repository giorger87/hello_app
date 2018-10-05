package com.globant.cartService.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.globant.cartService.entities.Item;
import com.globant.cartService.exceptions.ItemNotFoundException;
import com.globant.cartService.services.ItemService;


@RestController
class ItemController {

	private final ItemService itemService;

	ItemController(ItemService  itemService) {
		this.itemService = itemService;
	}

	@GetMapping("/items")
	List<Item> all() {
		return itemService.getAll();
	}

	// Single item
	@GetMapping("/items/{id}")
	Item one(@PathVariable Long id) throws ItemNotFoundException {
		return itemService.getItemById(id);
				
	}

}
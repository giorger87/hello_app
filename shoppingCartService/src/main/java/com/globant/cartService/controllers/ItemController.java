package com.globant.cartService.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.globant.cartService.entities.Item;
import com.globant.cartService.exceptions.ItemNotFoundException;
import com.globant.cartService.repositories.ItemRepository;


@RestController
class ItemController {

	// private final ItemRepositoryImpl repository = new ItemRepositoryImpl();

	private final ItemRepository repository;

	ItemController(ItemRepository itemRepository) {
		this.repository = itemRepository;
	}

	@GetMapping("/items")
	List<Item> all() {
		return repository.findAll();
	}

	// Single item
	@GetMapping("/items/{id}")
	Item one(@PathVariable Long id) throws ItemNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(id));
	}

}
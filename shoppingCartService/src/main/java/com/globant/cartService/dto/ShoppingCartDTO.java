package com.globant.cartService.dto;

import java.util.List;

import com.globant.cartService.entities.Item;


public class ShoppingCartDTO {

	private Long id;

	private List<Item> items;
	private String state;

	public ShoppingCartDTO() {
			
		}

	public ShoppingCartDTO(String state, List<Item> items) {
			this.state=state;
			this.items = items;
		}

}

package com.globant.cartService.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.globant.cartService.entities.Item;



public class ShoppingCartDTO {

	private Long cartId;
    
	private List<Item> items;
	
	@JsonProperty(access = Access.READ_ONLY) //To avoid change state when create or update a cart
	private String state;

	public ShoppingCartDTO() {
			
		}

	public ShoppingCartDTO(String state, List<Item> items) {
			this.state=state;
			this.items = items;
		}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
}

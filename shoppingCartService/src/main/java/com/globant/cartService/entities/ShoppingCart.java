package com.globant.cartService.entities;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


import lombok.Data;

@Data
@Entity
public class ShoppingCart {
	private @Id @GeneratedValue Long cartId;
	

	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
    
	
    @ManyToMany
    private List <Item> items;
	private String state;
	
	
	public ShoppingCart() {
		
	}

	public ShoppingCart(String state, List<Item> items, User user) {
		this.state=state;
		this.items = items;
		this.user = user;
	}
	


}

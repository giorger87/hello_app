package com.globant.cartService.entities;


import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



import lombok.Data;

@Data
@Entity
public class ShoppingCart {
	private @Id @GeneratedValue Long id;
	
    
	@ManyToMany
    private List <Item> items;
	private String state;
	
	public ShoppingCart() {
		
	}

	public ShoppingCart(String state, List<Item> items) {
		this.state=state;
		this.items = items;
	}
	


}

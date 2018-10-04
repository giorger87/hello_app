package com.globant.cartService.entities;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Item {

	private @Id Long id;
	private String title;
	private double price;

	public Item() {
		
	}
	public Item(String title, double price) {
		this.title = title;
		this.price = price;
	}
	
	
}

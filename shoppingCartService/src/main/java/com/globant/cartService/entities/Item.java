package com.globant.cartService.entities;
import lombok.Data;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;




@Data
@Entity
public class Item {

	@Id
	private Long id;
	
/*	I do not persist those properties because I retrieve on demand from URL through ItemService
 * */
	@Transient
	private String title;
	@Transient
	private double price;


	
	public Item() {
		
	}
	public Item(String title, double price) {
		this.title = title;
		this.price = price;
	}
	
	
}

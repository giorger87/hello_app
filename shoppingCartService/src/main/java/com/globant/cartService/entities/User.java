package com.globant.cartService.entities;


import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.Data;

@Data
@Entity
public class User {

	@Id 
	private Long userId;

    
    public User() {
    	
    }
    public User(long id) {
    	this.userId = id;
    }
}

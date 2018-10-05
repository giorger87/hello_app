package com.globant.cartService.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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

package com.globant.cartService.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
public class ItemDTO {
	private @Id Long id;
	private String title;
	private double price;
}

package com.globant.cartService.services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.globant.cartService.dto.ItemDTO;
import com.globant.cartService.entities.Item;

@Service
public class ItemService {

	
	RestTemplate restTemplate = new RestTemplate();
	
	public Item getItemById(Long id) {
	     ResponseEntity<Item> itemsResponse =
		   	      restTemplate.exchange("http://challenge.getsandbox.com/articles/"+ id,
		   	                  HttpMethod.GET, null, new ParameterizedTypeReference<Item>() {
		   	          });
	     			
	     return itemsResponse.getBody();

		     
	}

	public List<Item> getAll(){
	     ResponseEntity<List<Item>> itemsResponse =
	   	      restTemplate.exchange("http://challenge.getsandbox.com/articles",
	   	                  HttpMethod.GET, null, new ParameterizedTypeReference<List<Item>>() {
	   	          });
	     return itemsResponse.getBody();
	}
}

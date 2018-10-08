package com.globant.cartService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.globant.cartService.entities.Item;

@Service
public class ItemService {
	   private final String url;
	   RestTemplate restTemplate = new RestTemplate();
	   
	    @Autowired
	    public ItemService(@Value("${item.service.url}") String url) {
	        this.url = url;
	    }
	
	
	
	public Item getItemById(Long id) {
	     ResponseEntity<Item> itemsResponse =
		   	      restTemplate.exchange(url + "/"+ id,
		   	                  HttpMethod.GET, null, new ParameterizedTypeReference<Item>() {
		   	          });
	     			
	     return itemsResponse.getBody();

		     
	}

	public List<Item> getAll(){
	     ResponseEntity<List<Item>> itemsResponse =
	   	      restTemplate.exchange(url,
	   	                  HttpMethod.GET, null, new ParameterizedTypeReference<List<Item>>() {
	   	          });
	     return itemsResponse.getBody();
	}
}
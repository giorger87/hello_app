package com.globant.cartService.repositories;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.globant.cartService.entities.*;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(ItemRepository itemRepository, ShoppingCartRepository shoppingCartRepository) {
		//RestTemplate is used for retrieve the items from the web service of the client
		RestTemplate restTemplate = new RestTemplate();
	     ResponseEntity<List<Item>> itemsResponse =
	      restTemplate.exchange("http://challenge.getsandbox.com/articles",
	                  HttpMethod.GET, null, new ParameterizedTypeReference<List<Item>>() {
	          });
	     List<Item> items= new ArrayList<Item>();
	     items= itemsResponse.getBody();
	     
	     itemRepository.saveAll(items);
	     
	     //Create a mock shopping cart with all the items
	     ShoppingCart cart =  shoppingCartRepository.save(new ShoppingCart("Pending", items));

		return args -> {
			log.info("Creating Shopping Cart with the next items -> " + cart );
		};
	}
}
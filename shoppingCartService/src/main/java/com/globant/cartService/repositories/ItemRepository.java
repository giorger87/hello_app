package com.globant.cartService.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.globant.cartService.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}

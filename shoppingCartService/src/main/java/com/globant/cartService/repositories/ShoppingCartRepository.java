package com.globant.cartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globant.cartService.entities.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}

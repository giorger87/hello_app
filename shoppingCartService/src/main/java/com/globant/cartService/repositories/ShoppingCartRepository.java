package com.globant.cartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globant.cartService.entities.ShoppingCart;
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}

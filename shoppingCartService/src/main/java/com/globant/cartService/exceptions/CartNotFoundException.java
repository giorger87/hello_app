package com.globant.cartService.exceptions;

public class CartNotFoundException extends Throwable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cartId;
	public CartNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public CartNotFoundException(Long id) {
		this.cartId=id;
	}

	public Long getCartId() {
		return cartId;
	}

	
}

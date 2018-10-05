package com.globant.cartService.exceptions;

public class UserNotFoundException extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	public UserNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(Long id) {
		this.userId=id;
	}

	public Long getUserId() {
		return userId;
	}

}

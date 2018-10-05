package com.globant.cartService.exceptions;

public class UserUnauthorizedException extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long userId;

	public UserUnauthorizedException() {
	}

	public UserUnauthorizedException(Long id) {
		this.userId = id;
	}

	public long getUserId() {
		return userId;
	}
}

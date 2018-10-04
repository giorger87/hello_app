package com.globant.cartService.exceptions;

public class ItemNotFoundException extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long itemId;

	public ItemNotFoundException() {
	}

	public ItemNotFoundException(Long id) {
		this.itemId = id;
	}

	public long getItemId() {
		return itemId;
	}

}

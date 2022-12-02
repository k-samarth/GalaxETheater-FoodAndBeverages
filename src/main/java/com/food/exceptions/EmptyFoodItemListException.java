package com.food.exceptions;

@SuppressWarnings("serial")
public class EmptyFoodItemListException extends RuntimeException {

	public EmptyFoodItemListException() {
	}

	public EmptyFoodItemListException(String message) {
		super(message);
		System.out.println(message);
		
	}

	public EmptyFoodItemListException(Throwable cause) {
		super(cause);
		
	}

	public EmptyFoodItemListException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyFoodItemListException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

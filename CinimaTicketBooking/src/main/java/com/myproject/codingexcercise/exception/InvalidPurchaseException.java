package com.myproject.codingexcercise.exception;

@SuppressWarnings("serial")
public class InvalidPurchaseException extends RuntimeException {

	public InvalidPurchaseException(String string) {
		System.out.println(string);
	}

}

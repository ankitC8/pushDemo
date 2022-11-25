package com.app.exception;

public class InsufficientBalanceException extends Exception implements AutoCloseable {

	public InsufficientBalanceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void close() throws Exception {
		System.out.println("Your resource is closed now");

	}

}

package com.example.session.exception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException (int id) {
		super("could not found the user with id"+ id);
	}
}

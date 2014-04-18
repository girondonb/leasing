package com.leasing2.exceptions;

public class GenericException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public GenericException(String logMessage)
	{
		this.message = logMessage; 
	}
}

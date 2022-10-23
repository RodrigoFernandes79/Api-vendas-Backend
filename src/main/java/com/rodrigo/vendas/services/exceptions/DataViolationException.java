package com.rodrigo.vendas.services.exceptions;

public class DataViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataViolationException(String message) {
		super(message);
	}

}

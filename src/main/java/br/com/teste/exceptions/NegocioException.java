package br.com.teste.exceptions;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegocioException() {

	}

	public NegocioException(String message) {
		super(message);
	}

}

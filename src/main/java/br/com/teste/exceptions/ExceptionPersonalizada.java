package br.com.teste.exceptions;

public class ExceptionPersonalizada extends Exception {

	private static final long serialVersionUID = 1L;

	public ExceptionPersonalizada() {

	}

	public ExceptionPersonalizada(String message) {
		super(message);
	}

}

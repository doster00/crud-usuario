package br.com.teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public abstract class TestePadrao {

	@Rule
	public ExpectedException verificadorDeExceptions = ExpectedException.none();

	@Before
	public abstract void antesDeExecutarOsTestes() throws Exception;

	@After
	public void depoisDeExecutarOsTestes() throws Exception {

	}

	public void verificaException(Class<? extends Exception> clazz, String message) {
		verificadorDeExceptions.expect(clazz);
		verificadorDeExceptions.expectMessage(message);
	}

}

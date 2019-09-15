package br.com.teste.utils;

public class ConexaoUtils {

	protected static final String URL_PADRAO = "jdbc:h2:mem:crud";
	protected static final String USUARIO = "sa";
	protected static final String SENHA = "";

	public static String url() {
		return URL_PADRAO;
	}
}

package br.com.teste.utils;

import java.security.MessageDigest;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Utils {

	public static final String USUARIO_MASTER = "master";
	public static final String USUARIO_MASTER_EMAIL = "master@master.com";
	
	public static final String MENSAGEM_CADASTRO_SUCESSO = "Registro salvo com sucesso.";
	public static final String MENSAGEM_ALTERADO_SUCESSO = "Registro alterado com sucesso.";
	public static final String MENSAGEM_EXCLUIDO_SUCESSO = "Registro excluído com sucesso.";
	public static final String MENSAGEM_ERRO_CADASTRO = "Erro ao cadastrar dados. Tente novamente. Se persistir entre em contato com o suporte";
	public static final String MENSAGEM_ERRO_EXCLUIR = "Erro ao excluir dados. Tente novamente. Se persistir entre em contato com o suporte";
	public static final String MENSAGEM_ERRO_LISTAR = "Erro ao listar dados. Tente novamente. Se persistir entre em contato com o suporte";
	public static final String MENSAGEM_ERRO_PADRAO = "Ocorreu um erro inesperado. Tente novamente. Se persistir entre em contato com o suporte";

	/*
	 * Utilitarios
	 */

	public static boolean isNuloOuVazio(String entrada) {
		return entrada == null || entrada.trim().isEmpty();
	}

	public static boolean isNuloOuVazio(Collection<?> entrada) {
		return entrada == null || entrada.isEmpty();
	}

	public static boolean isNuloOuVazio(Object entrada) {
		return entrada == null;
	}

	public static boolean isPreenchido(String entrada) {
		return !isNuloOuVazio(entrada);
	}

	public static boolean isPreenchido(Collection<?> entrada) {
		return !isNuloOuVazio(entrada);
	}

	public static boolean isPreenchido(Object entrada) {
		return !isNuloOuVazio(entrada);
	}

	public static String criptografaSenha(String senha) {
		String senhaCriptografada = "";

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte digest[] = messageDigest.digest(senha.getBytes("UTF-8"));
			StringBuilder sbSenha = new StringBuilder();

			for (byte b : digest) {
				sbSenha.append(String.format("%02X", 0xFF & b));
			}

			senhaCriptografada = sbSenha.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return senhaCriptografada;
	}

	public static void enviarMensagem(String mensagem) {
		enviarMensagem(mensagem, "", FacesMessage.SEVERITY_INFO);
	}

	public static void enviarMensagem(String mensagem, String detalhe, Severity nivel) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(nivel, detalhe, mensagem));
	}

}

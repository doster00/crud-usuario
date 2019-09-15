package br.com.teste.rn;

import br.com.teste.daos.UsuarioDAO;
import br.com.teste.entidades.Usuario;

public class UsuarioRN extends RegraNegocio<Usuario, UsuarioDAO> {

	@Override
	public void getDAOInstance() {
		setDaoPadrao(new UsuarioDAO());
	}

	public Usuario buscarPorNome(String email) {
		return getDaoPadrao().buscarPorEmail(email);
	}

	public Usuario buscarPorEmailESenha(String email, String senha) {
		return getDaoPadrao().buscarPorEmailESenha(email, senha);
	}

}

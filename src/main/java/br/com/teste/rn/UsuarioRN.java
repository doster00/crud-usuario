package br.com.teste.rn;

import br.com.teste.daos.UsuarioDAO;
import br.com.teste.entidades.Usuario;
import br.com.teste.exceptions.NegocioException;
import br.com.teste.utils.Utils;

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

	@Override
	public void verificaRestricoesComuns(Usuario usuario) throws NegocioException {
		if (Utils.isNuloOuVazio(usuario.getNome())) {
			// throw new NegocioException("Favor informar o nome");
		}

		if (Utils.isNuloOuVazio(usuario.getEmail())) {
			throw new NegocioException("Favor informar o e-mail");
		}

		if (Utils.isEmailInvalido(usuario.getEmail())) {
			throw new NegocioException("Favor informar um e-mail válido");
		}

		if (Utils.isNuloOuVazio(usuario.getSenha())) {
			throw new NegocioException("Favor informar a senha");
		}

		if (Utils.isNuloOuVazio(usuario.getTelefones())) {
			throw new NegocioException("Favor informar um telefone");
		}
	}

	@Override
	public void verificaRestricoesSalvar(Usuario usuario) throws NegocioException {
		verificaRestricoesComuns(usuario);

		Usuario usuarioSalvo = buscarPorNome(usuario.getNome());

		if (Utils.isPreenchido(usuarioSalvo)) {
			throw new NegocioException("O nome utilizado já está sendo usado por outro usuário.");
		}
	}

	@Override
	public void verificaRestricoesEditar(Usuario usuario) throws NegocioException {
		verificaRestricoesComuns(usuario);

		Usuario usuarioSalvo = buscarPorNome(usuario.getNome());

		if (Utils.isPreenchido(usuarioSalvo) && Utils.isPreenchido(usuarioSalvo.getId())
				&& !usuarioSalvo.getId().equals(usuario.getId())) {
			throw new NegocioException("O nome utilizado já está sendo usado por outro usuário.");
		}
	}

	@Override
	public void verificaRestricoesExcluir(Usuario usuario) throws NegocioException {

	}

}

package br.com.teste.services.usuario.alteracao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import br.com.teste.TestePadrao;
import br.com.teste.entidades.Telefone;
import br.com.teste.entidades.Usuario;
import br.com.teste.exceptions.NegocioException;
import br.com.teste.rn.UsuarioRN;

public class TesteAlteracaoUsuario extends TestePadrao {

	private UsuarioRN usuarioRN;
	private Usuario usuarioMock;

	@Override
	public void antesDeExecutarOsTestes() throws Exception {
		usuarioRN = new UsuarioRN();
		usuarioMock = criarUsuarioPadrao();
		inserirUsuarioMockCasoNaoExista();
	}

	private Usuario criarUsuarioPadrao() {
		Usuario usuario = new Usuario();
		usuario.setNome("Usuário");
		usuario.setEmail("usuario@email.com");
		usuario.setSenha("123456");
		Telefone telefone = new Telefone(81, "988884444", Telefone.TIPO_CELULAR);
		telefone.setUsuario(usuario);
		usuario.setTelefones(Arrays.asList(new Telefone[] { telefone }));
		return usuario;
	}

	private void inserirUsuarioMockCasoNaoExista() throws Exception {
		Usuario usuarioSalvo = verificaUsuarioPorEmailESenha();

		if (usuarioSalvo == null) {
			usuarioRN.salvar(usuarioMock);
		} else {
			usuarioMock = usuarioSalvo;
		}
	}

	private Usuario verificaUsuarioPorEmailESenha() {
		Usuario usuario = usuarioRN.buscarPorEmailESenha(usuarioMock.getEmail(), usuarioMock.getSenha());
		return usuario;
	}

	@Test
	public void testBuscarUsuarioPeloEmailESenha() {
		Usuario usuario = verificaUsuarioPorEmailESenha();
		assertEquals(usuarioMock.getId(), usuario.getId());
	}

	@Test
	public void testAlterarUsuarioRemovendoONome() throws Exception {
		verificaException(NegocioException.class, "Favor informar o nome");
		Usuario usuario = usuarioRN.buscarPorEmailESenha(usuarioMock.getEmail(), usuarioMock.getSenha());
		usuario.setNome(null);
		usuarioRN.atualizar(usuario);
	}

	@Test
	public void testAlterarUsuarioRemovendoOEmail() throws Exception {
		verificaException(NegocioException.class, "Favor informar o e-mail");
		Usuario usuario = usuarioRN.buscarPorEmailESenha(usuarioMock.getEmail(), usuarioMock.getSenha());
		usuario.setEmail(null);
		usuarioRN.atualizar(usuario);
	}

	@Test
	public void testAlterarUsuarioInformandoUmEmailInvalido() throws Exception {
		verificaException(NegocioException.class, "Favor informar um e-mail válido");
		Usuario usuario = usuarioRN.buscarPorEmailESenha(usuarioMock.getEmail(), usuarioMock.getSenha());
		usuario.setEmail("abcde1234");
		usuarioRN.atualizar(usuario);
	}

	@Test
	public void testAlterarUsuarioSemInformarTelefones() throws Exception {
		verificaException(NegocioException.class, "Favor informar um telefone");
		Usuario usuario = usuarioRN.buscarPorEmailESenha(usuarioMock.getEmail(), usuarioMock.getSenha());
		usuario.setTelefones(null);
		usuarioRN.atualizar(usuario);
	}

	@Test
	public void testAlterarUsuarioComTodosOsDadosCorretos() throws Exception {
		String mensagemSucesso = "Usuário alterado com sucesso";
		String novoNomeUsuario = "Usuario Alterado";
		String mensagemRetornada = null;
		Usuario usuario = null;

		usuario = verificaUsuarioPorEmailESenha();
		usuario.setNome(novoNomeUsuario);
		usuarioRN.atualizar(usuario);

		usuario = verificaUsuarioPorEmailESenha();
		mensagemRetornada = "Usuário alterado com sucesso";

		assertEquals(mensagemSucesso, mensagemRetornada);
		assertEquals(novoNomeUsuario, usuario.getNome());
	}

}

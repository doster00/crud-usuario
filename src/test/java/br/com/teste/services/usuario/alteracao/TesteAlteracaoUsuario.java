package br.com.teste.services.usuario.alteracao;

import java.util.Arrays;

import org.junit.Assert;

import br.com.teste.entidades.Telefone;
import br.com.teste.entidades.Usuario;
import br.com.teste.rn.UsuarioRN;
import junit.framework.TestCase;

public class TesteAlteracaoUsuario extends TestCase {

	private UsuarioRN usuarioRN;
	private Usuario usuarioMock;

	@Override
	protected void setUp() throws Exception {
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

	public void testBuscarUsuarioPeloEmailESenha() {
		Usuario usuario = verificaUsuarioPorEmailESenha();
		Assert.assertEquals(usuarioMock.getId(), usuario.getId());
	}

	public void testAlterarUsuarioRemovendoONome() {
		try {
			Usuario usuario = usuarioRN.buscarPorEmailESenha(usuarioMock.getEmail(), usuarioMock.getSenha());
			usuario.setNome(null);
			usuarioRN.atualizar(usuario);
		} catch (Exception e) {
			Assert.assertEquals("Favor informar o nome", e.getMessage());
		}
	}

	public void testAlterarUsuarioRemovendoOEmail() {
		try {
			Usuario usuario = usuarioRN.buscarPorEmailESenha(usuarioMock.getEmail(), usuarioMock.getSenha());
			usuario.setEmail(null);
			usuarioRN.atualizar(usuario);
		} catch (Exception e) {
			Assert.assertEquals("Favor informar o e-mail", e.getMessage());
		}
	}

	public void testAlterarUsuarioInformandoUmEmailInvalido() {
		try {
			Usuario usuario = usuarioRN.buscarPorEmailESenha(usuarioMock.getEmail(), usuarioMock.getSenha());
			usuario.setEmail("abcde1234");
			usuarioRN.atualizar(usuario);
		} catch (Exception e) {
			Assert.assertEquals("Favor informar um e-mail válido", e.getMessage());
		}
	}

	public void testAlterarUsuarioSemInformarTelefones() {
		try {
			Usuario usuario = usuarioRN.buscarPorEmailESenha(usuarioMock.getEmail(), usuarioMock.getSenha());
			usuario.setTelefones(null);
			usuarioRN.atualizar(usuario);
		} catch (Exception e) {
			Assert.assertEquals("Favor informar um telefone", e.getMessage());
		}
	}

	public void testAlterarUsuarioComTodosOsDadosCorretos() {
		String mensagemSucesso = "Usuário alterado com sucesso";
		String novoNomeUsuario = "Usuario Alterado";
		String mensagemRetornada = null;
		Usuario usuario = null;

		try {
			usuario = verificaUsuarioPorEmailESenha();
			usuario.setNome(novoNomeUsuario);
			usuarioRN.atualizar(usuario);

			usuario = verificaUsuarioPorEmailESenha();
			mensagemRetornada = "Usuário alterado com sucesso";
		} catch (Exception e) {
			mensagemRetornada = "Erro ao alterar";
			e.printStackTrace();
		}

		Assert.assertEquals(mensagemSucesso, mensagemRetornada);
		Assert.assertEquals(novoNomeUsuario, usuario.getNome());
	}
}

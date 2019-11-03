package br.com.teste.services.usuario.inclusao;

import java.util.Arrays;

import br.com.teste.entidades.Telefone;
import br.com.teste.entidades.Usuario;
import br.com.teste.rn.UsuarioRN;
import junit.framework.TestCase;

public class TesteInclusaoUsuario extends TestCase {

	private UsuarioRN usuarioRN;
	private Usuario usuarioMock;

	@Override
	protected void setUp() throws Exception {
		usuarioRN = new UsuarioRN();
		usuarioMock = criarUsuarioPadrao();
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

	public void testInserirUsuarioSemInformarNome() {
		boolean temErro = false;
		try {
			usuarioMock.setNome(null);
			usuarioRN.salvar(usuarioMock);
		} catch (Exception e) {
			assertEquals("Favor informar o nome", e.getMessage());
			temErro = true;
		}
		assertTrue(temErro);
	}

	public void testInserirUsuarioSemInformarEmail() {
		boolean temErro = false;
		try {
			usuarioMock.setEmail(null);
			usuarioRN.salvar(usuarioMock);
		} catch (Exception e) {
			temErro = true;
			assertEquals("Favor informar o e-mail", e.getMessage());
		}
		assertTrue(temErro);
	}

	public void testInserirUsuarioComEmailInvalido() {
		boolean temErro = false;
		try {
			usuarioMock.setEmail("abcde123");
			usuarioRN.salvar(usuarioMock);
		} catch (Exception e) {
			temErro = true;
			assertEquals("Favor informar um e-mail válido", e.getMessage());
		}
		assertTrue(temErro);
	}

	public void testInserirUsuarioSemInformarTelefones() {
		boolean temErro = false;
		try {
			usuarioMock.setTelefones(null);
			usuarioRN.salvar(usuarioMock);
		} catch (Exception e) {
			temErro = true;
			assertEquals("Favor informar um telefone", e.getMessage());
		}
		assertTrue(temErro);
	}

	public void testInserirUsuarioComTodosOsDadosCorretos() {
		String mensagemSucesso = "Usuário salvo com sucesso";
		String mensagemRetornada = null;
		boolean temErro = false;

		try {
			usuarioRN.salvar(usuarioMock);
			mensagemRetornada = "Usuário salvo com sucesso";
		} catch (Exception e) {
			temErro = true;
			mensagemRetornada = "Erro ao inserir";
		}

		assertFalse(temErro);
		assertEquals(mensagemSucesso, mensagemRetornada);
	}

}

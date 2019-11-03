package br.com.teste.services.usuario.inclusao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import br.com.teste.TestePadrao;
import br.com.teste.entidades.Telefone;
import br.com.teste.entidades.Usuario;
import br.com.teste.exceptions.NegocioException;
import br.com.teste.rn.UsuarioRN;

public class TesteInclusaoUsuario extends TestePadrao {

	private UsuarioRN usuarioRN;
	private Usuario usuarioMock;

	@Override
	public void antesDeExecutarOsTestes() throws Exception {
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

	@Test
	public void testInserirUsuarioSemInformarNome() throws Exception {
		verificaException(NegocioException.class, "Favor informar o nome");
		usuarioMock.setNome(null);
		usuarioRN.salvar(usuarioMock);
	}

	@Test
	public void testInserirUsuarioSemInformarEmail() throws Exception {
		verificaException(NegocioException.class, "Favor informar o e-mail");
		usuarioMock.setEmail(null);
		usuarioRN.salvar(usuarioMock);
	}

	@Test
	public void testInserirUsuarioComEmailInvalido() throws Exception {
		verificaException(NegocioException.class, "Favor informar um e-mail válido");
		usuarioMock.setEmail("abcde123");
		usuarioRN.salvar(usuarioMock);
	}

	@Test
	public void testInserirUsuarioSemInformarTelefones() throws Exception {
		verificaException(NegocioException.class, "Favor informar um telefone");
		usuarioMock.setTelefones(null);
		usuarioRN.salvar(usuarioMock);
	}

	@Test
	public void testInserirUsuarioComTodosOsDadosCorretos() throws Exception {
		String mensagemSucesso = "Usuário salvo com sucesso";
		String mensagemRetornada = null;

		usuarioRN.salvar(usuarioMock);
		mensagemRetornada = "Usuário salvo com sucesso";

		assertEquals(mensagemSucesso, mensagemRetornada);
	}

}

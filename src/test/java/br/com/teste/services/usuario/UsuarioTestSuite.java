package br.com.teste.services.usuario;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.teste.services.usuario.alteracao.TesteAlteracaoUsuario;
import br.com.teste.services.usuario.inclusao.TesteInclusaoUsuario;

@RunWith(Suite.class)
@SuiteClasses({ //
		TesteInclusaoUsuario.class, //
		TesteAlteracaoUsuario.class //
})
public class UsuarioTestSuite {

}

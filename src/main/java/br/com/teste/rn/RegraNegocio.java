package br.com.teste.rn;

import java.util.List;

import br.com.teste.daos.DAOPadrao;
import br.com.teste.entidades.Entidade;
import br.com.teste.exceptions.NegocioException;

public abstract class RegraNegocio<T extends Entidade, E extends DAOPadrao<T>> {

	private T entidade;
	private E daoPadrao;

	public abstract void getDAOInstance();

	public abstract void verificaRestricoesComuns(T entidade) throws NegocioException;

	public abstract void verificaRestricoesSalvar(T entidade) throws NegocioException;

	public abstract void verificaRestricoesEditar(T entidade) throws NegocioException;

	public abstract void verificaRestricoesExcluir(T entidade) throws NegocioException;;

	/*
	 * Construtor
	 */

	public RegraNegocio() {
		getDAOInstance();
	}

	/*
	 * Especificos
	 */

	public void salvar(T entidade) throws Exception {
		verificaRestricoesSalvar(entidade);
		daoPadrao.salvar(entidade);
	}

	public void atualizar(T entidade) throws Exception {
		verificaRestricoesEditar(entidade);
		daoPadrao.atualizar(entidade);
	}

	public void excluir(T entidade) throws Exception {
		verificaRestricoesExcluir(entidade);
		daoPadrao.excluir(entidade);
	}

	public List<T> listarTodos() {
		return daoPadrao.listarTodos();
	}

	public T getById(Long id) {
		return daoPadrao.getById(id);
	}

	/*
	 * Getters e Setters
	 */

	public T getEntidade() {
		return entidade;
	}

	public void setEntidade(T entidade) {
		this.entidade = entidade;
	}

	public E getDaoPadrao() {
		return daoPadrao;
	}

	public void setDaoPadrao(E daoPadrao) {
		this.daoPadrao = daoPadrao;
	}

}

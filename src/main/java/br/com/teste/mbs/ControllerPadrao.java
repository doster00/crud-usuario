package br.com.teste.mbs;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.teste.entidades.Entidade;
import br.com.teste.exceptions.NegocioException;
import br.com.teste.rn.RegraNegocio;
import br.com.teste.utils.Utils;

public abstract class ControllerPadrao<T extends Entidade, E extends RegraNegocio<T, ?>> implements Serializable {

	private static final long serialVersionUID = 1L;

	private E regraNegocio;
	private T entidade;
	private T registroSelecionado;
	private List<T> registros;

	public abstract void novo() throws Exception;

	public abstract void editar() throws Exception;

	public abstract void excluir() throws Exception;

	public abstract void salvar() throws Exception;

	public abstract void pesquisar() throws Exception;

	public abstract void limpar() throws Exception;

	public abstract void selecionar() throws Exception;

	public abstract void iniciarTela() throws Exception;

	/*
	 * Hooks
	 */

	@PostConstruct
	public void init() {
		try {
			iniciarTela();
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	public void novo(ActionEvent ae) {
		try {
			novo();
		} catch (NegocioException e) {
			Utils.enviarMensagem(e.getMessage());
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent ae) {
		try {
			editar();
		} catch (NegocioException e) {
			Utils.enviarMensagem(e.getMessage());
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	public void salvar(ActionEvent ae) {
		try {
			salvar();
			Utils.enviarMensagem(Utils.MENSAGEM_CADASTRO_SUCESSO);
			limpar();
			pesquisar();
			RequestContext.getCurrentInstance().execute("PF('dlgPrincipalWvar').hide()");
		} catch (NegocioException e) {
			Utils.enviarMensagem(e.getMessage());
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent ae) {
		try {
			excluir();
			pesquisar();
			Utils.enviarMensagem(Utils.MENSAGEM_EXCLUIDO_SUCESSO);
			limpar();
		} catch (NegocioException e) {
			Utils.enviarMensagem(e.getMessage());
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void onRowSelect(SelectEvent event) {
		try {
			setRegistroSelecionado((T) event.getObject());
			selecionar();
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		setRegistroSelecionado(null);
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

	public E getRegraNegocio() {
		return regraNegocio;
	}

	public void setRegraNegocio(E regraNegocio) {
		this.regraNegocio = regraNegocio;
	}

	public List<T> getRegistros() {
		return registros;
	}

	public void setRegistros(List<T> registros) {
		this.registros = registros;
	}

	public T getRegistroSelecionado() {
		return registroSelecionado;
	}

	public void setRegistroSelecionado(T registroSelecionado) {
		this.registroSelecionado = registroSelecionado;
	}

}

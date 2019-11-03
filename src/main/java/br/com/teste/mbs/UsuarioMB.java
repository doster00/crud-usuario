package br.com.teste.mbs;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.teste.entidades.Telefone;
import br.com.teste.entidades.Usuario;
import br.com.teste.exceptions.NegocioException;
import br.com.teste.rn.UsuarioRN;
import br.com.teste.utils.Utils;

@ManagedBean
@ViewScoped
public class UsuarioMB extends ControllerPadrao<Usuario, UsuarioRN> {

	private static final long serialVersionUID = 1L;

	private Telefone telefone;
	private Telefone telefoneSelecionado;
	private List<Telefone> telefones;
	private List<Telefone> telefonesRemovidos;

	@Override
	public void novo() throws Exception {
		limpar();
	}

	@Override
	public void editar() throws Exception {

	}

	@Override
	public void excluir() throws Exception {
		getRegraNegocio().excluir(getRegistroSelecionado());
	}

	@Override
	public void salvar() throws Exception {
		adicionarTelefoneAoUsuario();
		if (Utils.isPreenchido(getEntidade()) && Utils.isNuloOuVazio(getEntidade().getId())) {
			getRegraNegocio().salvar(getEntidade());
		} else {
			getRegraNegocio().atualizar(getEntidade());
		}
	}

	private void adicionarTelefoneAoUsuario() {
		telefones.forEach(telefone -> {
			telefone.setUsuario(getEntidade());
		});
		getEntidade().setTelefones(telefones);
	}

	@Override
	public void pesquisar() throws Exception {
		setRegistros(getRegraNegocio().listarTodos());
	}

	@Override
	public void limpar() throws Exception {
		setEntidade(new Usuario());
		setRegraNegocio(new UsuarioRN());
		setTelefone(new Telefone());
		setTelefones(new ArrayList<Telefone>());
		setTelefonesRemovidos(new ArrayList<Telefone>());
	}

	@Override
	public void selecionar() throws Exception {
		setEntidade(getRegistroSelecionado());
		setTelefones(getRegistroSelecionado().getTelefones());
	}

	@Override
	public void iniciarTela() throws Exception {
		limpar();
		pesquisar();
	}

	public void adicionarTelefoneGrid() {
		try {
			verificaTelefoneAntes();

			if (Utils.isPreenchido(getTelefones()) && getTelefoneSelecionado() != null) {
				getTelefones().remove(getTelefoneSelecionado());
			}

			getTelefones().add(getTelefone());
			setTelefone(new Telefone());
		} catch (NegocioException e) {
			Utils.enviarMensagem(e.getMessage());
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	private void verificaTelefoneAntes() throws NegocioException {
		if (Utils.isNuloOuVazio(getTelefone().getDdd())) {
			throw new NegocioException("Favor informar o DDD");
		}

		if (Utils.isNuloOuVazio(getTelefone().getNumero())) {
			throw new NegocioException("Favor informar o número");
		}

		if (Utils.isNuloOuVazio(getTelefone().getTipo())) {
			throw new NegocioException("Favor informar o tipo");
		}
	}

	public void removerItemGrid(Telefone telefone) {
		try {
			getTelefones().remove(telefone);
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
		}
	}

	public void selecionaTelefoneGrid() {
		try {
			setTelefone(copiarTelefone());
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
		}
	}

	private Telefone copiarTelefone() {
		Telefone telefone = new Telefone();
		telefone.setId(getTelefoneSelecionado().getId());
		telefone.setDdd(getTelefoneSelecionado().getDdd());
		telefone.setNumero(getTelefoneSelecionado().getNumero());
		telefone.setTipo(getTelefoneSelecionado().getTipo());
		telefone.setUsuario(getTelefoneSelecionado().getUsuario());
		telefone.setIdDataTable(getTelefoneSelecionado().getIdDataTable());
		return telefone;
	}

	/*
	 * Getters e Setters
	 */

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Telefone> getTelefonesRemovidos() {
		return telefonesRemovidos;
	}

	public void setTelefonesRemovidos(List<Telefone> telefonesRemovidos) {
		this.telefonesRemovidos = telefonesRemovidos;
	}

	public Telefone getTelefoneSelecionado() {
		return telefoneSelecionado;
	}

	public void setTelefoneSelecionado(Telefone telefoneSelecionado) {
		this.telefoneSelecionado = telefoneSelecionado;
	}

}

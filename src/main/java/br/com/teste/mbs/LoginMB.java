package br.com.teste.mbs;

import java.io.Serializable;
import java.util.Arrays;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.teste.entidades.Telefone;
import br.com.teste.entidades.Usuario;
import br.com.teste.rn.UsuarioRN;
import br.com.teste.utils.Utils;

@ManagedBean(name = "loginMB")
@ViewScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	/*
	 * Construtor
	 */

	public LoginMB() throws Exception {
		criaUsuarioPadrao();
	}

	/*
	 * Especificos
	 */

	private void criaUsuarioPadrao() {
		UsuarioRN usuarioRN = new UsuarioRN();
		Usuario usuario = usuarioRN.buscarPorEmailESenha(Utils.USUARIO_MASTER_EMAIL, Utils.USUARIO_MASTER);

		if (usuario == null) {
			usuario = new Usuario();
			usuario.setNome(Utils.USUARIO_MASTER);
			usuario.setSenha(Utils.USUARIO_MASTER);
			usuario.setEmail(Utils.USUARIO_MASTER_EMAIL);

			Telefone telefone = new Telefone();
			telefone.setDdd(88);
			telefone.setNumero("123456789");
			telefone.setTipo(Telefone.TIPO_CELULAR);
			telefone.setUsuario(usuario);
			Telefone[] telefones = { telefone };
			usuario.setTelefones(Arrays.asList(telefones));

			usuarioRN.salvar(usuario);
		}
	}

	public String doLogin() throws Exception {
		try {
			UsuarioRN usuarioRN = new UsuarioRN();

			setUsuario(usuarioRN.buscarPorEmailESenha(getUsuario().getEmail(), getUsuario().getSenha()));

			if (Utils.isPreenchido(getUsuario())) {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(false);

				session.setAttribute("usuario", usuario);

				return "/pages/frm_index?faces-redirect=true";
			} else {
				throw new Exception("Favor verificar o e-mail e a senha.");
			}
		} catch (Exception e) {
			setUsuario(new Usuario());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "/frm_login?faces-redirect=true";
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/frm_login?faces-redirect=true";
	}

	/*
	 * Getters e Setters
	 */

	public Usuario getUsuario() {
		return usuario;
	}

	public Usuario getUsuarioLogado() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return (Usuario) session.getAttribute("usuario");
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

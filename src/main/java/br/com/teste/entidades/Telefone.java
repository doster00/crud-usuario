package br.com.teste.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_telefone")
public class Telefone extends Entidade {

	private static final long serialVersionUID = 1L;

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_USUARIO = "usuario";
	public static final String COLUMN_DDD = "ddd";
	public static final String COLUMN_NUMERO = "numero";
	public static final String COLUMN_TIPO = "tipo";

	public static final String TIPO_FIXO = "F";
	public static final String TIPO_CELULAR = "C";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column(name = "ddd")
	private Integer ddd;

	@Column(name = "numero")
	private String numero;

	@Column(name = "tipo")
	private String tipo;
	
	@Transient
	private Long idDataTable = new Date().getTime();

	/*
	 * Getters e Setters
	 */

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public String getTipoFormatado() {
		return tipo != null ? TIPO_FIXO.equals(tipo) ? "Fixo" : "Celular" : "";
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdDataTable() {
		return idDataTable;
	}

	public void setIdDataTable(Long idDataTable) {
		this.idDataTable = idDataTable;
	}

}

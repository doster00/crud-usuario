package br.com.teste.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.teste.entidades.Entidade;
import br.com.teste.utils.HibernateUtil;
import br.com.teste.utils.Utils;

public abstract class DAOPadrao<T extends Entidade> {

	private T entidade;

	private Session session;

	public abstract Criteria createCriteriaConsultarTodos();

	public void salvar(T entidade) {
		try {
			getSession().beginTransaction();
			getSession().persist(entidade);
			getSession().getTransaction().commit();

		} finally {
			getSession().close();
		}
	}

	public void atualizar(T entidade) {
		try {
			getSession().beginTransaction();
			getSession().merge(entidade);
			getSession().getTransaction().commit();
		} finally {
			getSession().close();
		}
	}

	public void excluir(T entidade) {
		try {
			getSession().beginTransaction();
			getSession().delete(entidade);
			getSession().getTransaction().commit();
		} finally {
			getSession().close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> listarTodos() {
		getSession().beginTransaction();
		Criteria ct = createCriteriaConsultarTodos();
		List<T> registros = Utils.isPreenchido(ct) ? ct.list() : null;
		getSession().getTransaction().commit();
		return registros;
	}

	@SuppressWarnings("unchecked")
	public T getById(Long id) {
		return (T) getSession().get(entidade.getClass(), id);
	}

	/*
	 * Getters e Setters
	 */

	public Session getSession() {
		if (session == null || !session.isOpen()) {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
		}

		return session;
	}

	public T getEntidade() {
		return entidade;
	}

	public void setEntidade(T entidade) {
		this.entidade = entidade;
	}

}

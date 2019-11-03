package br.com.teste.daos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.com.teste.entidades.Usuario;
import br.com.teste.utils.Utils;

public class UsuarioDAO extends DAOPadrao<Usuario> {

	@Override
	public Criteria createCriteriaConsultarTodos() {
		Criteria ct = getSession().createCriteria(Usuario.class);
		ct.createAlias(Usuario.COLUMN_TELEFONES, Usuario.COLUMN_TELEFONES, JoinType.LEFT_OUTER_JOIN);
		ct.add(Restrictions.ne(Usuario.COLUMN_EMAIL, Utils.USUARIO_MASTER_EMAIL));
		ct.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return ct;
	}

	public Usuario buscarPorEmail(String email) {
		getSession().beginTransaction();
		Criteria ct = getSession().createCriteria(Usuario.class);
		ct.add(Restrictions.eq(Usuario.COLUMN_EMAIL, email));
		Usuario usuario = (Usuario) ct.uniqueResult();
		getSession().getTransaction().commit();
		return usuario;
	}

	public Usuario buscarPorEmailESenha(String email, String senha) {
		getSession().beginTransaction();
		Criteria ct = getSession().createCriteria(Usuario.class);
		ct.createAlias(Usuario.COLUMN_TELEFONES, Usuario.COLUMN_TELEFONES, JoinType.LEFT_OUTER_JOIN);
		ct.add(Restrictions.eq(Usuario.COLUMN_EMAIL, email));
		ct.add(Restrictions.eq(Usuario.COLUMN_SENHA, senha));
		ct.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Usuario usuario = (Usuario) ct.uniqueResult();
		getSession().getTransaction().commit();
		return usuario;
	}
}

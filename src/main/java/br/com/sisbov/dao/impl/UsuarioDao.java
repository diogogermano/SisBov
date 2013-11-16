package br.com.sisbov.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.sisbov.dao.GenericHibernateDao;
import br.com.sisbov.persistence.Usuario;

public class UsuarioDao extends GenericHibernateDao<Usuario, Long> {
	
	 public Boolean logar(String nome, String senha) {
			Criteria criteria = getSession().createCriteria(Usuario.class);
			criteria.add(Restrictions.eq("login", nome));
			criteria.add(Restrictions.eq("senha", senha));
			return criteria.uniqueResult() != null;
		    }

}

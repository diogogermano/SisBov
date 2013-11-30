package br.com.sisbov.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.sisbov.dao.GenericHibernateDao;
import br.com.sisbov.persistence.Proprietario;


public class ProprietarioDao extends GenericHibernateDao<Proprietario, Long>{

	public List<Proprietario> pesquisarProprietarioPorNome(
			String nomeProprietario) {
		Criteria criteria = getSession().createCriteria(Proprietario.class);
		if (nomeProprietario != "") {
			criteria.add(Restrictions.like("nome", nomeProprietario, MatchMode.ANYWHERE));
		}
		return criteria.list();
	}
}

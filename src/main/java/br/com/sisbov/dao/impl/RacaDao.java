package br.com.sisbov.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.sisbov.dao.GenericHibernateDao;
import br.com.sisbov.persistence.Raca;


public class RacaDao extends GenericHibernateDao<Raca, Long>{
	
	public List<Raca> pesquisarRacaPorNome(
			String nomeRaca) {
		Criteria criteria = getSession().createCriteria(Raca.class);
		if (nomeRaca != "") {
			criteria.add(Restrictions.like("nome", nomeRaca, MatchMode.ANYWHERE));
		}
		return criteria.list();
	}

}

package br.com.sisbov.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.sisbov.dao.GenericHibernateDao;
import br.com.sisbov.persistence.Propriedade;


public class PropriedadeDao extends GenericHibernateDao<Propriedade, Long>{
	
	public List<Propriedade> pesquisarPropriedadePorNome(
			String nomePropriedade) {
		Criteria criteria = getSession().createCriteria(Propriedade.class);
		if (nomePropriedade != "") {
			criteria.add(Restrictions.like("nome", nomePropriedade, MatchMode.ANYWHERE));
		}
		return criteria.list();
	}

}

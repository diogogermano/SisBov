package br.com.sisbov.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.sisbov.dao.GenericHibernateDao;
import br.com.sisbov.persistence.Animal;


public class AnimalDao extends GenericHibernateDao<Animal, Long>{
	public List<Animal> pesquisarAnimalPorNome(
			String nomeAnimal) {
		Criteria criteria = getSession().createCriteria(Animal.class);
		if (nomeAnimal != "") {
			criteria.add(Restrictions.like("nome", nomeAnimal, MatchMode.ANYWHERE));
		}
		return criteria.list();
	}

}

package br.com.sisbov.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

public interface GenericDao<T, ID extends Serializable> {

	void save(T entity);

	void merge(T entity);

	void delete(T entity);

	List<T> findMany(Query query);

	T findOne(Query query);

	T findOne(Criteria criteria);
	
	T findOne(SQLQuery sqlQuery);
	
	List findAll();

	T findByID(Long id);

}

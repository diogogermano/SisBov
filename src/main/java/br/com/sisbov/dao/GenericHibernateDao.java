package br.com.sisbov.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sisbov.utils.HibernateUtils;

public abstract class GenericHibernateDao<T, ID extends Serializable>
		implements GenericDao<T, ID> {

	private Class<T> persistentClass;
	private Session session;

	public GenericHibernateDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public void setSession(Session s) {
		this.session = s;
	}

	protected Session getSession() {
		if (session == null) {
			session = HibernateUtils.getSession();
		}
		return session;
	}

	@Override
	public void save(T entity) {
		Session hibernateSession = this.getSession();
		Transaction t = hibernateSession.beginTransaction();
		hibernateSession.save(entity);
		t.commit();

	}

	@Override
	public void merge(T entity) {
		Session hibernateSession = this.getSession();
		Transaction t = hibernateSession.beginTransaction();
		hibernateSession.merge(entity);
		t.commit();
	}

	@Override
	public void delete(T entity) {
		Session hibernateSession = this.getSession();
		Transaction t = hibernateSession.beginTransaction();
		hibernateSession.delete(entity);
		t.commit();
	}

	@Override
	public List<T> findMany(Query query) {
		List<T> t;
		t = (List<T>) query.list();
		return t;
	}

	@Override
	public T findOne(Query query) {
		T t;
		t = (T) query.uniqueResult();
		return t;
	}

	@Override
	public T findByID(Long id) {
		Session hibernateSession = this.getSession();
		T t = null;
		t = (T) hibernateSession.get(getPersistentClass().getName(), id);
		return t;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public List findAll() {
		Session hibernateSession = this.getSession();
		List T = null;
		Query query = hibernateSession.createQuery("from "
				+ getPersistentClass().getName());
		T = query.list();
		return T;
	}
	
	public Query createQuery(String query){
		return HibernateUtils.getSession().createQuery(query);
	}

	@Override
	public T findOne(Criteria criteria) {
		T t;
		t = (T) criteria.uniqueResult();
		return t;
	}

	@Override
	public T findOne(SQLQuery sqlQuery) {
		T t;
		t = (T) sqlQuery.uniqueResult();
		return t;
	}

	
}

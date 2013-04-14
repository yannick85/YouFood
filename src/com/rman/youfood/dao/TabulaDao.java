package com.rman.youfood.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.rman.youfood.entity.Tabula;

public class TabulaDao {
	private EntityManagerFactory emf;
	@Resource private EntityTransaction myTrans; 

	public TabulaDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tabula> getAllTabula() {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT t FROM Tabula t");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public Tabula getTabulaById(Long tabulaId) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Tabula.class, tabulaId);
		} finally {
			em.close();
		}
	}
	
	public Tabula addTabula(Tabula tabula) {
		// TODO Auto-generated method stub
		Tabula result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(tabula);
			em.getTransaction().commit();
			result = tabula;
		} finally {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			em.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Tabula> getTableByZone(Long zoneId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT t FROM Tabula t WHERE t.zone="+String.valueOf(zoneId)+"");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	public void updateTabula(Tabula table) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.joinTransaction();
			em.merge(table);
			em.flush();
			myTrans.commit();
		} finally {
			em.close();
		}
	}
	public void deleteTabula(Tabula table) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.remove(em.merge(table));
			myTrans.commit();
		} finally {
			em.close();
		}
	}
}

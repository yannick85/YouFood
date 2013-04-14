package com.rman.youfood.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.rman.youfood.entity.Waiter;

public class WaiterDao {
	private EntityManagerFactory emf;
	@Resource private EntityTransaction myTrans; 

	public WaiterDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@SuppressWarnings("unchecked")
	public List<Waiter> getAllWaiter() {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT w FROM Waiter w");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Waiter> getWaiterByRestaurant(Long restaurantId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT w FROM Waiter w WHERE w.restaurant="+String.valueOf(restaurantId)+"");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public Waiter getWaiterById(Long waiterId) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Waiter.class, waiterId);
		} finally {
			em.close();
		}
	}
	
	public Waiter addWaiter(Waiter waiter) {
		// TODO Auto-generated method stub
		Waiter result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(waiter);
			em.getTransaction().commit();
			result = waiter;
		} finally {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			em.close();
		}
		return result;
	}
	public void updateWaiter(Waiter waiter) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.joinTransaction();
			em.merge(waiter);
			em.flush();
			myTrans.commit();
		} finally {
			em.close();
		}
	}
	public void deleteWaiter(Waiter waiter) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.remove(em.merge(waiter));
			myTrans.commit();
		} finally {
			em.close();
		}
	}
}

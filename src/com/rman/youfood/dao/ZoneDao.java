package com.rman.youfood.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.rman.youfood.entity.Zone;

public class ZoneDao {
	private EntityManagerFactory emf;
	@Resource private EntityTransaction myTrans; 


	public ZoneDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@SuppressWarnings("unchecked")
	public List<Zone> getAllZone() {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT z FROM Zone z");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Zone> getZoneByRestaurant(Long restaurantId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT z FROM Zone z WHERE z.restaurant="+String.valueOf(restaurantId)+"");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public Zone getZoneById(Long zoneId) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Zone.class, zoneId);
		} finally {
			em.close();
		}
	}
	
	public Zone addZone(Zone zone) {
		// TODO Auto-generated method stub
		Zone result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(zone);
			em.getTransaction().commit();
			result = zone;
		} finally {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			em.close();
		}
		return result;
	}
	public void updateZone(Zone zone) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.joinTransaction();
			em.merge(zone);
			em.flush();
			myTrans.commit();
		} finally {
			em.close();
		}
	}
	public void deleteZone(Zone zone) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.remove(em.merge(zone));
			myTrans.commit();
		} finally {
			em.close();
		}
	}
}

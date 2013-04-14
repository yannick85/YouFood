package com.rman.youfood.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.rman.youfood.entity.Restaurant;

public class RestaurantDao {
	private EntityManagerFactory emf;
	@Resource private EntityTransaction myTrans; 

	public RestaurantDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@SuppressWarnings("unchecked")
	public List<Restaurant> getAllRestaurant() {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT r FROM Restaurant r");
			return query.getResultList();
		} finally {
			
		}
	}
	
	public Restaurant getRestaurantById(Long restaurantId) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Restaurant.class, restaurantId);
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Restaurant> getRestaurantByMenu(Long menuId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT r FROM Restaurant r WHERE r.menu="+String.valueOf(menuId)+"");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Restaurant> getRestaurantByNotMenu(Long menuId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT r FROM Restaurant r WHERE r.menu!="+String.valueOf(menuId)+" OR r.menu IS NULL");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Restaurant> getRestaurantWhithoutMenu() {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT r FROM Restaurant r WHERE r.menu=0 OR r.menu IS NULL");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public Restaurant addRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		Restaurant result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(restaurant);
			em.getTransaction().commit();
			result = restaurant;
		} finally {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			em.close();
		}
		return result;
	}
	
	public void updateRestaurant(Restaurant restaurant) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.joinTransaction();
			em.merge(restaurant);
			em.flush();
			myTrans.commit();
		} finally {
			em.close();
		}
	}
	public void deleteRestaurant(Restaurant restaurant) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.remove(em.merge(restaurant));
			myTrans.commit();
		} finally {
			em.close();
		}
	}
}

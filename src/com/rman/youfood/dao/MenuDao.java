package com.rman.youfood.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.rman.youfood.entity.Menu;

public class MenuDao {
	private EntityManagerFactory emf;
	@Resource private EntityTransaction myTrans; 

	public MenuDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> getAllMenu() {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT m FROM Menu m");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public Menu getMenuById(Long menuId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT m FROM Menu m WHERE m.id="+String.valueOf(menuId)+"");
			return (Menu) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
		finally {
			em.close();
		}
	}
	
	public Menu addMenu(Menu menu) {
		// TODO Auto-generated method stub
		Menu result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(menu);
			em.getTransaction().commit();
			result = menu;
		} finally {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			em.close();
		}
		return result;
	}
	
	public Menu getCurrentMenuForRestaurant(Long idRestaurant){
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT m FROM Menu m , Restaurant r WHERE m.id = r.menu AND r.id="+String.valueOf(idRestaurant)+"");
			return (Menu) query.getSingleResult();
		} finally {
			em.close();
		}
	}
	public void updateMenu(Menu menu) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.joinTransaction();
			em.merge(menu);
			em.flush();
			myTrans.commit();
		} finally {
			em.close();
		}
	}
	public void deleteMenu(Menu menu) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.remove(em.merge(menu));
			myTrans.commit();
		} finally {
			em.close();
		}
	}
}

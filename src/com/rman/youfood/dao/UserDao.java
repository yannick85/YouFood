package com.rman.youfood.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.rman.youfood.entity.User;
import com.rman.youfood.util.YouFoodUtil;

public class UserDao {
	private EntityManagerFactory emf;

	public UserDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	
	public User addUser(User user) {
		User result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			result = user;
		} finally {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			em.close();
		}
		return result;
	}
	
	public User userExist(String name,String password) {
		EntityManager em = emf.createEntityManager();
		try {
			password = YouFoodUtil.getInstance().MD5(password);
			Query query = em.createQuery("SELECT u FROM User u WHERE u.name='"+name+"' AND u.password='"+password+"'");
			try{
				return (User) query.getSingleResult();
			}catch(javax.persistence.NoResultException e){
				return null;
			}			
		} finally {
			em.close();
		}
	}

}

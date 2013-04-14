package com.rman.youfood.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.rman.youfood.entity.InstructionMenu;

public class InstructionMenuDao {

	private EntityManagerFactory emf;

	public InstructionMenuDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public InstructionMenu addInstructionMenu(InstructionMenu menu) {
		InstructionMenu result = null;
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
}

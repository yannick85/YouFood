package com.rman.youfood.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.rman.youfood.entity.Instruction;

public class InstructionDao {
	private EntityManagerFactory emf;

	public InstructionDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@SuppressWarnings("unchecked")
	public List<Instruction> getAllInstruction() {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT i FROM Instruction i");
			return query.getResultList();
		} finally {
			
		}
	}
	
	public Instruction getInstructionById(Long instructionId) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Instruction.class, instructionId);
		} finally {
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Instruction> getInstructionsForZone(Long idZone){
		EntityManager em = emf.createEntityManager();
		try {
			//TODO : Include Waiter constraint / Proute !
			Query query = em.createQuery("SELECT i FROM Instruction i JOIN i.table t JOIN t.zone z WHERE i.served = 0 AND z.id = "+idZone+" ORDER BY i.creationDate ASC");
			return query.getResultList();
		} finally {
			
		}
	}
	@SuppressWarnings("unchecked")
	public List<Instruction> getInstructionsByRestaurant(Long idRestaurant){
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT i FROM Instruction i JOIN i.table t JOIN t.zone z WHERE z.restaurant = "+idRestaurant+"");
			return query.getResultList();
		} finally {
			
		}
	}
	/*public Integer getNumberInstructionsByRestaurant(Long idRestaurant){
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT COUNT(i.id) FROM Instruction i JOIN i.table t JOIN t.zone z WHERE z.restaurant = "+idRestaurant+"");
			return (Integer) query.getSingleResult();
		} finally {
			
		}
	}*/
	
	public Instruction addInstruction(Instruction instruction) {
		// TODO Auto-generated method stub
		Instruction result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(instruction);
			em.getTransaction().commit();
			result = instruction;
		} finally {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			em.close();
		}
		return result;
	}
	
	
	public boolean finishInstruction(Instruction instruction){
		boolean result = true;
		Instruction i = null;
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			i = em.find(Instruction.class, instruction.getId());
			i.setServed(true);
			t.commit();
		} catch (PersistenceException e){
			System.out.print(e.getMessage());
			result = false;
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}
		return result;
	}
	
	public boolean updateInstruction(Instruction instruction) {
		boolean result = true;
		Instruction i = null;
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			i = em.find(Instruction.class, instruction.getId());
			i.setCreationDate(instruction.getCreationDate());
			i.setMenus(instruction.getMenus());
			i.setTable(instruction.getTable());
			i.setServed(instruction.getServed());
			t.commit();
		} catch (PersistenceException e){
			result = false;
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}
		return result;
	}
}

package com.rman.youfood.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.rman.youfood.entity.Menu;
import com.rman.youfood.entity.Product;

public class ProductDao {
	private EntityManagerFactory emf;
	@Resource private EntityTransaction myTrans; 

	public ProductDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getAllProduct() {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT p FROM Product p");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public Product getProductById(Long productId) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Product.class, productId);
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getProductByType(Integer id) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT p FROM Product p WHERE p.type="+String.valueOf(id)+"");
			return  query.getResultList();
		} finally {
			em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Product> getProductByTypeAndMenu(Integer id,Long menuId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT p FROM Product p , ProductMenuAssoc a WHERE p.type="+String.valueOf(id)+" AND p.id=a.product AND a.menu="+String.valueOf(menuId)+"");
			return  query.getResultList();
		} finally {
			em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Product> getProductsForMenu(Long menuId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT p FROM Product p JOIN p.menus m WHERE m.id="+String.valueOf(menuId)+"");
			return  query.getResultList();
		} finally {
			em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Product> getProductByTypeAndNotMenu(Integer id,Long menuId) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT p FROM Product p WHERE p.type="+String.valueOf(id)+" AND p.id NOT IN (SELECT p.id FROM Product p , ProductMenuAssoc a WHERE p.type="+String.valueOf(id)+" AND p.id=a.product AND a.menu="+String.valueOf(menuId)+")");
			return  query.getResultList();
		} finally {
			em.close();
		}
	}
	public Product addProduct(Product product) {
		Product result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();
			result = product;
		} finally {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			em.close();
		}
		return result;
	}
	
	public void AddProductToMenu(Product product,Menu menu){
			EntityManager em = emf.createEntityManager();
			try {
				menu.getProducts().add(product);
				em.getTransaction().begin();
				em.merge(menu);
				em.getTransaction().commit();
			} finally {
				if(em.getTransaction().isActive()) em.getTransaction().rollback();
				em.close();
			}
	}
	
	public void RemoveProductFromMenu(Product product,Menu menu) {
		EntityManager em = emf.createEntityManager();
		try {
			menu.removeProduct(product);
			em.getTransaction().begin();
			em.merge(menu);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	public void updateProduct(Product product) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.joinTransaction();
			em.merge(product);
			em.flush();
			myTrans.commit();
		} finally {
			em.close();
		}
	}
	public void deleteProduct(Product product) {
		EntityManager em = emf.createEntityManager();
		try {
			myTrans =  em.getTransaction();
			myTrans.begin();
			em.remove(em.merge(product));
			myTrans.commit();
		} finally {
			em.close();
		}
	}
}

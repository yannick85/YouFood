package com.rman.youfood.dao;

import com.rman.youfood.util.PersistenceManager;

public final class DaoFactory {
	
	private static DaoFactory instance;
	
	private MenuDao menuDao;
	private InstructionDao instructionDao;
	private InstructionMenuDao instructionMenuDao;
	private ProductDao productDao;
	private RestaurantDao restaurantDao;
	private TabulaDao tabulaDao;
	private WaiterDao waiterDao;
	private ZoneDao zoneDao;
	private UserDao userDao;
	
	public static DaoFactory getInstance() {
		if(instance == null) {
			instance = new DaoFactory();
		}
		return instance;
	}
	
	public MenuDao getMenuDao() {
		if(menuDao == null) {
			menuDao = new MenuDao(PersistenceManager.getEntityManagerFactory());
		}
		return menuDao;
	}
	
	public InstructionDao getInstructionDao() {
		if(instructionDao == null) {
			instructionDao = new InstructionDao(PersistenceManager.getEntityManagerFactory());
		}
		return instructionDao;
	}
	
	public InstructionMenuDao getInstructionMenuDao() {
		if(instructionMenuDao == null) {
			instructionMenuDao = new InstructionMenuDao(PersistenceManager.getEntityManagerFactory());
		}
		return instructionMenuDao;
	}
	
	public ProductDao getProductDao() {
		if(productDao == null) {
			productDao = new ProductDao(PersistenceManager.getEntityManagerFactory());
		}
		return productDao;
	}
	
	public RestaurantDao getRestaurantDao() {
		if(restaurantDao == null) {
			restaurantDao = new RestaurantDao(PersistenceManager.getEntityManagerFactory());
		}
		return restaurantDao;
	}
	
	public TabulaDao getTableDao() {
		if(tabulaDao == null) {
			tabulaDao = new TabulaDao(PersistenceManager.getEntityManagerFactory());
		}
		return tabulaDao;
	}
	
	public WaiterDao getWaiterDao() {
		if(waiterDao == null) {
			waiterDao = new WaiterDao(PersistenceManager.getEntityManagerFactory());
		}
		return waiterDao;
	}
	
	public ZoneDao getZoneDao() {
		if(zoneDao == null) {
			zoneDao = new ZoneDao(PersistenceManager.getEntityManagerFactory());
		}
		return zoneDao;
	}
	
	public UserDao getUserDao() {
		if(userDao == null) {
			userDao = new UserDao(PersistenceManager.getEntityManagerFactory());
		}
		return userDao;
	}
}
package com.rman.youfood.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.MenuDao;
import com.rman.youfood.entity.Menu;

@Path("/menu")
public class MenuResource {

	@GET @Path("/current/{id}")
    public Menu getCurrentMenu(@PathParam("id") String idRestaurant) {
		MenuDao menuDao = DaoFactory.getInstance().getMenuDao();
		return menuDao.getCurrentMenuForRestaurant(Long.valueOf(idRestaurant));		
	}
}

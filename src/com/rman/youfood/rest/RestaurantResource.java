package com.rman.youfood.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.RestaurantDao;
import com.rman.youfood.entity.Restaurant;


@Path("/restaurant")
public class RestaurantResource {

	@GET @Path("/one/{id}")
    public Restaurant handleGetRequest(@PathParam("id") String id) {
		RestaurantDao campusDao = DaoFactory.getInstance().getRestaurantDao();
		return campusDao.getRestaurantById(Long.valueOf(id));		
	}
	
	
	@GET @Path("/all")	
    public List<Restaurant> handleGetAllRequest() {
		RestaurantDao campusDao = DaoFactory.getInstance().getRestaurantDao();
		return campusDao.getAllRestaurant();		
	}
}

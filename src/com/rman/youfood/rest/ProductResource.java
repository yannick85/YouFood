package com.rman.youfood.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.ProductDao;
import com.rman.youfood.entity.Product;

@Path("/product")
public class ProductResource {

	@GET @Path("/{id}")
    public List<Product> getProductsForMenu(@PathParam("id") String idMenu) {
		ProductDao productDao = DaoFactory.getInstance().getProductDao();
		return productDao.getProductsForMenu(Long.valueOf(idMenu));		
	}
}

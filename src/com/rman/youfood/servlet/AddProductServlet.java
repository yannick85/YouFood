package com.rman.youfood.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.ProductDao;
import com.rman.youfood.entity.Product;

public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddProductServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao productDao = DaoFactory.getInstance().getProductDao();
		Product product = new Product();
		Boolean update=false;
		Boolean check = true;
		List<String> errorList = new ArrayList<String>();
		if((String) request.getParameter("p_id") != null){
			product = productDao.getProductById(Long.valueOf((String) request.getParameter("p_id")));
			update=true;
		}
		if( (String) request.getParameter("p_title") != null && (String) request.getParameter("p_type") != null && request.getParameter("p_price") != null){
			String productTitle = (String) request.getParameter("p_title");
			Float price = null;
			try {
				price = Float.valueOf(((String) request.getParameter("p_price").replace(",", ".")));
			} catch (NullPointerException e) {
			
			}catch(NumberFormatException en){
				
			}
			
			Integer productType = Integer.valueOf((String) request.getParameter("p_type"));
			
			product.setTitle(productTitle);
			product.setType(productType);
			product.setPrice(price);
			if(!(productTitle.matches("^[a-zA-Z0-9 -]{4,25}$"))){
				check=false;
				errorList.add("Nom invalide. entre 4 et 25 caractères");
			}
			if(!(price != null)){
				check=false;
				errorList.add("Prix invalide.");
			}
			if(!(productType != null)){
				check=false;
				errorList.add("type invalide.");
			}
			
			if(check){
				if(update){
					productDao.updateProduct(product);
					request.setAttribute("success","update");
				}else{
					productDao.addProduct(product);
					request.setAttribute("success","new");
				}
			}
		}
		request.setAttribute("errorList",errorList);
		request.setAttribute("product",product);
		request.setAttribute("update",update);
		request.getRequestDispatcher("/WEB-INF/add_product.jsp").forward(request, response);
	}

}

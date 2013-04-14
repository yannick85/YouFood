package com.rman.youfood.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.RestaurantDao;
import com.rman.youfood.entity.Restaurant;

public class AddRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRestaurantServlet() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RestaurantDao restaurantDao = DaoFactory.getInstance().getRestaurantDao();
		Restaurant restaurant = new Restaurant();
		Boolean update=false;
		Boolean check = true;
		List<String> errorList = new ArrayList<String>();
		if((String) request.getParameter("r_id") != null){
			restaurant = restaurantDao.getRestaurantById(Long.valueOf((String) request.getParameter("r_id")));
			update=true;
		}
		if( (String) request.getParameter("r_name") != null){
			String RestaurantName = (String) request.getParameter("r_name");
			restaurant.setName(RestaurantName);
			if(!(RestaurantName.matches("^[a-zA-Z0-9 -]{4,25}$"))){
				check = false;
				errorList.add("Nom invalide. entre 4 et 25 caractères");
			}
			if(check){
				if(update){
					restaurantDao.updateRestaurant(restaurant);
					request.setAttribute("success","update");
				}else{
					restaurantDao.addRestaurant(restaurant);
					request.setAttribute("success","new");
				}
			}
		}
		request.setAttribute("errorList",errorList);
		request.setAttribute("restaurant",restaurant);
		request.setAttribute("update",update);
		request.getRequestDispatcher("/WEB-INF/add_restaurant.jsp").forward(request, response);
	}

}

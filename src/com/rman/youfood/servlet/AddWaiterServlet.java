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
import com.rman.youfood.dao.WaiterDao;
import com.rman.youfood.entity.Restaurant;
import com.rman.youfood.entity.Waiter;

public class AddWaiterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddWaiterServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WaiterDao waiterDao = DaoFactory.getInstance().getWaiterDao();
		RestaurantDao restaurantDao = DaoFactory.getInstance().getRestaurantDao();
		Restaurant restaurant = new Restaurant();
		Waiter waiter = new Waiter();
		Boolean update=false;
		Boolean check = true;
		List<String> errorList = new ArrayList<String>();
		if((String) request.getParameter("w_id") != null){
			waiter = waiterDao.getWaiterById(Long.valueOf((String) request.getParameter("w_id")));
			update=true;
		}
		if(request.getSession().getAttribute("r_id") != null || update){
			if(update){
				restaurant = waiter.getRestaurant();
			}else{
				restaurant = restaurantDao.getRestaurantById((Long) request.getSession().getAttribute("r_id"));
			}
			if( (String) request.getParameter("w_firstname") != null && (String) request.getParameter("w_lastname") != null){
				String firstname = (String) request.getParameter("w_firstname");
				String lastname = (String) request.getParameter("w_lastname");
				waiter.setFirstname(firstname);
				waiter.setLastname(lastname);
				if(!(firstname.matches("^[a-zA-Z0-9 -]{2,25}$"))){
					check=false;
					errorList.add("Nom invalide. entre 2 et 25 caractères");
				}
				if(!(lastname.matches("^[a-zA-Z0-9 -]{2,25}$"))){
					check=false;
					errorList.add("Nom invalide. entre 2 et 25 caractères");
				}
				if(check){
					if(update){
						waiterDao.updateWaiter(waiter);
						request.setAttribute("success","update");
					}else{
						waiter.setRestaurant(restaurant);
						waiter = waiterDao.addWaiter(waiter);
						request.setAttribute("success","new");
					}
				}
			}
		}
		request.setAttribute("errorList",errorList);
		request.setAttribute("restaurant",restaurant);
		request.setAttribute("waiter",waiter);
		request.setAttribute("update",update);
		request.getRequestDispatcher("/WEB-INF/add_waiter.jsp").forward(request, response);
	}

}

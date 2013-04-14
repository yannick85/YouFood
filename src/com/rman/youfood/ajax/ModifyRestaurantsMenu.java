package com.rman.youfood.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.MenuDao;
import com.rman.youfood.dao.RestaurantDao;
import com.rman.youfood.entity.Restaurant;


public class ModifyRestaurantsMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ModifyRestaurantsMenu() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( (String) request.getParameter("r_id") != null && (String) request.getParameter("m_id") != null){
			Long menuId = Long.valueOf((String) request.getParameter("m_id"));
			RestaurantDao restaurantDao = DaoFactory.getInstance().getRestaurantDao();
			MenuDao menuDao = DaoFactory.getInstance().getMenuDao(); 
			Restaurant restaurant = restaurantDao.getRestaurantById(Long.valueOf((String) request.getParameter("r_id")));
			restaurant.setMenu(menuDao.getMenuById(menuId));
			restaurantDao.updateRestaurant(restaurant);
			response.getOutputStream().println("<div id=\"response\">ok</div>");
		}
	}

}

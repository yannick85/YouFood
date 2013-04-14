package com.rman.youfood.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.RestaurantDao;
import com.rman.youfood.dao.WaiterDao;
import com.rman.youfood.dao.ZoneDao;
import com.rman.youfood.entity.Restaurant;
import com.rman.youfood.entity.Waiter;
import com.rman.youfood.entity.Zone;

public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long rId =null;
		Boolean ok =false;
		if( (String) request.getParameter("r_id") != null){
			rId = Long.valueOf((String) request.getParameter("r_id"));
			ok=true;
			request.getSession().setAttribute("r_id",rId);
		}else{
			if(request.getSession().getAttribute("r_id") != null){
				rId = (Long) request.getSession().getAttribute("r_id");
				ok=true;
			}
		}
		if(ok){
			RestaurantDao restaurantDao = DaoFactory.getInstance().getRestaurantDao();
			Restaurant restaurant = restaurantDao.getRestaurantById(rId);
			request.setAttribute("restaurant", restaurant);
			WaiterDao waiterDao = DaoFactory.getInstance().getWaiterDao();
			List<Waiter> waiters = waiterDao.getWaiterByRestaurant(restaurant.getId());
			request.setAttribute("listWaiter", waiters);
			ZoneDao zoneDao = DaoFactory.getInstance().getZoneDao();
			List<Zone> zones = zoneDao.getZoneByRestaurant(restaurant.getId());
			request.setAttribute("listZone", zones);
			request.getRequestDispatcher("/WEB-INF/restaurant.jsp").forward(request, response);
		}
	}

}

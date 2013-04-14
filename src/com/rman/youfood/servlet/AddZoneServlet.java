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
import com.rman.youfood.dao.ZoneDao;
import com.rman.youfood.entity.Restaurant;
import com.rman.youfood.entity.Zone;

public class AddZoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddZoneServlet() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RestaurantDao restaurantDao = DaoFactory.getInstance().getRestaurantDao();
		ZoneDao zoneDao = DaoFactory.getInstance().getZoneDao();
		Restaurant restaurant = new Restaurant();
		Zone zone = new Zone();
		Boolean update=false;
		Boolean check = true;
		List<String> errorList = new ArrayList<String>();
		if((String) request.getParameter("z_id") != null){
			zone = zoneDao.getZoneById(Long.valueOf((String) request.getParameter("z_id")));
			update=true;
		}
		if(request.getSession().getAttribute("r_id") != null || update){
			if(update){
				restaurant = zone.getRestaurant();
			}else{
				restaurant = restaurantDao.getRestaurantById((Long) request.getSession().getAttribute("r_id"));
			}
			if( (String) request.getParameter("z_name") != null){
				String name = (String) request.getParameter("z_name");
				zone.setName(name);
				if(!(name.matches("^[a-zA-Z0-9 -]{4,25}$"))){
					check=false;
					errorList.add("Nom invalide. entre 4 et 25 caractères");
				}
				if(check){
					if(update){
						zoneDao.updateZone(zone);
						request.setAttribute("success","update");
					}else{
						zone.setRestaurant(restaurant);
						zone = zoneDao.addZone(zone);
						request.setAttribute("success","new");
					}
				}
			}
		}
		request.setAttribute("errorList",errorList);
		request.setAttribute("restaurant",restaurant);
		request.setAttribute("zone",zone);
		request.setAttribute("update",update);
		request.getRequestDispatcher("/WEB-INF/add_zone.jsp").forward(request, response);
	}
}
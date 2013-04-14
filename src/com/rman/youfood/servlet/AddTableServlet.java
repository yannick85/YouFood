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
import com.rman.youfood.dao.TabulaDao;
import com.rman.youfood.dao.ZoneDao;
import com.rman.youfood.entity.Restaurant;
import com.rman.youfood.entity.Tabula;
import com.rman.youfood.entity.Zone;

public class AddTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTableServlet() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RestaurantDao restaurantDao = DaoFactory.getInstance().getRestaurantDao();
		ZoneDao zoneDao = DaoFactory.getInstance().getZoneDao();
		TabulaDao tableDao = DaoFactory.getInstance().getTableDao();
		Restaurant restaurant = new Restaurant();
		Zone zone = new Zone();
		Tabula table = new Tabula();
		Boolean update=false;
		Boolean check = true;
		List<String> errorList = new ArrayList<String>();
		if((String) request.getParameter("t_id") != null){
			table = tableDao.getTabulaById(Long.valueOf((String) request.getParameter("t_id")));
			update=true;
		}
		if(request.getSession().getAttribute("z_id") != null || update){
			if(update){
				zone=table.getZone();
				restaurant = zone.getRestaurant();
			}else{
				zone = zoneDao.getZoneById((Long) request.getSession().getAttribute("z_id"));
				restaurant = restaurantDao.getRestaurantById((Long) request.getSession().getAttribute("r_id"));
			}
			if( (String) request.getParameter("t_name") != null){
				String name = (String) request.getParameter("t_name");
				table.setName(name);
				if(!(name.matches("^[a-zA-Z0-9 -]{1,25}$"))){
					check=false;
					errorList.add("Nom invalide. entre 1 et 25 caractères");
				}
				if(check){
					if(update){
						tableDao.updateTabula(table);
						request.setAttribute("success","update");
					}else{
						table.setZone(zone);
						table = tableDao.addTabula(table);
						request.setAttribute("success","new");
					}
				}
			}
		}
		request.setAttribute("errorList",errorList);
		request.setAttribute("restaurant",restaurant);
		request.setAttribute("zone",zone);
		request.setAttribute("table",table);
		request.setAttribute("update",update);
		request.getRequestDispatcher("/WEB-INF/add_table.jsp").forward(request, response);
	}

}

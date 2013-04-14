package com.rman.youfood.servlet;

import java.io.IOException;
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

public class ZoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZoneServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("r_id") != null){
			Long zId =null;
			Boolean ok =false;
			if( (String) request.getParameter("z_id") != null){
				zId = Long.valueOf((String) request.getParameter("z_id"));
				ok=true;
				request.getSession().setAttribute("z_id",zId);
			}else{
				if(request.getSession().getAttribute("z_id") != null){
					zId = (Long) request.getSession().getAttribute("z_id");
					ok=true;
				}
			}
			if(ok){
				RestaurantDao restaurantDao = DaoFactory.getInstance().getRestaurantDao();
				Restaurant restaurant = restaurantDao.getRestaurantById((Long) request.getSession().getAttribute("r_id") );
				request.setAttribute("restaurant", restaurant);
				
				ZoneDao zoneDao = DaoFactory.getInstance().getZoneDao();
				Zone zone = zoneDao.getZoneById(zId);
				request.setAttribute("zone", zone);
				
				TabulaDao tableDao = DaoFactory.getInstance().getTableDao();
				List<Tabula> tables = tableDao.getTableByZone(zone.getId());
				request.setAttribute("listTable", tables);
				
				request.getRequestDispatcher("/WEB-INF/zone.jsp").forward(request, response);
			}
		}else{
			//NO RESTAURANT SELECTED
		}
	}

}

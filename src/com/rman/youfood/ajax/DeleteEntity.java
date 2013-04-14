package com.rman.youfood.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.MenuDao;
import com.rman.youfood.dao.ProductDao;
import com.rman.youfood.dao.RestaurantDao;
import com.rman.youfood.dao.TabulaDao;
import com.rman.youfood.dao.WaiterDao;
import com.rman.youfood.dao.ZoneDao;
import com.rman.youfood.entity.Menu;
import com.rman.youfood.entity.Product;
import com.rman.youfood.entity.Restaurant;
import com.rman.youfood.entity.Tabula;
import com.rman.youfood.entity.Waiter;
import com.rman.youfood.entity.Zone;



public class DeleteEntity extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteEntity() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((String) request.getParameter("ent") != null && (String) request.getParameter("id") != null){
			String entity = (String) request.getParameter("ent");
			Long id = Long.valueOf((String) request.getParameter("id"));
			switch (entity) {
			case "table":
				TabulaDao tableDao = DaoFactory.getInstance().getTableDao();
				Tabula table = tableDao.getTabulaById(id);
				tableDao.deleteTabula(table);
				response.getWriter().println("ok");
				break;
			case "product":
				ProductDao productDao = DaoFactory.getInstance().getProductDao();
				Product product = productDao.getProductById(id);
				productDao.deleteProduct(product);
				response.getWriter().println("ok");
				break;
			case "menu":
				MenuDao menuDao = DaoFactory.getInstance().getMenuDao();
				Menu menu = menuDao.getMenuById(id);
				menuDao.deleteMenu(menu);
				response.getWriter().println("ok");
				break;
			case "restaurant":
				RestaurantDao restaurantDao = DaoFactory.getInstance().getRestaurantDao();
				Restaurant restaurant = restaurantDao.getRestaurantById(id);
				restaurantDao.deleteRestaurant(restaurant);
				if((Long) request.getSession().getAttribute("r_id") == id){
					request.getSession().removeAttribute("r_id");
				}
				response.getWriter().println("ok");
				break;
			case "waiter":
				WaiterDao waiterDao = DaoFactory.getInstance().getWaiterDao();
				Waiter waiter = waiterDao.getWaiterById(id);
				waiterDao.deleteWaiter(waiter);
				response.getWriter().println("ok");
				break;
			case "zone":
				ZoneDao zoneDao = DaoFactory.getInstance().getZoneDao();
				Zone zone = zoneDao.getZoneById(id);
				zoneDao.deleteZone(zone);
				if((Long) request.getSession().getAttribute("z_id") == id){
					request.getSession().removeAttribute("z_id");
				}
				response.getWriter().println("ok");
				break;
			default:
				response.getWriter().println("error");
				break;
			}
		}
	}
}



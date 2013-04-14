package com.rman.youfood.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.MenuDao;
import com.rman.youfood.dao.ProductDao;
import com.rman.youfood.dao.RestaurantDao;
import com.rman.youfood.entity.Menu;
import com.rman.youfood.entity.Product;
import com.rman.youfood.entity.Restaurant;
import com.rman.youfood.util.YouFoodUtil;

public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MenuServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( (String) request.getParameter("m_id") != null){
			MenuDao menuDao = DaoFactory.getInstance().getMenuDao();
			Menu menu = menuDao.getMenuById(Long.valueOf((String) request.getParameter("m_id")));
			request.setAttribute("menu", menu);
			
			RestaurantDao restaurantDao = DaoFactory.getInstance().getRestaurantDao();
			List<Restaurant> restaurantsYes = restaurantDao.getRestaurantByMenu(menu.getId());
			request.setAttribute("listRestaurantChecked", restaurantsYes);
			List<Restaurant> restaurantsNo = restaurantDao.getRestaurantByNotMenu(menu.getId());
			request.setAttribute("listRestaurantNotChecked", restaurantsNo);
			
			ProductDao productDao = DaoFactory.getInstance().getProductDao();
			List<Product> startersYes = menu.getProductByType(0);
			List<Product> principalsYes = menu.getProductByType(1);
			List<Product> desertsYes = menu.getProductByType(2);
			
			request.setAttribute("listStarterChecked", startersYes);
			request.setAttribute("listPrincipalChecked", principalsYes);
			request.setAttribute("listDesertChecked", desertsYes);
			
			List<Product> startersNo = YouFoodUtil.getInstance().minusLP(productDao.getProductByType(0), startersYes);
			/*productDao.getProductByType(0); REMOVEALL fonctionne de manière étrange 
			startersNo.removeAll(startersYes);*/
			List<Product> principalsNo = YouFoodUtil.getInstance().minusLP(productDao.getProductByType(1),principalsYes);
			List<Product> desertsNo = YouFoodUtil.getInstance().minusLP(productDao.getProductByType(2),desertsYes);		
			request.setAttribute("listStarterNotChecked", startersNo);
			request.setAttribute("listPrincipalNotChecked", principalsNo);
			request.setAttribute("listDesertNotChecked", desertsNo);
			
			request.getRequestDispatcher("/WEB-INF/menu.jsp").forward(request, response);
		}
	}

}

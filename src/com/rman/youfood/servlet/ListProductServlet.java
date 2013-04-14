package com.rman.youfood.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.ProductDao;
import com.rman.youfood.entity.Product;

public class ListProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListProductServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao productDao = DaoFactory.getInstance().getProductDao();
		List<Product> starters = productDao.getProductByType(0);
		List<Product> principals = productDao.getProductByType(1);
		List<Product> deserts = productDao.getProductByType(2);
		
		request.setAttribute("listStarter", starters);
		request.setAttribute("listPrincipal", principals);
		request.setAttribute("listDesert", deserts);
		request.getRequestDispatcher("/WEB-INF/list_product.jsp").forward(request, response);
	}

}

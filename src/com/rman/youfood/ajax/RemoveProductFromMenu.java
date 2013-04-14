package com.rman.youfood.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.MenuDao;
import com.rman.youfood.dao.ProductDao;


public class RemoveProductFromMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RemoveProductFromMenu() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( (String) request.getParameter("m_id") != null && (String) request.getParameter("p_id") != null){
			Long menuId = Long.valueOf((String) request.getParameter("m_id"));
			Long productId = Long.valueOf((String) request.getParameter("p_id"));
			ProductDao productDao = DaoFactory.getInstance().getProductDao();
			MenuDao menuDao = DaoFactory.getInstance().getMenuDao();
			productDao.RemoveProductFromMenu(productDao.getProductById(productId), menuDao.getMenuById(menuId));
			response.getOutputStream().println("<div id=\"response\">ok</div>");
		}
	}

}

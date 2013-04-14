package com.rman.youfood.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.MenuDao;
import com.rman.youfood.entity.Menu;

public class ListMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListMenuServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuDao menuDao = DaoFactory.getInstance().getMenuDao();
		List<Menu> menus = menuDao.getAllMenu();
		request.setAttribute("listMenu", menus);
		request.getRequestDispatcher("/WEB-INF/list_menu.jsp").forward(request, response);
	}

}

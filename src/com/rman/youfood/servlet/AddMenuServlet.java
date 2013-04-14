package com.rman.youfood.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.MenuDao;
import com.rman.youfood.entity.Menu;

public class AddMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddMenuServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuDao menuDao = DaoFactory.getInstance().getMenuDao();
		Menu menu = new Menu();
		Boolean update=false;
		if((String) request.getParameter("m_id") != null){
			menu = menuDao.getMenuById(Long.valueOf((String) request.getParameter("m_id")));
			update=true;
		}
		if( (String) request.getParameter("m_title") != null){
			menu.setTitle((String) request.getParameter("m_title"));
			if(((String) request.getParameter("m_title")).matches("^[a-zA-Z0-9 -]{4,25}$")){
				if(update){
					menuDao.updateMenu(menu);
					request.setAttribute("success","update");
				}else{
					request.setAttribute("success","new");
					menu = menuDao.addMenu(menu);
				}
			}else{
				List<String> errorList = new ArrayList<String>();
				errorList.add("Nom invalide. entre 4 et 25 caractères");
				request.setAttribute("errorList",errorList);
			}
		}
		request.setAttribute("update",update);
		request.setAttribute("menu",menu);
		request.getRequestDispatcher("/WEB-INF/add_menu.jsp").forward(request, response);
	}

}

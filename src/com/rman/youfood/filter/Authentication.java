package com.rman.youfood.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.UserDao;
import com.rman.youfood.entity.User;

public class Authentication implements Filter {

	private FilterConfig config;
	
	@Override
	public void destroy() {
		this.config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,	FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			if(httpServletRequest.getSession().getAttribute("user") == null) {
				if( (String) request.getParameter("user-name") != null && (String) request.getParameter("user-pass") != null){
					UserDao userDao = DaoFactory.getInstance().getUserDao();
					User user = userDao.userExist((String) request.getParameter("user-name"), (String) request.getParameter("user-pass"));
					if(user != null){
						httpServletRequest.getSession().setAttribute("user", user);
					}else{
						//Wrong password/name
						request.setAttribute("action", "wrong");
						request.getRequestDispatcher("/WEB-INF/log.jsp").forward(request, response);
						return;
					}
				}else{
					request.getRequestDispatcher("/WEB-INF/log.jsp").forward(request, response);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

}

<%@page import="com.rman.youfood.dao.DaoFactory"%>
<%@page import="com.rman.youfood.dao.RestaurantDao"%>
<%@page import="com.rman.youfood.entity.Restaurant"%>
<%@page import="com.rman.youfood.dao.ZoneDao"%>
<%@page import="com.rman.youfood.entity.Zone"%>
<%@page import="com.rman.youfood.entity.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="big-wrapper">
<div id="header-wrapper">
<div id="logo">
</div>
<div id="header">
<div id="menu">
<div id="menu-restaurant" class="menu-element">
<a class="menu-biglink" href="<%= contextPath %>">Restaurants</a>
<a class="fancybox-iframe menu-littlelink" href="<%= contextPath %>/restaurant/add">Ajouter<div class="new-icon"></div></a>
</div>
<div id="menu-menu" class="menu-element">
<a class="menu-biglink" href="<%= contextPath %>/menulist">Menus</a>
<a class="fancybox-iframe menu-littlelink" href="<%= contextPath %>/menu/add">Ajouter<div class="new-icon"></div></a>
</div>
<div id="menu-product" class="menu-element">
<a class="menu-biglink" href="<%= contextPath %>/productlist">Produits</a>
<a class="fancybox-iframe menu-littlelink" href="<%= contextPath %>/product/add">Ajouter<div class="new-icon"></div></a>
</div>
<% if(request.getSession().getAttribute("r_id") != null){ %>
<div id="menu-waiter" class="menu-element">
<a class="menu-biglink" href="<%= contextPath %>/restaurant">Serveurs</a>
<a class="fancybox-iframe menu-littlelink" href="<%= contextPath %>/restaurant/waiter/add">Ajouter<div class="new-icon"></div></a>
</div>
<div id="menu-zone" class="menu-element">
<a class="menu-biglink" href="<%= contextPath %>/restaurant">Zones</a>
<a class="fancybox-iframe menu-littlelink" href="<%= contextPath %>/restaurant/zone/add">Ajouter<div class="new-icon"></div></a>
</div>
<% } %>
<% if(request.getSession().getAttribute("z_id") != null){ %>
<div id="menu-table" class="menu-element">
<a class="menu-biglink" href="<%= contextPath %>/restaurant/zone">Tables</a>
<a class="fancybox-iframe menu-littlelink" href="<%= contextPath %>/restaurant/zone/table/add">Ajouter<div class="new-icon"></div></a>
</div>
<% } %>
<div id="menu-stat" class="menu-element">
<a class="menu-biglink" href="<%= contextPath %>/stat">Statistiques</a></a>
</div>
</div>
</div>
<div class="clear"></div>
</div>
<div id="wrapper">
	<div id="page">
	<%
		if(request.getSession().getAttribute("user") != null){
			User user = (User) request.getSession().getAttribute("user");
	%>
	<div id="user-zone">
		<div id="user-name"><%= user.getName() %></div> Connecté - <a href="<%= contextPath %>/logout">Se déconnecter</a>
	</div>
	<%
		}
	%>
	<div id="breadcrumb">
	<% if(request.getSession().getAttribute("r_id") != null){
		Restaurant restaurant = restaurantDao.getRestaurantById((Long) request.getSession().getAttribute("r_id"));%>
		Restaurant sélectionné : <div id="restaurant-name"><%= restaurant.getName() %></div>
	<% } %>
	<% if(request.getSession().getAttribute("z_id") != null){
		ZoneDao zoneDao = DaoFactory.getInstance().getZoneDao();
		Zone zone = zoneDao.getZoneById((Long) request.getSession().getAttribute("z_id"));%>
		 - Zone sélectionné : <div id="zone-name"><%= zone.getName() %></div>   
	<% } %>
	</div>
	<div id="warning">
		<c:forEach items="${restaurantWithoutMenu}" var="restaurant">
			<div>Le restaurant <c:out value="${restaurant.name}"/> n'a pas de menu assignée.</div>
		</c:forEach>
	</div>
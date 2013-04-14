<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>YouFood - Bienvenue - Choisissez un restaurant</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Bienvenue sur "YouFood" - Choisissez un restaurant</h1>
	<c:choose>
			<c:when test="${empty listRestaurant}">
				<p>Aucun restaurant &agrave; afficher.</p>
			</c:when>
			<c:otherwise>
			<ul>
				<c:forEach items="${listRestaurant}" var="restaurant">
					<li id="restaurant-<c:out value="${restaurant.id}"/>">
						<a href="<%= contextPath %>/restaurant?r_id=<c:out value="${restaurant.id}"/>"><c:out value="${restaurant.name}"/></a>
						<a class="edit-icon fancybox-iframe" href="<%= contextPath %>/restaurant/add?r_id=<c:out value="${restaurant.id}"/>"></a>
						<a class="delete-icon" onclick="deleteEntity('restaurant','<c:out value="${restaurant.id}"/>','<c:out value="${restaurant.name}"/>')" ></a>	
					</li>
				</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>
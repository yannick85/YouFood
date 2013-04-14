<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>YouFood - Restaurant</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Restaurant : <c:out value="${restaurant.name}"/></h1>
	<c:choose>
		<c:when test="${empty listWaiter}">
			<p>Aucun serveur &agrave; afficher.</p>
		</c:when>
		<c:otherwise>
			<p>Serveurs :</p>
			<ul>
			<c:forEach items="${listWaiter}" var="waiter">
				<li id="waiter-<c:out value="${waiter.id}"/>">
					<c:out value="${waiter.firstname}"/> <c:out value="${waiter.lastname}"/>
					<a class="fancybox-iframe edit-icon" href="<%= contextPath %>/restaurant/waiter/add?w_id=<c:out value="${waiter.id}"/>"></a>
					<a class="delete-icon" onclick="deleteEntity('waiter','<c:out value="${waiter.id}"/>','<c:out value="${waiter.firstname}"/> <c:out value="${waiter.lastname}"/>')" ></a>	
				</li>
			</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${empty listZone}">
			<p>Aucune zone &agrave; afficher.</p>
		</c:when>
		<c:otherwise>
			<p>Zones :</p>
			<ul>
			<c:forEach items="${listZone}" var="zone">
				<li id="zone-<c:out value="${zone.id}"/>">
					<a href="<%= contextPath %>/restaurant/zone?z_id=<c:out value="${zone.id}"/>"><c:out value="${zone.name}"/></a>
					<a class="fancybox-iframe edit-icon" href="<%= contextPath %>/restaurant/zone/add?z_id=<c:out value="${zone.id}"/>"></a>
					<a class="delete-icon" onclick="deleteEntity('zone','<c:out value="${zone.id}"/>','<c:out value="${zone.name}"/>')" ></a>	
				</li>
			</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>
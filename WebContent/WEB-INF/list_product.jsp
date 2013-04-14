<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp" %>
<title>YouFood - Produits</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Produits</h1>
	<h2>Entr&eacutees :</h2>
	<c:choose>
			<c:when test="${empty listStarter}">
				<p>Aucune entr&eacutee &agrave; afficher.</p>
			</c:when>
			<c:otherwise>
			<ul>
			<c:forEach items="${listStarter}" var="starter">
				<li id="product-<c:out value="${starter.id}"/>">
					<c:out value="${starter.title}"/> (<c:out value="${starter.price}"/> &euro;)
					<a class="fancybox-iframe edit-icon" href="<%= contextPath %>/product/add?p_id=<c:out value="${starter.id}"/>"></a>
					<a class="delete-icon" onclick="deleteEntity('product','<c:out value="${starter.id}"/>','<c:out value="${starter.title}"/>')" ></a>
				</li>
			</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
	<h2>Plat principaux :</h2>
	<c:choose>
			<c:when test="${empty listPrincipal}">
				<p>Aucun plat principal &agrave; afficher.</p>
			</c:when>
			<c:otherwise>
			<ul>
			<c:forEach items="${listPrincipal}" var="principal">
				<li id="product-<c:out value="${principal.id}"/>">
					<c:out value="${principal.title}"/> (<c:out value="${principal.price}"/> &euro;)
					<a class="fancybox-iframe edit-icon" href="<%= contextPath %>/product/add?p_id=<c:out value="${principal.id}"/>"></a>
					<a class="delete-icon" onclick="deleteEntity('product','<c:out value="${principal.id}"/>','<c:out value="${principal.title}"/>')" ></a>
				</li>
			</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
	<h2>Desserts :</h2>
	<c:choose>
			<c:when test="${empty listDesert}">
				<p>Aucun d&eacutessert &agrave; afficher.</p>
			</c:when>
			<c:otherwise>
			<ul>
			<c:forEach items="${listDesert}" var="desert">
				<li id="product-<c:out value="${desert.id}"/>">
					<c:out value="${desert.title}"/> (<c:out value="${desert.price}"/> &euro;)
					<a class="edit-icon fancybox-iframe" href="<%= contextPath %>/product/add?p_id=<c:out value="${desert.id}"/>"></a>
					<a class="delete-icon" onclick="deleteEntity('product','<c:out value="${desert.id}"/>','<c:out value="${desert.title}"/>')" ></a>					
				</li>
			</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>
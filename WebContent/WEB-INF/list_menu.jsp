<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>YouFood - Menus</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Choisissez un menu</h1>
	<c:choose>
			<c:when test="${empty listMenu}">
				<p>Aucun menu &agrave; afficher.</p>
			</c:when>
			<c:otherwise>
			<ul>
			<c:forEach items="${listMenu}" var="menu">
				<li id="menu-<c:out value="${menu.id}"/>">
					<a href="<%= contextPath %>/menu?m_id=<c:out value="${menu.id}"/>"><c:out value="${menu.title}"/></a>
					<a class="edit-icon fancybox-iframe" href="<%= contextPath %>/menu/add?m_id=<c:out value="${menu.id}"/>"></a>
					<a class="delete-icon" onclick="deleteEntity('menu','<c:out value="${menu.id}"/>','<c:out value="${menu.title}"/>')" ></a>	
				</li>
			</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>
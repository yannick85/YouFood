<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>YouFood - Zone</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Restaurant : <c:out value="${restaurant.name}"/> - Zone : <c:out value="${zone.name}"/></h1>
	<c:choose>
		<c:when test="${empty listTable}">
			<p>Aucune table &agrave; afficher.</p>
		</c:when>
		<c:otherwise>
			<p>Tables :</p>
			<ul>
			<c:forEach items="${listTable}" var="table">
				<li id="table-<c:out value="${table.id}"/>">
					<c:out value="${table.name}"/>
					<a class="fancybox-iframe edit-icon" href="<%= contextPath %>/restaurant/zone/table/add?t_id=<c:out value="${table.id}"/>"></a>
					<a class="delete-icon" onclick="deleteEntity('table','<c:out value="${table.id}"/>','<c:out value="${table.name}"/>')" ></a>	
				</li>
			</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>
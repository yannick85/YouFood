<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<%= getServletContext().getContextPath() %>/box.css" />
<title>YouFood - <c:choose><c:when test="${update == true}">Modification</c:when><c:otherwise>Ajout</c:otherwise></c:choose> d'un restaurant</title>
</head>
<body>
<h1><c:choose><c:when test="${update == true}">Modification</c:when><c:otherwise>Ajout</c:otherwise></c:choose> d'un restaurant</h1>
	<c:forEach items="${errorList}" var="error">
		<div class="error"><c:out value="${error}"/></div>
	</c:forEach>
	<c:choose>
			<c:when test="${success == 'new'}">
				<p>Restaurant <c:out value="${restaurant.name}"/> cr&eacute;&eacute; avec succ&egrave;s</p>
			</c:when>
			<c:when test="${success == 'update'}">
				<p>Restaurant <c:out value="${restaurant.name}"/> modifi&eacute; avec succ&egrave;s</p>
			</c:when>
			<c:otherwise>
			<form action="" method="POST">
				<c:choose>
				<c:when test="${restaurant.id != null}">
					<input type="hidden" value="<c:out value="${restaurant.id}"/>" name="r_id"/>
				</c:when>
				</c:choose>
				<div>Nom : <br /><input type="text" name="r_name"  value='<c:out value="${restaurant.name}"/>'/></div>
				<input type="submit"/>
			</form>	
			</c:otherwise>
	</c:choose>
</body>
</html>
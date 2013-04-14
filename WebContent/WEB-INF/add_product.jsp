<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<%= getServletContext().getContextPath() %>/box.css" />
<title>YouFood - <c:choose><c:when test="${update == true}">Modification</c:when><c:otherwise>Ajout</c:otherwise></c:choose> d'un produit</title>
</head>
<body>
<h1><c:choose><c:when test="${update == true}">Modification</c:when><c:otherwise>Ajout</c:otherwise></c:choose> d'un produit</h1>
	<c:forEach items="${errorList}" var="error">
		<div class="error"><c:out value="${error}"/></div>
	</c:forEach>
	<c:choose>
			<c:when test="${success == 'new'}">
				<p>Produit <c:out value="${product.title}"/> cr&eacute;&eacute; avec succ&egrave;s</p>
			</c:when>
			<c:when test="${success == 'update'}">
				<p>Produit <c:out value="${product.title}"/> modifi&eacute; avec succ&egrave;s</p>
			</c:when>
			<c:otherwise>
			<form action="" method="POST">
				<c:choose>
				<c:when test="${product.id != null}">
					<input type="hidden" value="<c:out value="${product.id}"/>" name="p_id"/>
				</c:when>
				</c:choose>
					<div>Nom : <br /><input type="text" name="p_title"  value='<c:out value="${product.title}"/>'/></div>
					<div>Prix : <br /><input type="text" name="p_price"  value='<c:out value="${product.price}"/>'/>$</div>
				<c:choose>
					<c:when test="${product.type == 0}">
					<div>Type : <br /><select name="p_type"><option selected="selected" value="0">Entr&eacute;e</option><option value="1">Principal</option><option value="2">Dessert</option></select></div>
					</c:when>
					<c:when test="${product.type == 1}">
					<div>Type : <br /><select name="p_type"><option value="0">Entr&eacute;e</option><option selected="selected" value="1">Principal</option><option value="2">Dessert</option></select></div>
					</c:when>
					<c:when test="${product.type == 2}">
					<div>Type : <br /><select name="p_type"><option value="0">Entr&eacute;e</option><option value="1">Principal</option><option selected="selected" value="2">Dessert</option></select></div>
					</c:when>
					<c:otherwise>
					<div>Type : <br /><select name="p_type"><option value="0">Entr&eacute;e</option><option value="1">Principal</option><option value="2">Dessert</option></select></div>
					</c:otherwise>
				</c:choose>
			<input type="submit"/>
			</form>	
			</c:otherwise>
	</c:choose>
</body>
</html>
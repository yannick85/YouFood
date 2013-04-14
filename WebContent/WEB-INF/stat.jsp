<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>YouFood - Statistiques</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Statistiques</h1>
<div id="stat-left">
<h2>General</h2>
<div>
Nombre de commande : <c:out value="${numberInstruction}"/><br />
Nombre de menus commandés : <c:out value="${numberInstructionMenu}"/>
</div>
<h3>Meilleurs entr&eacute;es :</h3>
<p>
<c:forEach items="${bestStarter}" var="product" varStatus="rowCounter">
	<c:if test="${rowCounter.count < 6}">
		<c:out value="${rowCounter.count}" /> : <c:out value="${product.title}"/> - <c:out value="${popularityProduct.get(product.id)}"/> <br />
	</c:if>
</c:forEach>
</p>
<h3>Meilleurs principaux :</h3>
<p>
<c:forEach items="${bestPrincipal}" var="product" varStatus="rowCounter">
	<c:if test="${rowCounter.count < 6}">
		<c:out value="${rowCounter.count}" /> : <c:out value="${product.title}"/> - <c:out value="${popularityProduct.get(product.id)}"/> <br />
	</c:if>
</c:forEach>
</p>
<h3>Meilleurs desserts :</h3>
<p>
<c:forEach items="${bestDesert}" var="product" varStatus="rowCounter">
	<c:if test="${rowCounter.count < 6}">
		<c:out value="${rowCounter.count}" /> : <c:out value="${product.title}"/> - <c:out value="${popularityProduct.get(product.id)}"/> <br />
	</c:if>
</c:forEach>
</p>
</div>
<div id="stat-right">
<h2>Par restaurant</h2>
	<c:forEach items="${restaurants}" var="restaurant">
	<div class="stat-restaurant">
		<div id="s_r_plus_<c:out value="${restaurant.id}"/>" class="stat-restaurant-plus" onClick="jQuery(this).hide();jQuery('#content-stat-restaurant-<c:out value="${restaurant.id}"/>').show('slow');jQuery('#s_r_minus_<c:out value="${restaurant.id}"/>').show();" >[+]</div>
		<div style="display:none;" id="s_r_minus_<c:out value="${restaurant.id}"/>" class="stat-restaurant-minus" onClick="jQuery(this).hide();jQuery('#content-stat-restaurant-<c:out value="${restaurant.id}"/>').hide('slow');jQuery('#s_r_plus_<c:out value="${restaurant.id}"/>').show();" >[-]</div>
		<div class="stat-restaurant-title" id="title-stat-restaurant-<c:out value="${restaurant.id}"/>"><c:out value="${restaurant.name}"/></div>
		<div style="display:none;" class="stat-restaurant-content" id="content-stat-restaurant-<c:out value="${restaurant.id}"/>">
			Nombre de commandes : <c:out value="${numberInstructionByRestaurant.get(restaurant.id)}"/><br />
			Nombre de menus commandés : <c:out value="${numberInstructionMenuByRestaurant.get(restaurant.id)}"/>
			<div class="clear"></div>
			<div class="stat-restaurant-bestproducts">
				Meilleurs entr&eacute;es :<br />
				<c:forEach items="${bestStarterByRestaurant.get(restaurant.id)}" var="product" varStatus="rowCounter">
					<c:if test="${rowCounter.count < 4}">
						<c:out value="${rowCounter.count}" /> : <c:out value="${product.title}"/> - <c:out value="${popularityProductByRestaurant.get(restaurant.id).get(product.id)}"/> <br />
					</c:if>
				</c:forEach>
			</div>
			<div class="stat-restaurant-bestproducts">
				Meilleurs principaux :<br />
				<c:forEach items="${bestPrincipalByRestaurant.get(restaurant.id)}" var="product" varStatus="rowCounter">
					<c:if test="${rowCounter.count < 4}">
						<c:out value="${rowCounter.count}" /> : <c:out value="${product.title}"/> - <c:out value="${popularityProductByRestaurant.get(restaurant.id).get(product.id)}"/> <br />
					</c:if>
				</c:forEach>
			</div>
			<div class="stat-restaurant-bestproducts">
				Meilleurs dessert :<br />
				<c:forEach items="${bestDesertByRestaurant.get(restaurant.id)}" var="product" varStatus="rowCounter">
					<c:if test="${rowCounter.count < 4}">
						<c:out value="${rowCounter.count}" /> : <c:out value="${product.title}"/> - <c:out value="${popularityProductByRestaurant.get(restaurant.id).get(product.id)}"/> <br />
					</c:if>
				</c:forEach>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	</c:forEach>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
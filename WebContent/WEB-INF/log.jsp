<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${action == 'wrong'}">
	<p>Mauvais Nom/Mot de passe.</p>
</c:when>
</c:choose>
<p>Veuillez vous connectez pour continuer<p>
<form action="" method="POST">
<p>Nom :<br />
<input type="text" name="user-name"/></p>
<p>Mot de passe :<br />
<input type="password" name="user-pass"/></p>
<input type="submit" />
</form>
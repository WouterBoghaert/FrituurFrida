<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="nl">
	<head>
		<c:import url="/WEB-INF/JSP/head.jsp">
			<c:param name="title" value="Sauzen met ingrediënt"/>
		</c:import>
	</head>
	<body>
		<c:import url="/WEB-INF/JSP/menu.jsp"/>
		<h1>Sauzen zoeken op basis van een ingrediënt.</h1>
		<form>
			<label>Ingrediënt<span>${fout}</span>
			<input type="text" value="${param.ingrediënt}" name="ingrediënt" autofocus required/></label>
			<input type="submit" value="Zoeken"/>
		</form>
		<c:if test="${not empty sauzenByIngrediënt}">
			<ul class="zebra">
				<c:forEach var="sausnaam" items="${sauzenByIngrediënt}">
					<li>${sausnaam}</li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${not empty param and empty fout and empty sauzenByIngrediënt}">
			<div class="fout">Geen sauzen gevonden</div>
		</c:if>
	</body>
</html>
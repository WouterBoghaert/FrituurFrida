<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<!DOCTYPE html>
<html lang="nl">
<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name="title" value="Sauzen"/>
	</c:import>
</head>
<body>
	<vdab:menu/>
	<h1>Sauzen</h1>
	<h2>U heeft keuze uit de volgende sauzen:</h2>
	<form method="post">
		<ul class="zebra">
		<c:forEach var="saus" items="${sauzen}">
			<li><input type="checkbox" name="id" value="${saus.nummer}">
				${saus.naam}: 		
				<c:forEach var="ingrediënt" items="${saus.ingrediënten}" varStatus="status">
					<c:choose>
						<c:when test="${status.first}">					
							${ingrediënt}
						</c:when>
						<c:otherwise>
							, ${ingrediënt}
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<br>
				<img src="images/${saus.naam}.png" alt="${saus.naam}" title="${saus.naam}">
			</li>	
		</c:forEach>
		</ul>
		<input type="submit" value="Aangevinkte sauzen verwijderen">
	</form>
</body>
</html>
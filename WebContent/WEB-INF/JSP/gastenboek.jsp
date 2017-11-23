<%@page contentType='text/html' pageEncoding='UTF-8' session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="vdab"  uri="http://vdab.be/tags" %>
<!doctype html>
<html lang="nl">
	<head>
	 	<c:import url="/WEB-INF/JSP/head.jsp">
	 		<c:param name="title" value="Gastenboek"/>
 		</c:import>
	</head>
	<body>
		<vdab:menu/>
		<h1>Gastenboek</h1>
		<c:choose>
			<c:when test="${not empty param.showForm}">
				<form method="post" id="toevoegForm">
					<label for="naam">Naam:<span>${fouten.naam}</span></label>
					<input type="text" name="naam" required autofocus/>
					<label for="bericht">Bericht:<span>${fouten.bericht}</span></label><br>
					<textarea name="bericht" rows="8" cols="100" required></textarea><br>
					<input type="submit" value="Toevoegen" id="toevoegKnop"/>
				</form>
			</c:when>
			<c:otherwise>
				<c:url value="/gastenboek.htm" var="toevoegen">
					<c:param name="showForm" value="true"/>
				</c:url>
				<a href="<c:out value="${toevoegen}"/>">Toevoegen</a>
		</c:otherwise>
		</c:choose>
		
		<div>
			<c:forEach var="entry" items="${gastenboek}">
				<p>
				<fmt:parseDate value="${entry.datum}" pattern="yyyy-MM-dd" var="datum" type="date"/>
				<fmt:formatDate value="${datum}" dateStyle="short"/> ${entry.naam}<br>
				<strong>${entry.bericht}</strong>
				</p>
			</c:forEach>
		</div>
		<script type="text/javascript">
			document.getElementById("toevoegForm").onsubmit = function(){
				document.getElementById("toevoegKnop").disabled = true;
			};
		</script>
	</body>
</html>
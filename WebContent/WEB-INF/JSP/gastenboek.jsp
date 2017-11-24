<%@page contentType='text/html' pageEncoding='UTF-8'%>
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
		<c:if test="${ingelogd}">
			<c:url value="/gastenboek.htm" var="uitlogURL">
				<c:param name="uitloggen" value="true"/>
			</c:url>
			<form method="post" name="uitloggen" action="${uitlogURL}">
				<input type="submit" value="Uitloggen"/>
			</form>
		</c:if>
		<c:choose>
			<c:when test="${not empty param.showForm}">
				<c:url value="/gastenboek.htm" var="toevoegURL" >
					<c:param name="toevoegen" value="true"/>
				</c:url>
				<form method="post" id="toevoegForm" name="toevoegen" action="${toevoegURL}">
					<label for="naam">Naam:<span>${fouten.naam}</span></label>
					<input type="text" name="naam" required autofocus/>
					<label for="bericht">Bericht:<span>${fouten.bericht}</span></label><br>
					<textarea name="bericht" rows="8" cols="100" required></textarea><br>
					<input type="submit" value="Toevoegen" id="toevoegKnop"/>
				</form>
			</c:when>
			<c:otherwise>
				<c:url value="/gastenboek.htm" var="toevoegenLink">
					<c:param name="showForm" value="true"/>
				</c:url>
				<a href="<c:out value="${toevoegenLink}"/>">Toevoegen</a>
			</c:otherwise>
		</c:choose>
		
		<div>
			<c:url value="/gastenboek.htm" var="verwijderURL">
				<c:param name="verwijderen" value="true"/>
			</c:url>
			<form method="post" name="verwijderen" id="verwijderForm" action="${verwijderURL}">
				<c:forEach var="entry" items="${gastenboek}">
					<p>
					<c:if test="${ingelogd}"><input type="checkbox" name="id" value="${entry.id}"/></c:if>
					<fmt:parseDate value="${entry.datum}" pattern="yyyy-MM-dd" var="datum" type="date"/>
					<fmt:formatDate value="${datum}" dateStyle="short"/> ${entry.naam}<br>
					<strong>${entry.bericht}</strong>
					</p>
				</c:forEach>
				<c:if test="${ingelogd}"><input type="submit" value="Verwijderen" id="verwijderKnop"></c:if>
			</form>
		</div>
		<script type="text/javascript">
			document.getElementById("toevoegForm").addEventListener("submit", function(){
				document.getElementById("toevoegKnop").disabled = true;
			});
			document.getElementById("verwijderForm").addEventListener("submit", function(){
				document.getElementById("verwijderKnop").disabled = true;
			});
		</script>
	</body>
</html>
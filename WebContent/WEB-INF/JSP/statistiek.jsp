<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="nl">
	<head>
		<c:import url="/WEB-INF/JSP/head.jsp">
			<c:param name="title" value="Statistiek"/>
		</c:import>
		<style>
			td:last-child {
				text-align: right;
			}
		</style>
	</head>
	<body>
		<c:import url="/WEB-INF/JSP/menu.jsp"/>
		<h1>Statistiek</h1>
		<p>
			Aantal bezoeken aan de Indexpagina: ${aantalIndex}<br>
			Aantal bezoeken aan de Sauspagina: ${aantalSauzen}<br>
			Aantal bezoeken aan de Ingredi&euml;ntenpagina: ${aantalIngredienten}<br>
			Aantal bezoeken aan de MeisjesJongenspagina: ${aantalMeisjesjongens}<br>
			Aantal bezoeken aan de ZoekDeFrietpagina: ${aantalZoekdefriet}<br>
			Aantal bezoeken aan de SausRadenpagina: ${aantalSausraden}<br>
			Aantal bezoeken aan de Statistiekpagina: ${aantalStatistiek}<br>
		</p>
		<p>
			<c:if test="${not empty statistiek}">
				<table>
					<thead>
						<tr>
							<th>URL</th><th>aantal requests</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="entry" items="${statistiek}">
							<tr>
								<td>${entry.key}</td>
								<td>${entry.value}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</p>
	</body>
</html>
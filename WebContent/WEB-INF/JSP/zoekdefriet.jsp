<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="nl">
	<head>
		<c:import url="/WEB-INF/JSP/head.jsp">
			<c:param name="title" value="Zoek de friet!"/>
		</c:import>
	</head>
	<body>
		<c:import url="/WEB-INF/JSP/menu.jsp"/>
		<h1>Zoek de friet!</h1>
		<form method="post">
			<c:forEach var="deur" items="${zoekDeFrietSpel.deuren}" varStatus="status">
				<button type="submit" name="volgnummer" value="${status.index}">
					<c:if test="${not deur.open}">
						<img src="<c:url value="/images/deurtoe.png"/>" alt="deur toe">
					</c:if>
					<c:if test="${deur.open}">
						<c:if test="${not deur.metFriet}">
							<img src="<c:url value="/images/deuropen.png"/>" alt="deur open">
						</c:if>
						<c:if test="${deur.metFriet}">
							<img src="<c:url value="/images/gevonden.png"/>" alt="gevonden">
						</c:if>
					</c:if>
				</button>
			</c:forEach>
			<p>
				<input type="submit" name="nieuwSpel" value="Nieuw spel"/>
			</p>
		</form>	
	</body>
</html>
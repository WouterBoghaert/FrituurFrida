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
			<c:forEach var="nummer" begin="1" end="7">
				<button type="submit" name="volgnummer" value="${nummer}">
					<img src="<c:url value="/images/${geklikt.contains(nummer) ? zoekDeFrietRepository.findByVolgnummer(nummer) : 'deurtoe' }.png"/>" alt="${geklikt.contains(nummer) ? zoekDeFrietRepository.findByVolgnummer(nummer) : 'deur toe' }">
				</button>
			</c:forEach>
			<br>
			<input type="submit" name="nieuwSpel" value="Nieuw spel"/>
		</form>	
	</body>
</html>
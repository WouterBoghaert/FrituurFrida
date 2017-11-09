<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="nl">
	<head>
		<c:import url="/WEB-INF/JSP/head.jsp">
			<c:param name="title" value="Raad de saus!"/>
		</c:import>
	</head>
	<body>
		<c:import url="/WEB-INF/JSP/menu.jsp"/>
		<h1>Raad de saus!</h1>
		<form method="post">
			Te raden saus: ${sausRadenSpel.output}
			<br>
			<label for="letter">Letter</label>
			<input type="text" name="letter" maxlength="1" pattern="[a-z]"autofocus required id="letter"/>
			<p>
				<input type="submit" name="raden" value="Raden" id="raden"><br>
				<input type="submit" name="nieuwSpel" value="Nieuw spel"/>
			</p>
			<p>
				<img src="<c:url value="/images/${sausRadenSpel.aantalVerkeerd}.png"/>" alt="${sausRadenSpel.aantalVerkeerd}" title="${sausRadenSpel.aantalVerkeerd}">
			</p>
			<p>
				<c:if test="${sausRadenSpel.aantalVerkeerd == 10}">
					U bent verloren, de saus was ${sausRadenSpel.sausNaam}
					<script>
						document.getElementById("raden").disabled = true;
						document.getElementById("letter").disabled = true;
					</script>
				</c:if>
				<c:if test="${sausRadenSpel.output.equals(sausRadenSpel.sausNaam) && sausRadenSpel.aantalVerkeerd < 10}">
					U bent gewonnen, de saus was ${sausRadenSpel.sausNaam}
					<script>
						document.getElementById("raden").disabled = true;
						document.getElementById("letter").disabled = true;
					</script>
				</c:if>
			</p>
		</form>	
	</body>
</html>
<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<!doctype html>
<html lang="nl">
	<head>
		<c:import url="/WEB-INF/JSP/head.jsp">
			<c:param name="title" value="Inloggen"/>
		</c:import>
	</head>
	<body>
		<vdab:menu/>
		<h1>Inloggen</h1>
		<p>Gelieve uw wachtwoord in te geven om in te loggen,
		 om toegang te krijgen tot de beheersfuncties van het gastenboek</p>
		 <form method="post" id="inlogForm">
		 	<label for="wachtwoord">Wachtwoord:<span>${fouten.wachtwoord}</span></label><br>
		 	<input type="password" name="wachtwoord" required autofocus/><br>
		 	<input type="submit" value="Inloggen" id="inlogKnop"/>
		 </form>
<!-- 		 <script> -->
// 		 	document.getElementById("inlogForm").addEventListener("click", function(){
// 		 		document.getElementById("inlogKnop").disabled = true;
// 		 	});
<!-- 		 </script> -->
	</body>
</html>
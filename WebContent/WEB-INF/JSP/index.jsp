<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="nl">
<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name="title" value="Frituur Frida"/>
	</c:import>
</head>
<body>
	<c:import url="/WEB-INF/JSP/menu.jsp"/>
	<h1>Frituur Frida</h1>
	<h2>Vandaag zijn we ${openGesloten}</h2>
	<img src="images/${openGesloten}.png" alt="${openGesloten}" title="${openGesloten}">
	<h2>Adres</h2>
	<p>${adres.straat} ${adres.huisNr} <br>
		${adres.gemeente.postCode} ${adres.gemeente.naam} 
	</p>
	<div>Telefoonnummer helpdesk: 
	<a href="tel:+${telefoonHelpdesk.replace('/','').replace('.','')}">${telefoonHelpdesk}</a></div>
</body>
</html>
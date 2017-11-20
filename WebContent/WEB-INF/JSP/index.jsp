<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resourceBundles.teksten"/>
<!DOCTYPE html>
<html lang="nl">
<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name="title" value="Frituur Frida"/>
	</c:import>
</head>
<body>
	<c:import url="/WEB-INF/JSP/menu.jsp"/>
	<h1><fmt:message key="titel"/></h1>
<%-- 	<c:choose> --%>
<%-- 		<c:when test='${openGesloten.equals("open")}'> --%>
<%-- 			<c:set var="openGeslotenTaal" value="open"/> --%>
<%-- 			<c:set var="openGeslotenFiguurTaal" value="openFiguur"/> --%>
<%-- 		</c:when> --%>
<%-- 		<c:otherwise> --%>
<%-- 			<c:set var="openGeslotenTaal" value="gesloten"/> --%>
<%-- 			<c:set var="openGeslotenFiguurTaal" value="geslotenFiguur"/> --%>
<%-- 		</c:otherwise> --%>
<%-- 	</c:choose> --%>
	<h2><fmt:message key="ondertitel">
		<fmt:param value="${openGesloten}"/>
	</fmt:message></h2>
	<img src="images/${openGeslotenFiguur}.png" alt="${openGesloten}" title="${openGesloten}">
	<h2><fmt:message key="adres"/></h2>
	<p>${adres.straat} ${adres.huisNr} <br>
		${adres.gemeente.postCode} ${adres.gemeente.naam} 
	</p>
	<div><fmt:message key="telefoon"/> 
	<a href="tel:+${telefoonHelpdesk.replace('/','').replace('.','')}">${telefoonHelpdesk}</a></div>
</body>
</html>
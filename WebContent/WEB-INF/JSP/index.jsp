<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<fmt:setBundle basename="resourceBundles.teksten"/>
<!DOCTYPE html>
<html lang="nl">
<head>
	<fmt:message key="titel" var="titel"/>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name="title" value="${titel}"/>
	</c:import>
</head>
<body>
	<vdab:menu/>
	<h1>${titel}</h1>
	<c:choose>
		<c:when test='${openGesloten.equals("open")}'>
			<c:set var="openGeslotenTaal" value="open"/>
			<c:set var="openGeslotenFiguurTaal" value="openFiguur"/>
		</c:when>
		<c:otherwise>
			<c:set var="openGeslotenTaal" value="gesloten"/>
			<c:set var="openGeslotenFiguurTaal" value="geslotenFiguur"/>
		</c:otherwise>
	</c:choose>
	<h2><fmt:message key="ondertitel${openGesloten}"/></h2>
	<fmt:message key="${openGeslotenFiguurTaal}" var="openGeslotenFiguur"/>
	<img src="images/${openGeslotenFiguur}.png" alt="<fmt:message key="${openGeslotenTaal}"/>" title="<fmt:message key="${openGeslotenTaal}"/>">
	<h2><fmt:message key="adres"/></h2>
	<p>${adres.straat} ${adres.huisNr} <br>
		${adres.gemeente.postCode} ${adres.gemeente.naam} 
	</p>
	<div><fmt:message key="telefoon"/> 
	<a href="tel:+${telefoonHelpdesk.replace('/','').replace('.','')}">${telefoonHelpdesk}</a></div>
</body>
</html>
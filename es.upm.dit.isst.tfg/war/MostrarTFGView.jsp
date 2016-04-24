<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
	<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
	<%
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sistema de gestión de TFGs, bienvenido<c:out value="${user}"/></title>
      <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
<h1 style="margin-left: 20px; margin-top:20px;">Sistema de gestión de TFGs</h1>
<p style="margin-left: 20px; margin-top:20px;"><c:if test="${not empty user}">
	<c:out value="${user}"/>
</c:if><p>
<p style="margin-left: 20px; margin-top:20px;">Puede pulsar el siguiente enlace para:</p>
<a href="<c:url value="${url}"/>"><p style="margin-left: 20px; margin-top:20px;"><c:out value="${urlLinktext}"/></p></a></p>

<c:if test="${not empty user and tfg.autor == user and not empty tfg}">
<p style="margin-left: 20px; margin-top:20px;">Alumno: hay un tfg del usuario <c:out value="${user}"/></p>
	<table style="margin-left: 20px; margin-top:20px;">
		<tr>
			<td><c:out value="${tfg.autor}"/></td>
			<td><c:out value="${tfg.titulo}"/></td>
			<td><c:out value="${tfg.resumen}"/></td>
			<td><c:out value="${tfg.tutor}"/></td>
			<td><c:out value="${tfg.secretario}"/></td>
			<td><c:out value="${tfg.estado}"/></td>
			<td><c:out value="${tfg.memoria}"/></td>
		</tr>
	</table>
</c:if>
<c:if test="${not empty user and not empty tfg and tfg.estado lt 2}">
<p style="margin-left: 20px; margin-top:20px;">Sin memoria</p>
</c:if>
<c:if test="${not empty user and not empty tfg and tfg.estado == 2}">
<p style="margin-left: 20px; margin-top:20px;">Formulario subida de memoria. <c:out value="${tfg.fichero}"/></p>
	<form action="<%=blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data" style="margin-left: 20px; margin-top:20px;">
		<input id="autor" name="autor" type="hidden" value="${tfg.autor}"/>
		<input type="file" name="file"/>
		<input type="submit" value="Subir memoria"/>
	</form>
</c:if>

<c:if test="${not empty user and not empty tfg and tfg.estado == 1}">
	<form action="/borrarSolicitud" method="post" style="margin-left: 20px; margin-top:20px;">
		<input id="autor" name="autor" type="hidden" value="${tfg.autor}"/>
		<input type="submit" value="Retirar solicitud de TFG"/>
	</form>
</c:if>

<c:if test="${not empty user and empty tfg and empty tfgs}">
<p style="margin-left: 20px; margin-top:20px;">Alumno: esta es la solicitud de TFG</p>
	<form action="/nuevoTFG" method="post" acceptcharset="utf8" style="margin-left: 20px; margin-top:20px;">
		<input type="text" name="titulo" id="titulo" maxLength="255"
		size="20" required placeholder="Titulo"/>
		<input type="text" name="resumen" id="resumen" maxLength="255"
		size="20" required placeholder="resumen"/>
		<input type="text" name="tutor" id="tutor" maxLength="255"
		size="20" required placeholder="Tutor"/>
		<input type="submit" value="Solicitud"/>
	</form>
</c:if>
<c:if test="${not empty user and not empty tfg and tfg.estado gt 2}">
	<form action="/mostrarmemoria" method="get" style="margin-left: 20px; margin-top:20px;">
		<input id="autor" name="autor" type="hidden" value="{tfg.autor}"/>
		<input type="submit" value="Mostrar memoria"/>
	</form>
</c:if>

<!-- <c:if test="${not empty user and (fn:length(tfgs) != 0)}">
Profesor: hay un total de <c:out value="${fn:length(tfgs)}"/>
-->

</c:if>
<c:if test="${not empty user and not empty tfgs}">
		<p style="margin-left: 20px; margin-top:20px;">Profesor; hay un total de  <c:out value ="${fn:length(tfgs)}"/><p> 
		<table style="margin-left: 20px; margin-top:20px;">
		<tr>
			<th>autor</th>
			<th>titulo</th>
			<th>resumen</th>
			<th>tutor</th>
			<th>secretario</th>
			<th>estado/accion</th>
			<th>memoria</th>
		</tr>
				<c:forEach items="${tfgs}" var="tfgi">
					<tr>
						<td><c:out value="${tfgi.autor}"/></td>
						<td><c:out value="${tfgi.titulo}"/></td>
						<td><c:out value="${tfgi.resumen}"/></td>
						<td><c:out value="${tfgi.tutor}"/></td>
						<td><c:out value="${tfgi.secretario}"/></td>
						<td><c:out value="${tfgi.estado}"/></td>
						<td><c:out value="${tfgi.memoria}"/></td>
						<c:choose>
						<c:when test="${tfgi.estado == 1}">
						<form action ="/aceptarTutor" methof="get" acceptcharet="utf-8" style="margin-left: 20px; margin-top:20px;">
						<input type="hidden" name="autor" value="${tfgi.autor}"/>
						<td><c:out value="${tfgi.secretario}"/></td>
						<td><input type="submit" value="Aceptar tutor"/></td>
						</form>
						</c:when>
						<c:when test="${tfgi.estado == 3}">
						<form action="/aceptarSecretario" method="post" acceptcharset="utf-8" style="margin-left: 20px; margin-top:20px;">
							<input type="hidden" name="autor" value="${tfgi.autor}"/>
							<td><input type="text" name="secretario" id="secretario" maxLength="255"
							required size="20" placeholder="Sugerir secretario"/></td>
							<td><input type="submit" value="Sugerir secretario"/></td>
						</form>
						</c:when>
						</c:choose>
					</tr> 
					</c:forEach>
		</table>
</c:if>

</body>
</html>
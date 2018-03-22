<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<title>Affichage de tous les adhérents</title>
</head>
<body>
	<div class="container">
		<a class="btn btn-info" href="index.htm" role="button">Retour Accueil</a>
		<p align="center">
			 Listing&nbsp;des Adhérents
		</p>

		<table class="table table-hover" BORDER="1">
			<tr>
				<th>Numero</th>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Ville</th>
				<th>Actions</th>
			</tr>

			<c:forEach items="${mesAdherents}" var="item">
				<tr>
					<td>${item.idAdherent}</td>
					<td>${item.nomAdherent}</td>
					<td>${item.prenomAdherent}</td>
					<td>${item.villeAdherent}</td>
					<td>
                        <form action="supprimerAdherent.htm" method="post">
                            <a class="btn btn-info" href="modifierAdherent.htm?id=${item.idAdherent}" role="button"><span class="glyphicon glyphicon-pencil"></span>Modifier</a>
                            <input type="hidden" name="id" value="${item.idAdherent}"/>
                            <button type="submit" class="btn btn-danger" role="button"><span class="glyphicon glyphicon-remove-circle"></span>Supprimer</button>
                        </form>
                    </td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
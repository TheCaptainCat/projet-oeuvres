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
        Listing des Oeuvres
    </p>

    <table class="table table-hover" BORDER="1">
        <tr>
            <th>Titre</th>
            <th>Prix</th>
            <th>Propriétaire</th>
            <th>Etat</th>
            <th>Actions</th>
        </tr>

        <c:forEach items="${oeuvres}" var="item">
            <tr>
                <td>${item.titreOeuvrevente}</td>
                <td>${item.prixOeuvrevente}</td>
                <td>${item.proprietaire.prenomProprietaire} ${item.proprietaire.nomProprietaire}</td>
                <td>${item.etatOeuvrevente == 'L' ? 'Disponible' : 'Réservée'}</td>
                <td>
                    <a class="btn btn-info" href="#" role="button"><span class="glyphicon glyphicon-pencil"></span>Modifier</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
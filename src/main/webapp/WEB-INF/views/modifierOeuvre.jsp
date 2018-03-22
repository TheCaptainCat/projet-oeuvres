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

    <form action="updateOeuvre.htm" method="post">
        <input type="hidden" name="id" value="${oeuvre.idOeuvrevente}">

        <div class="form-group">
            <label for="titre">Titre</label>
            <input type="text" class="form-control" name="titre" id="titre" value="${oeuvre.titreOeuvrevente}"/>
        </div>
        <div class="form-group">
            <label for="prix">Prix</label>
            <input type="text" class="form-control" name="prix" id="prix" value="${oeuvre.prixOeuvrevente}"/>
        </div>
        <div class="form-group">
            <label for="proprietaire">Propriétaire de l'oeuvre</label>
            <select class="form-control" name="proprietaire" id="proprietaire">
                <c:forEach items="${proprietaires}" var="item">
                    <option value="${item.idProprietaire}"
                        ${item.idProprietaire == oeuvre.proprietaire.idProprietaire ? 'selected' : ''}
                    >${item.prenomProprietaire} ${item.nomProprietaire}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Etat</label>
            <c:choose>
                <c:when test="${oeuvre.etatOeuvrevente eq 'L'}">
                    <p>L'oeuvre est encore disponible à la vente !</p>
                    <%--<button type="button" class="btn btn-primary">Effectuer une réservation</button>--%>
                </c:when>
                <c:otherwise>
                    <p>L'oeuvre a déjà été réservée.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <button type="submit" class="btn btn-primary">Valider</button>
    </form>
</div>
</body>
</html>
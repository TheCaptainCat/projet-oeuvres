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

    <form action="updateAdherent.htm" method="post">
        <input type="hidden" name="id" value="${adherent.idAdherent}">

        <div class="form-group">
            <label for="nom">Nom</label>
            <input type="text" class="form-control" name="nom" id="nom" value="${adherent.nomAdherent}"/>
        </div>
        <div class="form-group">
            <label for="prenom">Prénom</label>
            <input type="text" class="form-control" name="prenom" id="prenom" value="${adherent.prenomAdherent}"/>
        </div>
        <div class="form-group">
            <label for="ville">Ville</label>
            <input type="text" class="form-control" name="ville" id="ville" value="${adherent.villeAdherent}"/>
        </div>

        <button type="submit" class="btn btn-primary">Valider</button>
    </form>
</div>
</body>
</html>
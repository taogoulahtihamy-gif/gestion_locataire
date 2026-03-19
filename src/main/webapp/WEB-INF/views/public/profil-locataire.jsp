<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.location.entity.Locataire" %>
<%
    Locataire locataire = (Locataire) session.getAttribute("locataireSession");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mon profil</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{background:#f4f7fb;font-family:Arial,sans-serif;}
        .wrap{max-width:760px;margin:40px auto;background:#fff;padding:28px;border-radius:18px;box-shadow:0 10px 30px rgba(0,0,0,.08);}
    </style>
</head>
<body>
<div class="wrap">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Mon profil</h2>
        <div class="d-flex gap-2">
            <a class="btn btn-outline-primary" href="<%= request.getContextPath() %>/offres">Voir les offres</a>
            <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/mes-demandes">Mes demandes</a>
            <a class="btn btn-outline-danger" href="<%= request.getContextPath() %>/logout">Déconnexion</a>
        </div>
    </div>

    <% if(request.getAttribute("success") != null){ %>
        <div class="alert alert-success"><%= request.getAttribute("success") %></div>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/profil-locataire">
        <div class="row g-3">
            <div class="col-md-6">
                <label>Nom</label>
                <input type="text" name="nom" class="form-control" value="<%= locataire != null ? locataire.getNom() : "" %>" required>
            </div>
            <div class="col-md-6">
                <label>Email</label>
                <input type="email" class="form-control" value="<%= locataire != null ? locataire.getEmail() : "" %>" disabled>
            </div>
            <div class="col-md-6">
                <label>Téléphone</label>
                <input type="text" name="telephone" class="form-control" value="<%= locataire != null ? locataire.getTelephone() : "" %>">
            </div>
            <div class="col-md-6">
                <label>Adresse</label>
                <input type="text" name="adresse" class="form-control" value="<%= locataire != null ? locataire.getAdresse() : "" %>">
            </div>
            <div class="col-12">
                <label>Nouveau mot de passe (optionnel)</label>
                <input type="password" name="motDePasse" class="form-control">
            </div>
        </div>
        <button class="btn btn-primary mt-3">Mettre à jour</button>
    </form>
</div>
</body>
</html>
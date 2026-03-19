<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List,com.location.entity.UniteLocation,com.location.entity.Locataire" %>
<%
    List<UniteLocation> offres = (List<UniteLocation>) request.getAttribute("offres");
    Locataire locataire = (Locataire) session.getAttribute("locataireSession");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Offres disponibles</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{background:#f4f7fb;font-family:Arial,sans-serif;}
        .wrap{max-width:1100px;margin:40px auto;padding:0 16px;}
        .card-offre{background:#fff;border-radius:18px;padding:20px;box-shadow:0 10px 30px rgba(0,0,0,.08);margin-bottom:18px;}
    </style>
</head>
<body>
<div class="wrap">
    <div class="d-flex justify-content-between align-items-center mb-4 flex-wrap gap-2">
        <h2>Offres disponibles</h2>
        <div class="d-flex gap-2">
            <% if(locataire == null){ %>
                <a class="btn btn-primary" href="<%= request.getContextPath() %>/login-locataire">Connexion locataire</a>
                <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/register">Inscription</a>
            <% } else { %>
                <a class="btn btn-outline-primary" href="<%= request.getContextPath() %>/profil-locataire">Mon profil</a>
                <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/mes-demandes">Mes demandes</a>
            <% } %>
        </div>
    </div>

    <% if(offres != null){ for(UniteLocation u : offres){ %>
        <div class="card-offre">
            <div class="row g-3">
                <div class="col-md-8">
                    <h4>Unité <%= u.getReference() != null ? u.getReference() : "#" + u.getId() %></h4>
                    <p class="mb-1"><strong>Immeuble :</strong> <%= u.getImmeuble() != null ? u.getImmeuble().getNom() : "-" %></p>
                    <p class="mb-1"><strong>Pièces :</strong> <%= u.getNombrePieces() %></p>
                    <p class="mb-1"><strong>Superficie :</strong> <%= u.getSuperficie() %> m²</p>
                    <p class="mb-1"><strong>Loyer :</strong> <%= u.getLoyer() != null ? String.format("%,.0f", u.getLoyer()) : "0" %> FCFA</p>
                    <p class="mb-0"><strong>Équipements :</strong> <%= u.getEquipements() != null ? u.getEquipements() : "-" %></p>
                </div>
                <div class="col-md-4">
                    <% if(locataire != null){ %>
                        <form method="post" action="<%= request.getContextPath() %>/demande-location">
                            <input type="hidden" name="uniteId" value="<%= u.getId() %>">
                            <label>Commentaire</label>
                            <textarea name="commentaire" class="form-control mb-2"></textarea>
                            <button class="btn btn-success w-100">Faire une demande</button>
                        </form>
                    <% } else { %>
                        <a class="btn btn-primary w-100" href="<%= request.getContextPath() %>/login-locataire">Se connecter pour demander</a>
                    <% } %>
                </div>
            </div>
        </div>
    <% }} %>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List,com.location.entity.DemandeLocation" %>
<%
    List<DemandeLocation> demandes = (List<DemandeLocation>) request.getAttribute("demandes");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mes demandes</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{background:#f4f7fb;font-family:Arial,sans-serif;}
        .wrap{max-width:1000px;margin:40px auto;background:#fff;padding:28px;border-radius:18px;box-shadow:0 10px 30px rgba(0,0,0,.08);}
    </style>
</head>
<body>
<div class="wrap">
    <div class="d-flex justify-content-between align-items-center mb-3 flex-wrap gap-2">
        <h2>Mes demandes de location</h2>
        <div class="d-flex gap-2">
            <a class="btn btn-outline-primary" href="<%= request.getContextPath() %>/offres">Voir les offres</a>
            <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/profil-locataire">Mon profil</a>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Unité</th>
                <th>Date</th>
                <th>Statut</th>
                <th>Commentaire</th>
            </tr>
            </thead>
            <tbody>
            <% if(demandes != null && !demandes.isEmpty()){ for(DemandeLocation d : demandes){ %>
                <tr>
                    <td><%= d.getId() %></td>
                    <td><%= d.getUniteLocation() != null ? (d.getUniteLocation().getReference() != null ? d.getUniteLocation().getReference() : d.getUniteLocation().getId()) : "-" %></td>
                    <td><%= d.getDateDemande() %></td>
                    <td><%= d.getStatut() %></td>
                    <td><%= d.getCommentaire() != null ? d.getCommentaire() : "-" %></td>
                </tr>
            <% }} else { %>
                <tr><td colspan="5" class="text-center">Aucune demande pour le moment.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
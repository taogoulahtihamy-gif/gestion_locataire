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
        body{
            background:#f4f7fb;
            font-family:Arial,sans-serif;
        }

        .page{
            max-width:1100px;
            margin:40px auto;
            padding:0 16px;
        }

        .card{
            border-radius:20px;
            box-shadow:0 10px 25px rgba(0,0,0,.08);
        }

        .badge{
            font-size:12px;
            padding:6px 10px;
            border-radius:10px;
        }

        @media(max-width:768px){
            .page{
                margin:20px auto;
            }
        }
    </style>
</head>
<body>

<div class="page">

    <div class="d-flex justify-content-between align-items-center mb-3 flex-wrap gap-2">
        <h2>Mes demandes</h2>

        <div class="d-flex gap-2">
            <a class="btn btn-outline-primary" href="<%= request.getContextPath() %>/offres">Offres</a>
            <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/profil-locataire">Profil</a>
        </div>
    </div>

    <div class="card p-3">
        <div class="table-responsive">
            <table class="table align-middle">
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
                <% if(demandes != null && !demandes.isEmpty()){
                    for(DemandeLocation d : demandes){ %>

                    <tr>
                        <td>#<%= d.getId() %></td>

                        <td>
                            <%= d.getUniteLocation() != null ? d.getUniteLocation().getReference() : "-" %>
                        </td>

                        <td><%= d.getDateDemande() %></td>

                        <td>
                            <% if("EN_ATTENTE".equals(d.getStatut())){ %>
                                <span class="badge bg-warning text-dark">En attente</span>
                            <% } else if("ACCEPTEE".equals(d.getStatut())){ %>
                                <span class="badge bg-success">Acceptée</span>
                            <% } else if("CONTRAT_CREE".equals(d.getStatut())){ %>
                                <span class="badge bg-primary">Contrat créé</span>
                            <% } else { %>
                                <span class="badge bg-danger">Refusée</span>
                            <% } %>
                        </td>

                        <td><%= d.getCommentaire() != null ? d.getCommentaire() : "-" %></td>
                    </tr>

                <% }} else { %>

                    <tr>
                        <td colspan="5" class="text-center">Aucune demande pour le moment</td>
                    </tr>

                <% } %>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>
</html>
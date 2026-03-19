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
        body{
            background:#f4f7fb;
            font-family:Arial,sans-serif;
        }

        .page{
            max-width:1200px;
            margin:40px auto;
            padding:0 16px;
        }

        .top-bar{
            display:flex;
            justify-content:space-between;
            align-items:center;
            flex-wrap:wrap;
            gap:12px;
            margin-bottom:30px;
        }

        .card-offre{
            background:#fff;
            border-radius:20px;
            padding:20px;
            box-shadow:0 10px 25px rgba(0,0,0,.08);
            transition:.2s;
        }

        .card-offre:hover{
            transform:translateY(-3px);
        }

        .price{
            font-size:20px;
            font-weight:bold;
            color:#2563eb;
        }

        textarea{
            border-radius:10px !important;
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

    <div class="top-bar">
        <h2>Offres disponibles</h2>

        <div class="d-flex gap-2 flex-wrap">
            <% if(locataire == null){ %>
                <a class="btn btn-primary" href="<%= request.getContextPath() %>/login-locataire">Connexion</a>
                <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/register">Inscription</a>
            <% } else { %>
                <a class="btn btn-outline-primary" href="<%= request.getContextPath() %>/profil-locataire">Profil</a>
                <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/mes-demandes">Mes demandes</a>
            <% } %>
        </div>
    </div>

    <div class="row g-4">
        <% if(offres != null){ for(UniteLocation u : offres){ %>

        <div class="col-md-6 col-lg-4">
            <div class="card-offre">

                <h5>Unité <%= u.getReference() != null ? u.getReference() : "#" + u.getId() %></h5>

                <p class="mb-1"><strong>Immeuble :</strong> <%= u.getImmeuble() != null ? u.getImmeuble().getNom() : "-" %></p>
                <p class="mb-1"><strong>Pièces :</strong> <%= u.getNombrePieces() %></p>
                <p class="mb-1"><strong>Surface :</strong> <%= u.getSuperficie() %> m²</p>

                <p class="price mb-2">
                    <%= u.getLoyer() != null ? String.format("%,.0f", u.getLoyer()) : "0" %> FCFA
                </p>

                <% if(locataire != null){ %>
                    <form method="post" action="<%= request.getContextPath() %>/demande-location">
                        <input type="hidden" name="uniteId" value="<%= u.getId() %>">
                        <textarea name="commentaire" class="form-control mb-2" placeholder="Message (optionnel)"></textarea>
                        <button class="btn btn-success w-100">Faire une demande</button>
                    </form>
                <% } else { %>
                    <a class="btn btn-primary w-100" href="<%= request.getContextPath() %>/login-locataire">
                        Se connecter pour demander
                    </a>
                <% } %>

            </div>
        </div>

        <% }} %>
    </div>

</div>

</body>
</html>
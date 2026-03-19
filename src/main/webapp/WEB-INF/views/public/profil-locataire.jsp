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
        body{
            background:#f4f7fb;
            font-family:Arial,sans-serif;
            margin:0;
        }

        .page-wrap{
            max-width:1100px;
            margin:40px auto;
            padding:0 16px;
        }

        .profile-card{
            background:#fff;
            border-radius:22px;
            padding:32px;
            box-shadow:0 10px 30px rgba(0,0,0,.08);
        }

        .top-bar{
            display:flex;
            justify-content:space-between;
            align-items:center;
            gap:16px;
            flex-wrap:wrap;
            margin-bottom:24px;
        }

        .top-bar h2{
            margin:0;
            font-size:42px;
            font-weight:300;
            color:#111827;
        }

        .top-actions{
            display:flex;
            gap:10px;
            flex-wrap:wrap;
        }

        .form-label{
            font-weight:600;
            color:#374151;
            margin-bottom:8px;
        }

        .form-control{
            border-radius:12px;
            min-height:48px;
            border:1px solid #d1d5db;
        }

        .form-control:focus{
            border-color:#2563eb;
            box-shadow:0 0 0 0.2rem rgba(37,99,235,.15);
        }

        .btn-main{
            background:linear-gradient(135deg,#2563eb,#1d4ed8);
            color:white;
            border:none;
            border-radius:12px;
            padding:12px 22px;
            font-weight:600;
        }

        .btn-main:hover{
            color:white;
            opacity:.96;
        }

        .btn-soft{
            border-radius:12px;
            padding:10px 18px;
            font-weight:600;
        }

        @media (max-width: 768px){
            .page-wrap{
                margin:20px auto;
            }

            .profile-card{
                padding:20px;
                border-radius:16px;
            }

            .top-bar h2{
                font-size:30px;
            }

            .top-actions{
                width:100%;
            }

            .top-actions a{
                flex:1 1 100%;
                text-align:center;
            }
        }
    </style>
</head>
<body>
<div class="page-wrap">
    <div class="profile-card">
        <div class="top-bar">
            <h2>Mon profil</h2>
            <div class="top-actions">
                <a class="btn btn-outline-primary btn-soft" href="<%= request.getContextPath() %>/offres">Voir les offres</a>
                <a class="btn btn-outline-secondary btn-soft" href="<%= request.getContextPath() %>/mes-demandes">Mes demandes</a>
                <a class="btn btn-outline-danger btn-soft" href="<%= request.getContextPath() %>/logout-locataire">Déconnexion</a>
            </div>
        </div>

        <% if(request.getAttribute("success") != null){ %>
            <div class="alert alert-success"><%= request.getAttribute("success") %></div>
        <% } %>

        <% if(request.getAttribute("error") != null){ %>
            <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
        <% } %>

        <form method="post" action="<%= request.getContextPath() %>/profil-locataire">
            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">Nom</label>
                    <input type="text" name="nom" class="form-control" value="<%= locataire != null ? locataire.getNom() : "" %>" required>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" value="<%= locataire != null ? locataire.getEmail() : "" %>" disabled>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Téléphone</label>
                    <input type="text" name="telephone" class="form-control" value="<%= locataire != null ? locataire.getTelephone() : "" %>">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Adresse</label>
                    <input type="text" name="adresse" class="form-control" value="<%= locataire != null ? locataire.getAdresse() : "" %>">
                </div>

                <div class="col-12">
                    <label class="form-label">Nouveau mot de passe (optionnel)</label>
                    <input type="password" name="motDePasse" class="form-control">
                </div>
            </div>

            <button class="btn btn-main mt-4">Mettre à jour</button>
        </form>
    </div>
</div>
</body>
</html>
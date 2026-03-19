<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription locataire</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
            background:linear-gradient(135deg,#2563eb,#1d4ed8);
            min-height:100vh;
            display:flex;
            align-items:center;
            justify-content:center;
            font-family:Arial,sans-serif;
            padding:20px;
        }

        .card-register{
            background:#fff;
            padding:32px;
            border-radius:22px;
            width:100%;
            max-width:560px;
            box-shadow:0 12px 32px rgba(0,0,0,.22);
        }

        .card-register h2{
            text-align:center;
            margin-bottom:24px;
            color:#111827;
        }

        .form-label{
            font-weight:600;
            color:#374151;
            margin-bottom:8px;
        }

        .form-control{
            border-radius:12px;
            min-height:46px;
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
            min-height:46px;
            font-weight:600;
        }

        .btn-main:hover{
            color:white;
            opacity:.95;
        }

        .bottom-link{
            text-align:center;
            margin-top:18px;
        }

        .bottom-link a{
            text-decoration:none;
            font-weight:600;
        }

        @media (max-width: 576px){
            .card-register{
                padding:22px;
                border-radius:16px;
            }

            .card-register h2{
                font-size:26px;
            }
        }
    </style>
</head>
<body>

<div class="card-register">
    <h2>Créer un compte locataire</h2>

    <% if(request.getAttribute("error") != null){ %>
        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/register">
        <div class="row g-3">
            <div class="col-md-6">
                <label class="form-label">Nom</label>
                <input type="text" name="nom" class="form-control" required>
            </div>

            <div class="col-md-6">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control" required>
            </div>

            <div class="col-md-6">
                <label class="form-label">Téléphone</label>
                <input type="text" name="telephone" class="form-control">
            </div>

            <div class="col-md-6">
                <label class="form-label">Adresse</label>
                <input type="text" name="adresse" class="form-control">
            </div>

            <div class="col-12">
                <label class="form-label">Mot de passe</label>
                <input type="password" name="motDePasse" class="form-control" required>
            </div>
        </div>

        <button class="btn btn-main w-100 mt-4">Créer mon compte</button>
    </form>

    <div class="bottom-link">
        Déjà inscrit ?
        <a href="<%= request.getContextPath() %>/login-locataire">Se connecter</a>
    </div>
</div>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion locataire</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
           <body style="
               margin:0;
               height:100vh;
               display:flex;
               align-items:center;
               justify-content:center;
               background: linear-gradient(135deg, #1e3a8a, #2563eb, #3b82f6);
               font-family: Arial, sans-serif;
           ">
        }

        .card{
            background:#fff;
            padding:30px;
            border-radius:20px;
            width:100%;
            max-width:420px;
            box-shadow:0 10px 30px rgba(0,0,0,.2);
        }

        h2{
            text-align:center;
            margin-bottom:25px;
        }

        .form-control{
            border-radius:10px;
            height:45px;
        }

        .btn-main{
            background:#2563eb;
            color:white;
            border:none;
            border-radius:10px;
            height:45px;
        }

        .btn-main:hover{
            opacity:.9;
        }
    </style>
</head>
<body>

<div class="card">

    <h2>Connexion</h2>

    <% if(request.getAttribute("error") != null){ %>
        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/login-locataire">
        <div class="mb-3">
            <input type="email" name="email" class="form-control" placeholder="Email" required>
        </div>

        <div class="mb-3">
            <input type="password" name="motDePasse" class="form-control" placeholder="Mot de passe" required>
        </div>

        <button class="btn btn-main w-100">Se connecter</button>
        <div style="margin-top:20px;text-align:center;">
            <div style="margin-bottom:10px;color:#6b7280;">Pas encore de compte ?</div>
            <a href="<%= request.getContextPath() %>/register"
               style="display:inline-block;padding:10px 16px;border-radius:10px;background:#eef2ff;color:#1d4ed8;font-weight:600;text-decoration:none;">
                Créer un compte locataire
            </a>
        </div>
    </form>

</div>

</body>
</html>
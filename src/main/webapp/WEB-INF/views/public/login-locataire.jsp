<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion locataire</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{background:#f4f7fb;font-family:Arial,sans-serif;}
        .wrap{max-width:520px;margin:60px auto;background:#fff;padding:28px;border-radius:18px;box-shadow:0 10px 30px rgba(0,0,0,.08);}
    </style>
</head>
<body>
<div class="wrap">
    <h2>Connexion locataire</h2>
    <% if(request.getAttribute("error") != null){ %>
        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/login-locataire">
        <div class="mb-3">
            <label>Email</label>
            <input type="email" name="email" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Mot de passe</label>
            <input type="password" name="motDePasse" class="form-control" required>
        </div>
        <button class="btn btn-primary w-100">Se connecter</button>
    </form>

    <div class="mt-3 text-center">
        <a href="<%= request.getContextPath() %>/register">Créer un compte locataire</a>
    </div>
</div>
</body>
</html>
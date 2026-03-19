<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription locataire</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{background:#f4f7fb;font-family:Arial,sans-serif;}
        .wrap{max-width:700px;margin:40px auto;background:#fff;padding:28px;border-radius:18px;box-shadow:0 10px 30px rgba(0,0,0,.08);}
    </style>
</head>
<body>
<div class="wrap">
    <h2>Inscription locataire</h2>
    <% if(request.getAttribute("error") != null){ %>
        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/register">
        <div class="row g-3">
            <div class="col-md-6">
                <label>Nom</label>
                <input type="text" name="nom" class="form-control" required>
            </div>
            <div class="col-md-6">
                <label>Email</label>
                <input type="email" name="email" class="form-control" required>
            </div>
            <div class="col-md-6">
                <label>Téléphone</label>
                <input type="text" name="telephone" class="form-control">
            </div>
            <div class="col-md-6">
                <label>Adresse</label>
                <input type="text" name="adresse" class="form-control">
            </div>
            <div class="col-12">
                <label>Mot de passe</label>
                <input type="password" name="motDePasse" class="form-control" required>
            </div>
        </div>
        <div class="mt-3 d-flex gap-2">
            <button class="btn btn-primary">Créer mon compte</button>
            <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/login-locataire">Déjà inscrit ? Connexion</a>
        </div>
    </form>
</div>
</body>
</html>
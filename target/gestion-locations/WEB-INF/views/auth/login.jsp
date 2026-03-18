<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            min-height: 100vh;
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #0f172a, #1d4ed8);
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }

        .login-card {
            width: 100%;
            max-width: 460px;
            background: white;
            border-radius: 24px;
            padding: 36px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.20);
        }

        .login-card h1 {
            margin: 0 0 12px 0;
            font-size: 42px;
            color: #111827;
            text-align: center;
        }

        .login-sub {
            text-align: center;
            color: #6b7280;
            margin-bottom: 24px;
        }

        .demo-box {
            background: #eff6ff;
            border: 1px solid #bfdbfe;
            color: #1d4ed8;
            border-radius: 14px;
            padding: 14px 16px;
            margin-bottom: 20px;
            font-size: 14px;
            text-align: center;
        }

        .form-group {
            margin-bottom: 18px;
        }

        .form-label {
            display: block;
            font-weight: 600;
            margin-bottom: 8px;
            color: #374151;
        }

        .form-control {
            width: 100%;
            box-sizing: border-box;
            border-radius: 12px;
            padding: 14px 16px;
            border: 1px solid #d1d5db;
            background: #f9fafb;
            font-size: 15px;
        }

        .form-control:focus {
            outline: none;
            border-color: #2563eb;
            background: white;
            box-shadow: 0 0 0 4px rgba(37,99,235,0.12);
        }

        .btn-login {
            width: 100%;
            border: none;
            border-radius: 14px;
            padding: 14px;
            font-size: 16px;
            font-weight: 700;
            color: white;
            background: linear-gradient(135deg, #2563eb, #1d4ed8);
            cursor: pointer;
        }

        .btn-login:hover {
            opacity: 0.95;
        }

        .error-box {
            background: #fef2f2;
            border: 1px solid #fecaca;
            color: #b91c1c;
            border-radius: 12px;
            padding: 12px 14px;
            margin-bottom: 18px;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="login-card">
    <h1>Connexion</h1>
    <div class="login-sub">Accédez à votre espace d’administration.</div>

    <div class="demo-box">
        <strong>Compte par défaut :</strong><br>
        admin@admin.com / admin123
    </div>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null && !error.isBlank()) {
    %>
    <div class="error-box"><%= error %></div>
    <%
        }
    %>

    <form method="post" action="<%= request.getContextPath() %>/login">
        <div class="form-group">
            <label class="form-label">Email</label>
            <input class="form-control" type="email" name="email" required>
        </div>

        <div class="form-group">
            <label class="form-label">Mot de passe</label>
            <input class="form-control" type="password" name="motDePasse" required>
        </div>

        <button type="submit" class="btn-login">Se connecter</button>
    </form>
</div>

</body>
</html>
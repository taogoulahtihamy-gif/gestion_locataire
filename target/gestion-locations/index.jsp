`<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body{
            background: linear-gradient(135deg,#0f2027,#203a43,#2c5364);
            height:100vh;
            display:flex;
            align-items:center;
            justify-content:center;
            font-family:Arial;
        }

        .login-card{
            width:400px;
            padding:40px;
            border-radius:15px;
            background:white;
            box-shadow:0 10px 30px rgba(0,0,0,0.3);
        }

        .login-title{
            text-align:center;
            margin-bottom:25px;
            font-weight:bold;
        }

        .btn-login{
            width:100%;
        }

        .default-account{
            font-size:13px;
            color:gray;
            text-align:center;
            margin-bottom:20px;
        }
    </style>
</head>

<body>

<div class="login-card">

    <h3 class="login-title">Connexion</h3>

    <div class="default-account">
        Compte par défaut : <br>
        <b>admin@admin.com / admin123</b>
    </div>

    <form action="login" method="post">

        <div class="mb-3">
            <label>Email</label>
            <input type="email" name="email" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Mot de passe</label>
            <input type="password" name="password" class="form-control" required>
        </div>

        <button class="btn btn-primary btn-login">
            Se connecter
        </button>

    </form>

</div>

</body>
</html>`
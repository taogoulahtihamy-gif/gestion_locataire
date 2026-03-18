<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.location.entity.Utilisateur" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de bord</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: #f4f7fb;
            font-family: Arial, sans-serif;
        }

        .navbar-brand {
            font-weight: bold;
        }

        .sidebar {
            min-height: calc(100vh - 56px);
            background: #1f2937;
            padding-top: 20px;
        }

        .sidebar a {
            display: block;
            color: #e5e7eb;
            text-decoration: none;
            padding: 12px 20px;
            border-radius: 8px;
            margin: 6px 10px;
            transition: 0.2s;
        }

        .sidebar a:hover,
        .sidebar a.active {
            background: #2563eb;
            color: white;
        }

        .content-area {
            padding: 30px;
        }

        .welcome-box {
            background: white;
            border-radius: 15px;
            padding: 20px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
            margin-bottom: 25px;
        }

        .stat-card {
            border: none;
            border-radius: 16px;
            color: white;
            box-shadow: 0 6px 18px rgba(0,0,0,0.08);
        }

        .stat-card .card-body {
            padding: 24px;
        }

        .stat-title {
            font-size: 15px;
            opacity: 0.9;
        }

        .stat-value {
            font-size: 30px;
            font-weight: bold;
            margin-top: 8px;
        }

        .bg-card-1 { background: linear-gradient(135deg, #2563eb, #1d4ed8); }
        .bg-card-2 { background: linear-gradient(135deg, #059669, #047857); }
        .bg-card-3 { background: linear-gradient(135deg, #d97706, #b45309); }
        .bg-card-4 { background: linear-gradient(135deg, #7c3aed, #6d28d9); }
        .bg-card-5 { background: linear-gradient(135deg, #dc2626, #b91c1c); }
        .bg-card-6 { background: linear-gradient(135deg, #0f766e, #115e59); }

        .total-box {
            background: white;
            border-radius: 15px;
            padding: 25px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
            margin-top: 25px;
        }

        .total-amount {
            font-size: 34px;
            font-weight: bold;
            color: #111827;
        }

        .muted-label {
            color: #6b7280;
        }
    </style>
</head>
<body>

<%
    Utilisateur user = (Utilisateur) session.getAttribute("user");
    String nomUtilisateur = (user != null && user.getNom() != null) ? user.getNom() : "Administrateur";
    String roleUtilisateur = (user != null && user.getRole() != null) ? user.getRole() : "ADMIN";
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3">
    <a class="navbar-brand" href="<%= request.getContextPath() %>/dashboard">Gestion Locations</a>
    <div class="ms-auto text-white">
        Connecté : <strong><%= nomUtilisateur %></strong> — <%= roleUtilisateur %>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar">
            <a href="<%= request.getContextPath() %>/dashboard" class="active">Tableau de bord</a>
            <a href="<%= request.getContextPath() %>/immeubles">Immeubles</a>
            <a href="<%= request.getContextPath() %>/unites">Unités</a>
            <a href="<%= request.getContextPath() %>/locataires">Locataires</a>
            <a href="<%= request.getContextPath() %>/contrats">Contrats</a>
            <a href="<%= request.getContextPath() %>/paiements">Paiements</a>
            <a href="<%= request.getContextPath() %>/utilisateurs">Utilisateurs</a>
            <a href="<%= request.getContextPath() %>/logout">Déconnexion</a>
        </div>

        <div class="col-md-10 content-area">
            <div class="welcome-box">
                <h2 class="mb-2">Tableau de bord</h2>
                <p class="muted-label mb-0">Vue d’ensemble de votre application de gestion locative.</p>
            </div>

            <div class="row g-4">
                <div class="col-md-4">
                    <div class="card stat-card bg-card-1">
                        <div class="card-body">
                            <div class="stat-title">Immeubles</div>
                            <div class="stat-value"><%= request.getAttribute("nbImmeubles") %></div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card stat-card bg-card-2">
                        <div class="card-body">
                            <div class="stat-title">Unités</div>
                            <div class="stat-value"><%= request.getAttribute("nbUnites") %></div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card stat-card bg-card-3">
                        <div class="card-body">
                            <div class="stat-title">Locataires</div>
                            <div class="stat-value"><%= request.getAttribute("nbLocataires") %></div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card stat-card bg-card-4">
                        <div class="card-body">
                            <div class="stat-title">Contrats</div>
                            <div class="stat-value"><%= request.getAttribute("nbContrats") %></div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card stat-card bg-card-5">
                        <div class="card-body">
                            <div class="stat-title">Paiements</div>
                            <div class="stat-value"><%= request.getAttribute("nbPaiements") %></div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card stat-card bg-card-6">
                        <div class="card-body">
                            <div class="stat-title">Utilisateurs</div>
                            <div class="stat-value"><%= request.getAttribute("nbUtilisateurs") %></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="total-box">
                <div class="muted-label">Montant total encaissé</div>
                <div class="total-amount">
                    <%= String.format("%,.0f", request.getAttribute("totalPaiements")) %> FCFA
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
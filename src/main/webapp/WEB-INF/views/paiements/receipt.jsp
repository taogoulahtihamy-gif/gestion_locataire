<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.location.entity.Paiement,com.location.entity.ContratLocation,com.location.entity.Locataire,com.location.entity.UniteLocation" %>
<%
    Paiement paiement = (Paiement) request.getAttribute("paiement");
    ContratLocation contrat = paiement != null ? paiement.getContrat() : null;
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Reçu de paiement</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f3f4f6;
            margin: 0;
            padding: 30px;
        }

        .receipt-card {
            max-width: 800px;
            margin: 0 auto;
            background: white;
            border-radius: 18px;
            padding: 32px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.08);
        }

        h1 {
            margin-top: 0;
            color: #111827;
        }

        .meta {
            margin: 20px 0;
            width: 100%;
            border-collapse: collapse;
        }

        .meta td {
            padding: 10px 0;
            border-bottom: 1px solid #e5e7eb;
        }

        .label {
            font-weight: bold;
            width: 220px;
            color: #374151;
        }

        .actions {
            margin-top: 24px;
            display: flex;
            gap: 12px;
            flex-wrap: wrap;
        }

        .btn {
            display: inline-block;
            padding: 12px 18px;
            border-radius: 12px;
            text-decoration: none;
            color: white;
            background: #2563eb;
            font-weight: bold;
        }

        .btn-secondary {
            background: #6b7280;
        }

        @media print {
            .actions {
                display: none;
            }

            body {
                background: white;
                padding: 0;
            }

            .receipt-card {
                box-shadow: none;
                max-width: 100%;
                margin: 0;
                border-radius: 0;
            }
        }
    </style>
</head>
<body>
<div class="receipt-card">
    <h1>Reçu de paiement</h1>
    <p>Ce document atteste l’enregistrement du paiement de loyer.</p>

    <table class="meta">
        <tr>
            <td class="label">Numéro de reçu</td>
            <td><%= paiement != null ? paiement.getNumeroRecu() : "-" %></td>
        </tr>
        <tr>
            <td class="label">Date de paiement</td>
            <td><%= paiement != null ? paiement.getDatePaiement() : "-" %></td>
        </tr>
        <tr>
            <td class="label">Montant</td>
            <td><%= paiement != null ? String.format("%,.0f", paiement.getMontant()) + " FCFA" : "-" %></td>
        </tr>
        <tr>
            <td class="label">Mode de paiement</td>
            <td><%= paiement != null ? paiement.getModePaiement() : "-" %></td>
        </tr>
        <tr>
            <td class="label">Statut</td>
            <td><%= paiement != null ? paiement.getStatut() : "-" %></td>
        </tr>
        <tr>
            <td class="label">Contrat</td>
            <td><%= contrat != null ? contrat.getId() : "-" %></td>
        </tr>
        <tr>
            <td class="label">Locataire</td>
            <td><%= contrat != null && contrat.getLocataire() != null ? contrat.getLocataire().getNom() : "-" %></td>
        </tr>
        <tr>
            <td class="label">Unité</td>
            <td><%= contrat != null && contrat.getUniteLocation() != null ? contrat.getUniteLocation().getId() : "-" %></td>
        </tr>
    </table>

    <div class="actions">
        <a href="javascript:window.print()" class="btn">Imprimer / PDF</a>
        <a href="<%= request.getContextPath() %>/paiements" class="btn btn-secondary">Retour</a>
    </div>
</div>
</body>
</html>
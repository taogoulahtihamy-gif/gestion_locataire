<%@ page contentType="text/html;charset=UTF-8" %>
<% request.setAttribute("pageTitle", "Dashboard"); %>
<%@ include file="/WEB-INF/views/common/header.jspf" %>

<div class="page-card">
    <div class="page-title">Tableau de bord</div>
    <p class="page-subtitle">Vue globale de votre activité immobilière</p>
</div>

<div class="stats-grid">

    <div class="stat-card">
        <div class="stat-title">Immeubles</div>
        <div class="stat-value"><%= request.getAttribute("nbImmeubles") %></div>
    </div>

    <div class="stat-card">
        <div class="stat-title">Unités</div>
        <div class="stat-value"><%= request.getAttribute("nbUnites") %></div>
    </div>

    <div class="stat-card">
        <div class="stat-title">Locataires</div>
        <div class="stat-value"><%= request.getAttribute("nbLocataires") %></div>
    </div>

    <div class="stat-card">
        <div class="stat-title">Contrats</div>
        <div class="stat-value"><%= request.getAttribute("nbContrats") %></div>
    </div>

    <div class="stat-card">
        <div class="stat-title">Paiements</div>
        <div class="stat-value"><%= request.getAttribute("nbPaiements") %></div>
    </div>

    <div class="stat-card">
        <div class="stat-title">Utilisateurs</div>
        <div class="stat-value"><%= request.getAttribute("nbUtilisateurs") %></div>
    </div>

    <div class="stat-card highlight">
        <div class="stat-title">Total encaissé</div>
        <div class="stat-value"><%= String.format("%,.0f", request.getAttribute("totalPaiements")) %> FCFA</div>
    </div>

</div>

<div class="page-card" style="margin-top:20px;">
    <h2>Indicateurs avancés</h2>

    <div class="stats-grid">

        <div class="stat-card">
            <div class="stat-title">Contrats actifs</div>
            <div class="stat-value"><%= request.getAttribute("nbContratsActifs") %></div>
        </div>

        <div class="stat-card">
            <div class="stat-title">Unités disponibles</div>
            <div class="stat-value"><%= request.getAttribute("nbUnitesDisponibles") %></div>
        </div>

        <div class="stat-card">
            <div class="stat-title">Unités occupées</div>
            <div class="stat-value"><%= request.getAttribute("nbUnitesOccupees") %></div>
        </div>

        <div class="stat-card danger">
            <div class="stat-title">Paiements en retard</div>
            <div class="stat-value"><%= request.getAttribute("nbPaiementsEnRetard") %></div>
        </div>

        <div class="stat-card highlight">
            <div class="stat-title">Taux d’occupation</div>
            <div class="stat-value"><%= String.format("%.1f", request.getAttribute("tauxOccupation")) %> %</div>
        </div>

    </div>
</div>

<style>
.stats-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
    gap: 16px;
    margin-top: 20px;
}

.stat-card {
    background: white;
    border-radius: 16px;
    padding: 20px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.05);
}

.stat-title {
    font-size: 14px;
    color: #6b7280;
    margin-bottom: 8px;
}

.stat-value {
    font-size: 26px;
    font-weight: bold;
    color: #111827;
}

.stat-card.highlight {
    background: linear-gradient(135deg, #2563eb, #1d4ed8);
    color: white;
}

.stat-card.highlight .stat-title,
.stat-card.highlight .stat-value {
    color: white;
}

.stat-card.danger {
    background: #fee2e2;
}
</style>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List,com.location.entity.Locataire" %>
<% request.setAttribute("pageTitle", "Locataires"); %>
<%@ include file="/WEB-INF/views/common/header.jspf" %>

<%
    Locataire item = (Locataire) request.getAttribute("item");
    List<Locataire> items = (List<Locataire>) request.getAttribute("items");
%>

<div class="page-card">
    <div class="page-title">Gestion des locataires</div>
    <p class="page-subtitle">Ajoutez, modifiez et gérez les profils locataires.</p>

    <form method="post" action="<%= request.getContextPath() %>/locataires">
        <input type="hidden" name="id" value="<%= item != null && item.getId() != null ? item.getId() : "" %>">

        <div class="form-grid">
            <div class="field">
                <label>Nom</label>
                <input type="text" name="nom" value="<%= item != null ? item.getNom() : "" %>" required>
            </div>
            <div class="field">
                <label>Email</label>
                <input type="email" name="email" value="<%= item != null ? item.getEmail() : "" %>" required>
            </div>
            <div class="field">
                <label>Téléphone</label>
                <input type="text" name="telephone" value="<%= item != null ? item.getTelephone() : "" %>" required>
            </div>
        </div>

        <div class="actions">
            <button class="btn-soft btn-primary-soft" type="submit"><%= item != null ? "Mettre à jour le locataire" : "Ajouter le locataire" %></button>
            <a class="btn-soft btn-secondary-soft" href="<%= request.getContextPath() %>/locataires">Réinitialiser</a>
        </div>
    </form>
</div>

<div class="page-card">
    <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:18px;flex-wrap:wrap;gap:12px;">
        <h2 style="margin:0;font-size:24px;">Liste des locataires</h2>
        <span class="badge-soft"><%= items != null ? items.size() : 0 %> locataire(s)</span>
    </div>

    <div class="table-wrapper">
    <div style="overflow-x:auto;">
        <table class="table-modern">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Email</th>
                <th>Téléphone</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (items != null && !items.isEmpty()) {
                    for (Locataire i : items) {
            %>
            <tr>
                <td><strong><%= i.getId() %></strong></td>
                <td><%= i.getNom() %></td>
                <td><%= i.getEmail() %></td>
                <td><%= i.getTelephone() %></td>
                <td>
                    <div style="display:flex;gap:8px;flex-wrap:wrap;">
                        <a class="btn-soft btn-warning-soft" href="<%= request.getContextPath() %>/locataires?action=edit&id=<%= i.getId() %>">Modifier</a>
                        <a class="btn-soft btn-danger-soft" href="<%= request.getContextPath() %>/locataires?action=delete&id=<%= i.getId() %>" onclick="return confirm('Supprimer ce locataire ?')">Supprimer</a>
                    </div>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5" style="text-align:center;color:#6b7280;">Aucun locataire enregistré pour le moment.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>
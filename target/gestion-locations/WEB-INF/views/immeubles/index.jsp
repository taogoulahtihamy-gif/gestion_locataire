<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List,com.location.entity.Immeuble" %>
<% request.setAttribute("pageTitle", "Immeubles"); %>
<%@ include file="/WEB-INF/views/common/header.jspf" %>

<%
    Immeuble item = (Immeuble) request.getAttribute("item");
    List<Immeuble> items = (List<Immeuble>) request.getAttribute("items");
%>

<div class="page-card">
    <div class="page-title">Gestion des immeubles</div>
    <p class="page-subtitle">Ajoutez, modifiez et gérez les immeubles de votre parc locatif.</p>

    <form method="post" action="<%= request.getContextPath() %>/immeubles">
        <input type="hidden" name="id" value="<%= item != null && item.getId() != null ? item.getId() : "" %>">

        <div class="form-grid">
            <div class="field">
                <label>Nom</label>
                <input type="text" name="nom" value="<%= item != null ? item.getNom() : "" %>" required>
            </div>

            <div class="field">
                <label>Adresse</label>
                <input type="text" name="adresse" value="<%= item != null ? item.getAdresse() : "" %>" required>
            </div>

            <div class="field">
                <label>Nombre d'unités</label>
                <input type="number" name="nombreUnites" value="<%= item != null && item.getNombreUnites() != null ? item.getNombreUnites() : "" %>">
            </div>

            <div class="field">
                <label>Équipements</label>
                <input type="text" name="equipements" value="<%= item != null ? item.getEquipements() : "" %>">
            </div>

            <div class="field" style="grid-column: 1 / -1;">
                <label>Description</label>
                <textarea name="description"><%= item != null ? item.getDescription() : "" %></textarea>
            </div>
        </div>

        <div class="actions">
            <button class="btn-soft btn-primary-soft" type="submit">
                <%= item != null ? "Mettre à jour l'immeuble" : "Ajouter l'immeuble" %>
            </button>
            <a class="btn-soft btn-secondary-soft" href="<%= request.getContextPath() %>/immeubles">Réinitialiser</a>
        </div>
    </form>
</div>

<div class="page-card">
    <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:18px;flex-wrap:wrap;gap:12px;">
        <h2 style="margin:0;font-size:24px;">Liste des immeubles</h2>
        <span class="badge-soft"><%= items != null ? items.size() : 0 %> immeuble(s)</span>
    </div>

    <div class="table-wrapper">
    <div style="overflow-x:auto;">
        <table class="table-modern">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Adresse</th>
                <th>Unités</th>
                <th>Équipements</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (items != null && !items.isEmpty()) {
                    for (Immeuble i : items) {
            %>
            <tr>
                <td><strong><%= i.getId() %></strong></td>
                <td><%= i.getNom() %></td>
                <td><%= i.getAdresse() %></td>
                <td><%= i.getNombreUnites() != null ? i.getNombreUnites() : 0 %></td>
                <td><%= i.getEquipements() != null ? i.getEquipements() : "-" %></td>
                <td>
                    <div style="display:flex;gap:8px;flex-wrap:wrap;">
                        <a class="btn-soft btn-warning-soft" href="<%= request.getContextPath() %>/immeubles?action=edit&id=<%= i.getId() %>">Modifier</a>
                        <a class="btn-soft btn-danger-soft" href="<%= request.getContextPath() %>/immeubles?action=delete&id=<%= i.getId() %>" onclick="return confirm('Supprimer cet immeuble ?')">Supprimer</a>
                    </div>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6" style="text-align:center;color:#6b7280;">Aucun immeuble enregistré pour le moment.</td>
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
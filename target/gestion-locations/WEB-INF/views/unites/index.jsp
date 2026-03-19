<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List,com.location.entity.UniteLocation,com.location.entity.Immeuble" %>
<% request.setAttribute("pageTitle", "Unités"); %>
<%@ include file="/WEB-INF/views/common/header.jspf" %>

<%
    UniteLocation item = (UniteLocation) request.getAttribute("item");
    List<UniteLocation> items = (List<UniteLocation>) request.getAttribute("items");
    List<Immeuble> immeubles = (List<Immeuble>) request.getAttribute("immeubles");
%>

<div class="page-card">
    <div class="page-title">Gestion des unités</div>
    <p class="page-subtitle">Ajoutez, modifiez et gérez les unités de location.</p>

    <form method="post" action="<%= request.getContextPath() %>/unites">
        <input type="hidden" name="id" value="<%= item != null && item.getId() != null ? item.getId() : "" %>">

        <div class="form-grid">
            <div class="field">
                <label>Immeuble</label>
                <select name="immeubleId" required>
                    <option value="">Choisir</option>
                    <%
                        if (immeubles != null) {
                            for (Immeuble i : immeubles) {
                    %>
                    <option value="<%= i.getId() %>" <%= item != null && item.getImmeuble() != null && item.getImmeuble().getId().equals(i.getId()) ? "selected" : "" %>>
                        <%= i.getNom() %>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div class="field">
                <label>Nombre de pièces</label>
                <input type="number" name="nombrePieces" value="<%= item != null ? item.getNombrePieces() : "" %>" required>
            </div>

            <div class="field">
                <label>Superficie (m²)</label>
                <input type="number" step="0.01" name="superficie" value="<%= item != null ? item.getSuperficie() : "" %>" required>
            </div>

            <div class="field">
                <label>Loyer mensuel</label>
                <input type="number" step="0.01" name="loyer" value="<%= item != null ? item.getLoyer() : "" %>" required>
            </div>

            <div class="field">
                <label>Statut</label>
                <select name="statut" required>
                    <%
                        String[] statuts = {"Disponible", "Occupée", "Maintenance"};
                        for (String s : statuts) {
                    %>
                    <option value="<%= s %>" <%= item != null && s.equals(item.getStatut()) ? "selected" : "" %>><%= s %></option>
                    <%
                        }
                    %>
                </select>
            </div>
        </div>

        <div class="actions">
            <button class="btn-soft btn-primary-soft" type="submit"><%= item != null ? "Mettre à jour l'unité" : "Ajouter l'unité" %></button>
            <a class="btn-soft btn-secondary-soft" href="<%= request.getContextPath() %>/unites">Réinitialiser</a>
        </div>
    </form>
</div>

<div class="page-card">
    <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:18px;flex-wrap:wrap;gap:12px;">
        <h2 style="margin:0;font-size:24px;">Liste des unités</h2>
        <span class="badge-soft"><%= items != null ? items.size() : 0 %> unité(s)</span>
    </div>

    <div class="table-wrapper">
    <div style="overflow-x:auto;">
        <table class="table-modern">
            <thead>
            <tr>
                <th>ID</th>
                <th>Immeuble</th>
                <th>Pièces</th>
                <th>Superficie</th>
                <th>Loyer</th>
                <th>Statut</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (items != null && !items.isEmpty()) {
                    for (UniteLocation i : items) {
            %>
            <tr>
                <td><strong><%= i.getId() %></strong></td>
                <td><%= i.getImmeuble() != null ? i.getImmeuble().getNom() : "-" %></td>
                <td><%= i.getNombrePieces() %></td>
                <td><%= i.getSuperficie() %> m²</td>
                <td><%= String.format("%,.0f", i.getLoyer()) %> FCFA</td>
                <td><%= i.getStatut() %></td>
                <td>
                    <div style="display:flex;gap:8px;flex-wrap:wrap;">
                        <a class="btn-soft btn-warning-soft" href="<%= request.getContextPath() %>/unites?action=edit&id=<%= i.getId() %>">Modifier</a>
                        <a class="btn-soft btn-danger-soft" href="<%= request.getContextPath() %>/unites?action=delete&id=<%= i.getId() %>" onclick="return confirm('Supprimer cette unité ?')">Supprimer</a>
                    </div>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="7" style="text-align:center;color:#6b7280;">Aucune unité enregistrée pour le moment.</td>
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
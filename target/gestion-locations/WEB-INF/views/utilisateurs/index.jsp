<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List,com.location.entity.Utilisateur" %>
<% request.setAttribute("pageTitle", "Utilisateurs"); %>
<%@ include file="/WEB-INF/views/common/header.jspf" %>

<%
    Utilisateur item = (Utilisateur) request.getAttribute("item");
    List<Utilisateur> items = (List<Utilisateur>) request.getAttribute("items");
%>

<div class="page-card">
    <div class="page-title">Gestion des utilisateurs</div>
    <p class="page-subtitle">Ajoutez et gérez les comptes d’accès à l’application.</p>

    <form method="post" action="<%= request.getContextPath() %>/utilisateurs">
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
                <label>Mot de passe</label>
                <input type="password" name="motDePasse" placeholder="<%= item != null ? "Laisser vide pour conserver l'ancien" : "" %>">
            </div>

            <div class="field">
                <label>Rôle</label>
                <select name="role" required>
                    <option value="ADMIN" <%= item != null && "ADMIN".equalsIgnoreCase(item.getRole()) ? "selected" : "" %>>ADMIN</option>
                    <option value="GESTIONNAIRE" <%= item != null && "GESTIONNAIRE".equalsIgnoreCase(item.getRole()) ? "selected" : "" %>>GESTIONNAIRE</option>
                </select>
            </div>
        </div>

        <div class="actions">
            <button class="btn-soft btn-primary-soft" type="submit">
                <%= item != null ? "Mettre à jour l'utilisateur" : "Ajouter l'utilisateur" %>
            </button>
            <a class="btn-soft btn-secondary-soft" href="<%= request.getContextPath() %>/utilisateurs">Réinitialiser</a>
        </div>
    </form>
</div>

<div class="page-card">
    <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:18px;flex-wrap:wrap;gap:12px;">
        <h2 style="margin:0;font-size:24px;">Liste des utilisateurs</h2>
        <span class="badge-soft"><%= items != null ? items.size() : 0 %> utilisateur(s)</span>
    </div>

    <div class="table-wrapper">
    <div style="overflow-x:auto;">
        <table class="table-modern">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Email</th>
                <th>Rôle</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (items != null && !items.isEmpty()) {
                    for (Utilisateur u : items) {
            %>
            <tr>
                <td><strong><%= u.getId() %></strong></td>
                <td><%= u.getNom() %></td>
                <td><%= u.getEmail() %></td>
                <td><%= u.getRole() %></td>
                <td>
                    <div style="display:flex;gap:8px;flex-wrap:wrap;">
                        <a class="btn-soft btn-warning-soft"
                           href="<%= request.getContextPath() %>/utilisateurs?action=edit&id=<%= u.getId() %>">
                            Modifier
                        </a>
                        <a class="btn-soft btn-danger-soft"
                           href="<%= request.getContextPath() %>/utilisateurs?action=delete&id=<%= u.getId() %>"
                           onclick="return confirm('Supprimer cet utilisateur ?')">
                            Supprimer
                        </a>
                    </div>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5" style="text-align:center;color:#6b7280;">Aucun utilisateur enregistré pour le moment.</td>
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
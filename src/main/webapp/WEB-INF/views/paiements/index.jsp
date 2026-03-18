<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List,com.location.entity.Paiement,com.location.entity.ContratLocation" %>
<% request.setAttribute("pageTitle", "Paiements"); %>
<%@ include file="/WEB-INF/views/common/header.jspf" %>

<%
    Paiement item = (Paiement) request.getAttribute("item");
    List<Paiement> items = (List<Paiement>) request.getAttribute("items");
    List<ContratLocation> contrats = (List<ContratLocation>) request.getAttribute("contrats");
    String erreur = (String) request.getAttribute("erreur");
%>

<div class="page-card">
    <div class="page-title">Gestion des paiements</div>
    <p class="page-subtitle">Enregistrez et suivez les paiements de loyers.</p>

    <% if (erreur != null && !erreur.isBlank()) { %>
    <div style="margin-bottom:16px;padding:12px 14px;border-radius:12px;background:#fef2f2;color:#b91c1c;border:1px solid #fecaca;">
        <%= erreur %>
    </div>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/paiements">
        <input type="hidden" name="id" value="<%= item != null && item.getId() != null ? item.getId() : "" %>">

        <div class="form-grid">
            <div class="field">
                <label>Contrat</label>
                <select name="contratId" required>
                    <option value="">Choisir</option>
                    <%
                        if (contrats != null) {
                            for (ContratLocation c : contrats) {
                    %>
                    <option value="<%= c.getId() %>" <%= item != null && item.getContrat() != null && item.getContrat().getId().equals(c.getId()) ? "selected" : "" %>>
                        Contrat #<%= c.getId() %>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div class="field">
                <label>Montant</label>
                <input type="number" step="0.01" name="montant" value="<%= item != null ? item.getMontant() : "" %>" required>
            </div>

            <div class="field">
                <label>Mode de paiement</label>
                <select name="modePaiement" required>
                    <%
                        String[] modes = {"Espèces", "Virement", "Wave", "Orange Money"};
                        for (String m : modes) {
                    %>
                    <option value="<%= m %>" <%= item != null && m.equals(item.getModePaiement()) ? "selected" : "" %>><%= m %></option>
                    <%
                        }
                    %>
                </select>
            </div>
        </div>

        <div class="actions">
            <button class="btn-soft btn-primary-soft" type="submit"><%= item != null ? "Mettre à jour le paiement" : "Ajouter le paiement" %></button>
            <a class="btn-soft btn-secondary-soft" href="<%= request.getContextPath() %>/paiements">Réinitialiser</a>
        </div>
    </form>
</div>

<div class="page-card">
    <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:18px;flex-wrap:wrap;gap:12px;">
        <h2 style="margin:0;font-size:24px;">Liste des paiements</h2>
        <span class="badge-soft"><%= items != null ? items.size() : 0 %> paiement(s)</span>
    </div>

    <div style="overflow-x:auto;">
        <table class="table-modern">
            <thead>
            <tr>
                <th>ID</th>
                <th>Contrat</th>
                <th>Montant</th>
                <th>Date paiement</th>
                <th>Mode</th>
                <th>Statut</th>
                <th>Reçu</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (items != null && !items.isEmpty()) {
                    for (Paiement p : items) {
            %>
            <tr>
                <td><strong><%= p.getId() %></strong></td>
                <td><%= p.getContrat() != null ? p.getContrat().getId() : "-" %></td>
                <td><%= String.format("%,.0f", p.getMontant()) %> FCFA</td>
                <td><%= p.getDatePaiement() %></td>
                <td><%= p.getModePaiement() %></td>
                <td><%= p.getStatut() != null ? p.getStatut() : "-" %></td>
                <td><%= p.getNumeroRecu() != null ? p.getNumeroRecu() : "-" %></td>
                <td>
                    <div style="display:flex;gap:8px;flex-wrap:wrap;">
                        <a class="btn-soft btn-primary-soft" href="<%= request.getContextPath() %>/receipt?id=<%= p.getId() %>">Reçu</a>
                        <a class="btn-soft btn-warning-soft" href="<%= request.getContextPath() %>/paiements?action=edit&id=<%= p.getId() %>">Modifier</a>
                        <a class="btn-soft btn-danger-soft" href="<%= request.getContextPath() %>/paiements?action=delete&id=<%= p.getId() %>" onclick="return confirm('Supprimer ce paiement ?')">Supprimer</a>
                    </div>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="8" style="text-align:center;color:#6b7280;">Aucun paiement enregistré pour le moment.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>
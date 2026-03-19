<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List,java.text.SimpleDateFormat,com.location.entity.ContratLocation,com.location.entity.Locataire,com.location.entity.UniteLocation" %>
<% request.setAttribute("pageTitle", "Contrats"); %>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%
    ContratLocation item = (ContratLocation) request.getAttribute("item");
    List<ContratLocation> items = (List<ContratLocation>) request.getAttribute("items");
    List<Locataire> locataires = (List<Locataire>) request.getAttribute("locataires");
    List<UniteLocation> unites = (List<UniteLocation>) request.getAttribute("unites");
    Long demandeSourceId = (Long) request.getAttribute("demandeSourceId");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>

<div class="page-card">
    <h1 class="page-title">Gestion des contrats</h1>
    <p class="page-subtitle">Créer et suivre les contrats de location.</p>
</div>

<div class="page-card">
    <h3><%= item != null && item.getId() != null ? "Modifier le contrat" : "Créer un contrat" %></h3>
<% if (request.getAttribute("errorMessage") != null) { %>
    <div class="alert alert-danger" style="margin-bottom: 20px;">
        <%= request.getAttribute("errorMessage") %>
    </div>
<% } %>
    <form method="post" action="<%= request.getContextPath() %>/contrats">
        <input type="hidden" name="id" value="<%= item != null && item.getId()!=null ? item.getId() : "" %>">
        <input type="hidden" name="demandeSourceId" value="<%= demandeSourceId != null ? demandeSourceId : "" %>">

        <div class="form-grid">
            <div class="field">
                <label>Locataire</label>
                <select name="locataireId" required>
                    <option value="">Choisir</option>
                    <% for (Locataire l : locataires) { %>
                        <option value="<%= l.getId() %>"
                            <%= item!=null && item.getLocataire()!=null && item.getLocataire().getId().equals(l.getId()) ? "selected" : "" %>>
                            <%= l.getNom() %>
                        </option>
                    <% } %>
                </select>
            </div>

            <div class="field">
                <label>Unité</label>
                <select name="uniteId" required>
                    <option value="">Choisir</option>
                    <% for (UniteLocation u : unites) { %>
                        <option value="<%= u.getId() %>"
                            <%= item!=null && item.getUniteLocation()!=null && item.getUniteLocation().getId().equals(u.getId()) ? "selected" : "" %>>
                            Unité #<%= u.getId() %> - <%= u.getNombrePieces() %> pièces
                        </option>
                    <% } %>
                </select>
            </div>

            <div class="field">
                <label>Date début</label>
                <input type="date" name="dateDebut"
                       value="<%= item != null && item.getDateDebut()!=null ? sdf.format(item.getDateDebut()) : "" %>" required>
            </div>

            <div class="field">
                <label>Date fin</label>
                <input type="date" name="dateFin"
                       value="<%= item != null && item.getDateFin()!=null ? sdf.format(item.getDateFin()) : "" %>" required>
            </div>

            <div class="field">
                <label>Statut</label>
                <select name="statut" required>
                    <% String[] statuts={"Actif","En attente","Expiré","Résilié"}; for(String s:statuts){ %>
                        <option value="<%= s %>" <%= item!=null && s.equals(item.getStatut()) ? "selected" : "" %>><%= s %></option>
                    <% } %>
                </select>
            </div>
        </div>

        <div class="actions">
            <button class="btn-soft btn-primary-soft" type="submit">
                <%= item != null && item.getId()!=null ? "Mettre à jour" : "Créer le contrat" %>
            </button>
            <a class="btn-soft btn-secondary-soft" href="<%= request.getContextPath() %>/contrats">Réinitialiser</a>
        </div>
    </form>
</div>

<div class="page-card">
    <h3>Liste des contrats</h3>
    <div class="table-wrapper">
        <table class="table-modern">
            <thead>
            <tr>
                <th>ID</th>
                <th>Locataire</th>
                <th>Unité</th>
                <th>Début</th>
                <th>Fin</th>
                <th>Statut</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% if (items != null && !items.isEmpty()) { %>
                <% for (ContratLocation c : items) { %>
                    <tr>
                        <td><%= c.getId() %></td>
                        <td><%= c.getLocataire()!=null ? c.getLocataire().getNom() : "" %></td>
                        <td><%= c.getUniteLocation()!=null ? c.getUniteLocation().getId() : "" %></td>
                        <td><%= c.getDateDebut()!=null ? sdf.format(c.getDateDebut()) : "" %></td>
                        <td><%= c.getDateFin()!=null ? sdf.format(c.getDateFin()) : "" %></td>
                        <td><%= c.getStatut() %></td>
                        <td>
                            <a class="btn-soft btn-warning-soft" href="<%= request.getContextPath() %>/contrats?action=edit&id=<%= c.getId() %>">Modifier</a>
                            <a class="btn-soft btn-danger-soft" href="<%= request.getContextPath() %>/contrats?action=delete&id=<%= c.getId() %>" onclick="return confirm('Supprimer ce contrat ?')">Supprimer</a>
                        </td>
                    </tr>
                <% } %>
            <% } else { %>
                <tr>
                    <td colspan="7" style="text-align:center;">Aucun contrat trouvé.</td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>
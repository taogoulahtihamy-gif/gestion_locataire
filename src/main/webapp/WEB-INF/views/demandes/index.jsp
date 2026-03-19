<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.location.entity.DemandeLocation" %>
<%
    List<DemandeLocation> items = (List<DemandeLocation>) request.getAttribute("items");
%>

<%@ include file="/WEB-INF/views/common/header.jspf" %>

<div class="page-card">
    <h1 class="page-title">Gestion des demandes</h1>
    <p class="page-subtitle">Suivi des demandes de location soumises par les locataires.</p>
   <div class="table-wrapper">
       <table class="table-modern" style="min-width: 700px;">
    <div class="table-responsive mt-4">
        <table class="table table-bordered bg-white">
            <thead>
            <tr>
                <th>ID</th>
                <th>Locataire</th>
                <th>Email</th>
                <th>Unité</th>
                <th>Date</th>
                <th>Statut</th>
                <th>Commentaire</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (items != null && !items.isEmpty()) {
                    for (DemandeLocation d : items) {
            %>
            <tr>
                <td><%= d.getId() %></td>
                <td><%= d.getLocataire() != null ? d.getLocataire().getNom() : "-" %></td>
                <td><%= d.getLocataire() != null ? d.getLocataire().getEmail() : "-" %></td>
                <td>
                    <%= d.getUniteLocation() != null
                            ? (d.getUniteLocation().getReference() != null
                                ? d.getUniteLocation().getReference()
                                : "Unité #" + d.getUniteLocation().getId())
                            : "-" %>
                </td>
                <td><%= d.getDateDemande() %></td>
                <td><%= d.getStatut() %></td>
                <td><%= d.getCommentaire() != null ? d.getCommentaire() : "-" %></td>
               <td>
                   <% if ("EN_ATTENTE".equalsIgnoreCase(d.getStatut())) { %>
                       <a class="btn btn-sm btn-success" href="<%= request.getContextPath() %>/demandes?action=accepter&id=<%= d.getId() %>">Accepter</a>
                       <a class="btn btn-sm btn-danger" href="<%= request.getContextPath() %>/demandes?action=refuser&id=<%= d.getId() %>">Refuser</a>

                   <% } else if ("ACCEPTEE".equalsIgnoreCase(d.getStatut())) { %>
                       <a class="btn btn-sm btn-primary" href="<%= request.getContextPath() %>/contrats?action=fromDemande&demandeId=<%= d.getId() %>">Créer contrat</a>

                   <% } else if ("CONTRAT_CREE".equalsIgnoreCase(d.getStatut())) { %>
                       <span class="badge bg-primary">Contrat créé</span>

                   <% } else if ("REFUSEE".equalsIgnoreCase(d.getStatut())) { %>
                       <span class="badge bg-danger">Refusée</span>

                   <% } else { %>
                       <span class="text-muted">Traitée</span>
                   <% } %>
               </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="8" class="text-center">Aucune demande trouvée.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>
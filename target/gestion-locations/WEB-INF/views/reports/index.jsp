<%@ page contentType="text/html;charset=UTF-8" %>
<% request.setAttribute("pageTitle", "Rapports"); %>
<%@ include file="/WEB-INF/views/common/header.jspf" %>

<div class="page-card">

    <div class="header">
        <h1>Rapports & Statistiques</h1>

        <div class="export-actions">
            <button type="button" onclick="exportPDF()" class="btn-soft btn-primary-soft">
                📄 Export PDF
            </button>

            <button type="button" onclick="exportExcel()" class="btn-soft btn-success-soft">
                📊 Export Excel
            </button>
        </div>
    </div>

    <div class="report-stats-grid">

        <div class="report-stat-card">
            <div class="label">Demandes</div>
            <div class="value"><%= request.getAttribute("nbDemandes") %></div>
        </div>

        <div class="report-stat-card">
            <div class="label">Contrats</div>
            <div class="value"><%= request.getAttribute("nbContrats") %></div>
        </div>

        <div class="report-stat-card">
            <div class="label">Paiements</div>
            <div class="value"><%= request.getAttribute("nbPaiements") %></div>
        </div>

        <div class="report-stat-card highlight">
            <div class="label">Total encaissé</div>
            <div class="value big">
                <%= String.format("%,.0f", request.getAttribute("totalPaiements")) %> FCFA
            </div>
        </div>

    </div>
</div>

<style>
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 25px;
    flex-wrap: wrap;
    gap: 16px;
}

.export-actions {
    display: flex;
    gap: 14px;
    flex-wrap: wrap;
}

.btn-soft {
    border: none;
    border-radius: 20px;
    padding: 12px 22px;
    font-size: 16px;
    font-weight: 700;
    color: white;
    cursor: pointer;
    display: flex;
    align-items: center;
    gap: 8px;
    box-shadow: 0 10px 20px rgba(0,0,0,0.15);
    transition: all 0.2s ease;
}

.btn-soft:hover {
    transform: translateY(-2px) scale(1.03);
    box-shadow: 0 14px 25px rgba(0,0,0,0.25);
}

.btn-soft:active {
    transform: scale(0.98);
}

.btn-primary-soft {
    background: linear-gradient(135deg, #1d4ed8, #2563eb);
}

.btn-success-soft {
    background: linear-gradient(135deg, #16a34a, #22c55e);
}

.report-stats-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 20px;
}

.report-stat-card {
    background: #eef3f7;
    border-radius: 16px;
    padding: 25px;
    text-align: center;
}

.label {
    font-size: 14px;
    color: #555;
    margin-bottom: 10px;
}

.value {
    font-size: 22px;
    font-weight: bold;
}

.value.big {
    font-size: 26px;
}

.highlight {
    background: linear-gradient(135deg, #e0f2fe, #dbeafe);
}

@media print {
    .export-actions,
    button {
        display: none !important;
    }

    body {
        background: white;
    }

    .page-card {
        box-shadow: none;
    }
}
</style>

<script>
function exportPDF() {
    window.location.href = "<%= request.getContextPath() %>/reports/pdf";
}

function exportExcel() {
    let rows = [
        ["Type", "Valeur"],
        ["Demandes", "<%= request.getAttribute("nbDemandes") %>"],
        ["Contrats", "<%= request.getAttribute("nbContrats") %>"],
        ["Paiements", "<%= request.getAttribute("nbPaiements") %>"],
        ["Total encaissé", "<%= String.format("%,.0f", request.getAttribute("totalPaiements")) %> FCFA"]
    ];

    let content = rows.map(row => row.join("\t")).join("\n");

    let blob = new Blob([content], { type: "application/vnd.ms-excel;charset=utf-8;" });
    let link = document.createElement("a");

    link.href = URL.createObjectURL(blob);
    link.download = "rapport.xls";
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}
</script>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>
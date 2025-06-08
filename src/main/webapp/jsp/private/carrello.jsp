<%@page import="java.util.Map"%>
<%@page import="com.azienda.progettoCatalogoProdotto.model.Prodotto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carrello</title>    
    <link rel="icon" href="<%=request.getContextPath()%>/resource/img/logo-sito.png"  type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/carrello.css">
</head>
<body>

<jsp:include page="/jsp/navbar.jsp" />

<div class="carrello-wrapper allineamento">
    <h2>Il tuo Carrello</h2>

    <%
        Map<Prodotto, Integer> carrelloProdotti = (Map<Prodotto, Integer>) request.getAttribute("carrelloProdotti");
    	
        double totale = 0;

        if (carrelloProdotti == null || carrelloProdotti.isEmpty()) {
    %>
        <p class="alert alert-warning text-center">Il carrello è vuoto.</p>
    <%
        } else {
    %>	
    	<% String erroreAcquisto = (String) request.getAttribute("messaggioErrore");
			if (erroreAcquisto != null) { %>
			<p class="error"><%= erroreAcquisto %></p>
		<% } %>
        <div class="tabella-carrello-wrapper table-responsive mx-auto">
    		<table class="table table-striped table-hover align-middle text-center">
                <thead class="table-primary">
                    <tr>
                        <th>Nome</th>
                        <th>Prezzo</th>
                        <th>Quantità</th>
                        <th>Subtotale</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Map.Entry<Prodotto, Integer> entry : carrelloProdotti.entrySet()) {
                            Prodotto p = entry.getKey();
                            int quantita = entry.getValue();
                            double subtotale = p.getPrezzo() * quantita;
                            totale += subtotale;
                    %>
                    <tr>
                        <td><%= p.getNome() %></td>
                        <td>€ <%= String.format("%.2f", p.getPrezzo()) %></td>
                        <td><%= quantita %></td>
                        <td>€ <%= String.format("%.2f", subtotale) %></td>
                    </tr>
                    <%
                        }
                    %>
                    <tr class="table-secondary">
                        <td colspan="3" class="text-end"><strong>Totale</strong></td>
                        <td><strong>€ <%= String.format("%.2f", totale) %></strong></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="text-center mt-4">
            <button type="button" class="btn btn-success me-2" data-bs-toggle="modal" data-bs-target="#confermaModal">
                Conferma Ordine
            </button>
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#svuotaModal">
                Svuota Carrello
            </button>
        </div>
    <%
        }
    %>
</div>

<!-- MODALE CONFERMA -->
<div class="modal fade" id="confermaModal" tabindex="-1" aria-labelledby="confermaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Conferma Ordine</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Chiudi"></button>
            </div>
            <div class="modal-body">Sei sicuro di voler confermare?</div>
            <div class="modal-footer">
                <form action="<%=request.getContextPath()%>/ConfermaOrdineServlet" method="post">
                    <button type="submit" class="btn btn-primary">Sì</button>
                </form>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
            </div>
        </div>
    </div>
</div>

<!-- MODALE SVUOTA -->
<div class="modal fade" id="svuotaModal" tabindex="-1" aria-labelledby="svuotaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Svuota Carrello</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Chiudi"></button>
            </div>
            <div class="modal-body">Sei sicuro di voler svuotare il carrello?</div>
            <div class="modal-footer">
                <form action="<%=request.getContextPath()%>/SvuotaCarrelloServlet" method="post">
                    <button type="submit" class="btn btn-primary">Sì</button>
                </form>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/darkMode.js"></script>
</body>
</html>

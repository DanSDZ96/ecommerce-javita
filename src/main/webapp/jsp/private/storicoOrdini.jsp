<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.azienda.progettoCatalogoProdotto.model.Acquisto"%>
<%@page import="com.azienda.progettoCatalogoProdotto.model.Ordine"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Storico Ordini</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resource/img/logo-sito.png"  type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/storicoOrdini.css">
</head>
<body>
    <div class="allineamento">
        <jsp:include page="/jsp/navbar.jsp"/>

        <div class="container">
            <h1>Storico Ordini</h1>

            <%	
                List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");

                if (ordini == null || ordini.isEmpty()) {
            %>
                <p class="alert alert-warning">Nessun ordine trovato.</p>
            <%
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    int index = 0;
                    for (Ordine o : ordini) {
                        String dataFormattata = o.getData_ordine().format(formatter);
                        double totale = 0;
                        for (Acquisto a : o.getAcquisti()) {
                            totale += a.getPrezzoProdotto() * a.getQuantita();
                        }
            %>
                <div class="order-card">
                    <div class="order-header">
                        <button class="toggle-button" onclick="toggleDetails('<%= index %>')">&#x25BC;</button>
                        Ordine effettuato il <%= dataFormattata %>
                        <span class="order-total">Totale: <%= String.format("%.2f", totale) %> €</span>
                    </div>

                    <div class="product-list" id="details-<%= index %>" style="display: none;">
                        <% for (Acquisto a : o.getAcquisti()) { %>
                            <div class="product-card">
                                <p><strong><%= a.getNomeProdotto() %></strong></p>
                                <p>Prezzo Unitario: <%= String.format("%.2f", a.getPrezzoProdotto()) %> €</p>
                                <p>Quantità: <%= a.getQuantita() %></p>
                            </div>
                        <% } %>
                    </div>
                </div>
            <%
                        index++;
                    }
                }
            %>
        </div>
    </div>

<jsp:include page="/jsp/footer.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/darkMode.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/detailsProductOrders.js"></script>
</body>
</html>

<%@page import="com.azienda.progettoCatalogoProdotto.model.Prodotto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Elimina Prodotti</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resource/img/logo-sito.png"  type="image/x-icon">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/deleteProdotto.css">
</head>
<body>
<jsp:include page="/jsp/navbar.jsp"></jsp:include>
<div class="profilo-container">
    <h1>Elimina Prodotti</h1>
    <% String successDeleteProduct = (String) request.getAttribute("messaggioSuccess");
        if (successDeleteProduct != null) { %>
            <p class="success mt-3"><%= successDeleteProduct %></p>
    <% } %>
    <%
    int appoggioIdProdotto=-99;
    List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("listaProdotti");
    Map<Integer, String> immagini = (Map<Integer, String>) request.getAttribute("chiaveImmagini");
    if (prodotti == null || prodotti.isEmpty()) {
    %>
    <div class="vuoto">
		<p class="alert alert-warning">Nessun prodotto disponibile.</p>
	</div>
    <%
    } else {
    %>
        <!-- <form action="<%= request.getContextPath() + "/DeleteProductServlet" %>" method="post"> -->
        <div class="product-container">
        
        <%
        for (Prodotto p : prodotti) {
        	String urlImmagine = immagini.get(p.getId());
        	appoggioIdProdotto = p.getId();
        %>
        
        <div class="card">
			 <div class="card-body">
			 	<img src="<%=urlImmagine%>" class="card-img-top" alt="Immagine prodotto">		
			    <h5 class="card-title"><%=p.getNome()%></h5>		
			    <p class="card-text">Disponibilità: <%=p.getDisponibilita()%></p>		    
			    <p class="card-text">Prezzo: €<%=p.getPrezzo()%></p>	
			    <button type="button" class="btn btn-danger mt-3" data-bs-toggle="modal" data-bs-target="#removeModal"><p>&#128473</p>
				</button>
			 </div>
		</div>
         
        <%
        }
        %>
        </div>

        
       
    <%
    }
    %>
</div>

<!-- MODALE CONFERMA -->
<div class="modal fade" id="removeModal" tabindex="-1" aria-labelledby="confermaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Rimuovi Prodotto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Chiudi"></button>
            </div>
            <div class="modal-body">
                Sei sicuro di voler eliminare il prodotto?
            </div>
            <div class="modal-footer">
                <!-- Pulsante SI: invia il form -->
                <form action="<%=request.getContextPath()%>/DeleteProductServlet" method="post">
                    <button type="submit" class="btn btn-primary" id="idProdotto" name="idProdotto" value="<%=appoggioIdProdotto%>">Sì</button>
                </form>
                 <!-- Pulsante NO -->
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





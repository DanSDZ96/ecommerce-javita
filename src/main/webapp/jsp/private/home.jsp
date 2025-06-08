<%@page import="java.util.Map"%>
<%@page import="com.azienda.progettoCatalogoProdotto.model.Prodotto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
<link rel="icon" href="<%=request.getContextPath()%>/resource/img/logo-sito.png"  type="image/x-icon">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/home.css">
</head>
<body>
<div class="allineamento">
	<jsp:include page="/jsp/navbar.jsp"/>
	
	<%
    String ordine = request.getParameter("ordine");
    if (ordine == null) {
        ordine = "titolo";
    }
    %>
    <div class="header-row container mt-3 mb-4">
        <h1>Catalogo prodotti</h1>
        <div class="subnavbar-dropdown">
            <label for="ordineSelect" class="form-label me-2">Ordina per:</label>
            <select id="ordineSelect" class="form-select d-inline-block w-auto" onchange="location = this.value;">
                <option value="?ordine=nome"    <%= "nome".equals(ordine) ? "selected" : "" %>>Nome: crescente</option>
                <option value="?ordine=nomedec"    <%= "nomedec".equals(ordine) ? "selected" : "" %>>Nome: decrescente</option>
                <option value="?ordine=prezzo"    <%= "prezzo".equals(ordine) ? "selected" : "" %>>Prezzo: crescente</option>
                <option value="?ordine=prezzodec"    <%= "prezzodec".equals(ordine) ? "selected" : "" %>>Prezzo: decrescente</option>
                <option value="?ordine=dispon"    <%= "dispon".equals(ordine) ? "selected" : "" %>>Disponibilità: crescente</option>
                <option value="?ordine=dispondec"    <%= "dispondec".equals(ordine) ? "selected" : "" %>>Disponibilità: decrescente</option>
            </select>
        </div>
    </div>
	
	<% 
	String erroreQuantitaProdotto = (String) request.getAttribute("erroreQuantita");
	if (erroreQuantitaProdotto != null) { %>
		<p class="error"><%= erroreQuantitaProdotto %></p>
	<% }
	List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("listaProdotti");
	Map<Integer, String> immagini = (Map<Integer, String>) request.getAttribute("chiaveImmagini");
	
	if(prodotti == null || prodotti.isEmpty()) {
	%>
	    <p class="alert alert-warning">Nessun prodotto trovato.</p>
	<% } else { %>
		<div class="product-container"> 
		<%	
	    for(Prodotto p : prodotti){ 
			String urlImmagine = immagini.get(p.getId());
		%>
	        <div class="card">
			  <img src="<%=urlImmagine%>" class="card-img-top" alt="...">	
			  <div class="card-body">		
			    <h5 class="card-title"><%=p.getNome()%></h5>		
			    <p class="card-text">Disponibilità: <%=p.getDisponibilita()%></p>		    
			    <p class="card-text">Prezzo: €<%=String.format("%.2f",p.getPrezzo())%></p>			
			    <form action="<%=request.getContextPath()%>/CarrelloServlet" method="post">
				    <input type="hidden" name="prodottoId" value="<%=p.getId()%>">
				    <%if(p.getDisponibilita()!=0) { %>
				    	<input type="number" name="quantita" value=1 min=1 max="<%=p.getDisponibilita()%>">
						<button type="submit" class="btn btn-primary mt-2">Acquista</button>
						<%} else { %>
						<button type="button" class="btn btn-danger mt-2" disabled>Non disponibile</button>
					<%} %>
				</form>		
			  </div>
			</div>
	<%  } 
	} %>
	</div>
</div>

<jsp:include page="/jsp/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/darkMode.js"></script>
</body>
</html>

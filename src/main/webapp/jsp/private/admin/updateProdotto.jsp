<%@page import="com.azienda.progettoCatalogoProdotto.model.Prodotto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Modifica Prodotti</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resource/img/logo-sito.png"  type="image/x-icon">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/updateProdotto.css">
</head>
<body>
<jsp:include page="/jsp/navbar.jsp"></jsp:include>
<div class="allineamento">
    <h1>Modifica Prodotti</h1>    
     <%
    Prodotto prodotto = (Prodotto) request.getAttribute("prodottoSelezionato");
    if (prodotto != null) {
    %>
    <div class="modifica-container container mt-5">
	    <div class="update-wrapper">
	        <h2 class="mt-3">Prodotto selezionato:</h2>
	        <h4 class="mt-3"><%= prodotto.getNome() %></h4>
	            <div class="card-body">
                    <p class="card-text">Disponibilità: <%=prodotto.getDisponibilita()%></p>
                    <p class="card-text">Prezzo: €<%=prodotto.getPrezzo()%></p>
                </div>
	    </div>	
	    <div class="update-wrapper">
	        <h2 class="mt-3">Modifica Prodotto:</h2>
	        <h4 class="mt-3"><%= prodotto.getNome() %></h4>
	        <form action="<%=request.getContextPath()%>/ConfermaUpdateProductServlet" method="post">
	            <input type="hidden" name="id" value="<%= prodotto.getId() %>"/>
	            <div class="mb-3">
	                <label>Nome:</label>
	                <input type="text" class="form-control" name="nome" value="<%= prodotto.getNome() %>" required />
	            </div>
	            <div class="mb-3">
	                <label>Prezzo (€):</label>
	                <input type="number" step="0.01" class="form-control" name="prezzo" value="<%= prodotto.getPrezzo() %>" required />
	            </div>
	            <div class="mb-3">
	                <label>Disponibilità:</label>
	                <input type="number" class="form-control" name="disponibilita" min=0 value="<%= prodotto.getDisponibilita() %>" required />
	            </div>
	            <button type="submit" class="btn btn-primary">Conferma Modifica</button>
	        </form>
	    </div>
	</div>
    <%
    }
    %>
	
	    

    <%
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
		    <form action="<%= request.getContextPath() + "/UpdateProductServlet" %>" method="post">
		        <div class="product-container">
		            <%
		                for (Prodotto p : prodotti) {
		                    String urlImmagine = immagini.get(p.getId());
		            %>
		                <div class="card">
		                    <img src="<%=urlImmagine%>" class="card-img-top" alt="Immagine prodotto">
		                    <div class="card-body">
		                        <h5 class="card-title"><%=p.getNome()%></h5>
		                        <p class="card-text">Disponibilità: <%=p.getDisponibilita()%></p>
		                        <p class="card-text">Prezzo: €<%=p.getPrezzo()%></p>
		                        <button type="submit" name="idProdotto" value="<%=p.getId()%>" class="btn btn-primary mt-2">Modifica</button>
		                    </div>
		                </div>
		            <%
		                }
		            %>
		        </div>
		    </form>
   		<%
    	}%>
</div>
   

   

<jsp:include page="/jsp/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/darkMode.js"></script>
</body>
</html>

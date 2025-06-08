<%@page import="com.azienda.progettoCatalogoProdotto.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Profilo</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resource/img/logo-sito.png"  type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/profilo.css">
</head>
<body>
<% 
    Utente user = (Utente)session.getAttribute("utente"); 
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
        return;
    }
%>
<jsp:include page="/jsp/navbar.jsp"></jsp:include>
	
<div class="profilo-container">
    <% String acquistoOK = (String) request.getAttribute("messaggioSuccess");
	if (acquistoOK != null) { %>
		<p class="error"><%= acquistoOK %></p>
	<% } %>
	
    <h1>Ciao <%=user.getUsername() %>, cosa vuoi fare?</h1>
       
    <div class="card-container">
        <div class="action-card">
            <img src="<%=request.getContextPath()%>/resource/img/passChange.png" alt="Cambia Password">
            <h2>Cambia Password</h2>
            <a href="<%=request.getContextPath()%>/jsp/private/changePassword.jsp" class="btn-primary">Vai</a>
        </div>
	
        <div class="action-card">
            <img src="<%=request.getContextPath()%>/resource/img/cart.png" alt="Visualizza Carrello">
            <h2>Il tuo Carrello</h2>
            <a href="<%=request.getContextPath()%>/CarrelloServlet" class="btn-primary">Vai</a>
        </div>

        <div class="action-card">
            <img src="<%=request.getContextPath()%>/resource/img/orders.png" alt="Storico Ordini">
            <h2>Storico Ordini</h2>
            <a href="<%=request.getContextPath()%>/StoricoOrdiniServlet" class="btn-primary">Vai</a>
        </div>
    </div>
	
	
	<% if (user.getRuolo().getNome().equals("ADMIN")) { %>
        <div class="card-container">
		    <div class="action-card">
		        <img src="<%=request.getContextPath()%>/resource/img/addProduct.png" alt="Aggiungi Prodotto">
		        <h2>Aggiungi Prodotto</h2>
		        <a href="<%= request.getContextPath() %>/CategorieServlet" class="btn-primary">Vai</a>
		    </div>
		
		    <div class="action-card">
		        <img src="<%=request.getContextPath()%>/resource/img/editProduct.png" alt="Modifica Prodotti">
		        <h2>Modifica Prodotti</h2>
		        <a href="<%= request.getContextPath() %>/UpdateProductServlet" class="btn-primary">Vai</a>
		    </div>
		
		    <div class="action-card">
		        <img src="<%=request.getContextPath()%>/resource/img/deleteProduct.png" alt="Elimina Prodotto">
		        <h2>Elimina Prodotto</h2>
		        <a href="<%= request.getContextPath() %>/DeleteProductServlet" class="btn-primary">Vai</a>
		    </div>
		
		    <div class="action-card">
		        <img src="<%=request.getContextPath()%>/resource/img/users.png" alt="Visualizza Utenti">
		        <h2>Utenti Registrati</h2>
		        <a href="<%= request.getContextPath() %>/ListUserServlet" class="btn-primary">Vai</a>
		    </div>
		</div>

    <% } %>
</div>

<jsp:include page="/jsp/footer.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/darkMode.js"></script>
</body>
</html>


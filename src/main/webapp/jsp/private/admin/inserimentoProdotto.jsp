<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.azienda.progettoCatalogoProdotto.model.Categoria" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Inserimento Prodotto</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resource/img/logo-sito.png"  type="image/x-icon">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/inserimentoProdotto.css">
</head>
<body>
<jsp:include page="/jsp/navbar.jsp"></jsp:include>
<div class="form-wrapper">
	<h2>Inserisci Nuovo Prodotto</h2>
	<% String successInsertProduct = (String) request.getAttribute("messaggioSuccess");
			if (successInsertProduct != null) { %>
			<p class="success"><%= successInsertProduct %></p>
		<% } %>
		
		<% String erroreInsertProduct = (String) request.getAttribute("chiaveErrore");
			if (erroreInsertProduct != null) { %>
			<p class="error"><%= erroreInsertProduct %></p>
		<% } %>
	<form action="<%= request.getContextPath() + "/InserisciProdottiServlet" %>" method="post" enctype="multipart/form-data">
	    <label>Nome Prodotto:</label><br>
	    <input type="text" name="nome" required><br><br>
	    
	    <label>Prezzo (€):</label><br>
	    <input type="number" name="prezzo" step="0.01" required><br><br>
	    
	    <label>Disponibilità:</label><br>
	    <input type="number" name="disponibilita" min=0 max=999 required><br><br>
	    
	    <label>Categoria:</label><br>
	    <select name="categoriaId" required>
	        <option value="">-- Seleziona categoria --</option>
	        <%
	            List<Categoria> categorie = (List<Categoria>) request.getAttribute("categorieDisponibili");
	            if (categorie != null) {
	                for (Categoria c : categorie) {
	        %>
	                    <option value="<%= c.getId() %>"><%= c.getNome() %></option>
	        <%
	                }
	            }
	        %>
	    </select><br><br>
	    
	    <label>Nome Immagine:</label><br>
	    <input type="text" name="nomeImmagine" required><br><br>
	
	    <label>File Immagine:</label><br>
	    <input type="file" name="file" required><br><br>
	    
	    <input type="submit" value="Inserisci Prodotto">
	    
		
		
	</form>
</div>
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/darkMode.js"></script>
</body>	
</html>

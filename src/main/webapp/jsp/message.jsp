<%@page import="com.azienda.progettoCatalogoProdotto.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Errore Interno</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/message.css">
</head>
<body>

<div class="allineamento">
   		<% Utente user = (Utente)session.getAttribute("utente");
			if(user != null) { %> 
				<jsp:include page="/jsp/navbar.jsp"/>
			<% } else { %>
				<jsp:include page="/jsp/navbarPublic.jsp"/>
			<% } %>

    <div class="container mt-5 text-center">
        <h1>Errore Interno del Server</h1>
        <p>Ci scusiamo per l'inconveniente. Qualcosa è andato storto.</p>
        <p>Ti invitiamo a riprovare più tardi o tornare alla home.</p>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/darkMode.js"></script>
</body>
</html>

<%@page import="com.azienda.progettoCatalogoProdotto.model.Utente"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chi Siamo - NovaCart</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resource/img/logo-sito.png"  type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">    
</head>
<body>
		<% Utente user = (Utente)session.getAttribute("utente");
			if(user != null) { %> 
				<jsp:include page="/jsp/navbar.jsp"/>
			<% } else { %>
				<jsp:include page="/jsp/navbarPublic.jsp"/>
			<% } %>
    

    <div class="container about-container text-center">
        <img src="<%=request.getContextPath()%>/resource/img/logo-sito.png" alt="NovaCart Logo" style="width: 300px; height: 300px">

        <h1 class="mb-4">Chi Siamo</h1>

        <div class="about-text text-start">
            <p>
                Nato dall’incontro tra tecnologia e semplicità, <strong>NovaCart</strong> è un e-commerce che ha l’obiettivo di rivoluzionare il modo in cui le persone fanno acquisti online.
                Il nome unisce <em>“Nova”</em> – che in latino significa “nuovo”, ma richiama anche le esplosioni stellari che generano nuova luce – e <em>“Cart”</em>, il simbolo universale dello shopping digitale.
            </p>

            <p>
                Lanciato con l’idea di creare un'esperienza d'acquisto intelligente, veloce e sostenibile, NovaCart si propone come una piattaforma modulare:
                capace di adattarsi alle esigenze del cliente e di integrare prodotti di qualità da settori diversi, sempre curati con attenzione e tecnologia all’avanguardia.
            </p>

            <h3 class="mt-4">Vision</h3>
            <p>
                Costruire un ecosistema e-commerce che non sia solo un luogo dove acquistare, ma un'esperienza coinvolgente, personalizzata e orientata al futuro.
            </p>
        </div>
    </div>

<jsp:include page="/jsp/footer.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/darkMode.js"></script>
</body>
</html>

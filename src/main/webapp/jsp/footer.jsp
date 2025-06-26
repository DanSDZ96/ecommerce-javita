<%@page import="com.azienda.progettoCatalogoProdotto.model.Utente"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/footer.css">

<div class="footer-custom mt-5">
    <div class="footer-top">
        <div class="footer-section footer-left">
            <p><strong>Sede Legale:</strong> Piazza Schiano, 23 - 80124 Napoli (NA)</p>
            <p><strong>Partita IVA:</strong> 11122233344</p>
        </div>

        <div class="footer-section footer-center">
            <p><strong>Sede:</strong> +39 081 1234567</p>
            <p><strong>Customer:</strong> +39 06 8901234</p>
            <p><strong>Amm.:</strong> +39 02 9876543</p>
        </div>

        <div class="footer-section footer-right">
            <p><a href="<%=request.getContextPath()%>/jsp/aboutUs.jsp">Chi siamo</a></p>
            <% 
            Utente utente = (Utente) session.getAttribute("utente");
    		if (utente != null) { %>
        		<p><a href="<%=request.getContextPath()%>/StoricoOrdiniServlet">I miei ordini</a></p>
    	<% 	} %>
            
            
            <div class="social-icons">
                <a href="https://facebook.com/" target="_blank"><img src="<%=request.getContextPath()%>/resource/img/facebook.png" alt="Facebook" width="24"></a>
                <a href="https://instagram.com/" target="_blank"><img src="<%=request.getContextPath()%>/resource/img/instagram.png" alt="Instagram" width="24"></a>
                <a href="https://x.com/" target="_blank"><img src="<%=request.getContextPath()%>/resource/img/twitter.png" alt="X" width="24"></a>
            </div>
        </div>
    </div>

    <div class="footer-bottom">
        <p>&copy; <%= 2025 %> Tutti i diritti riservati.</p>
        <p>Daniel Schiano di Zenise</p>
    </div>
</div>

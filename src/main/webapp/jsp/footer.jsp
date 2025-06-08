<%@page import="com.azienda.progettoCatalogoProdotto.model.Utente"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/footer.css">

<div class="footer-custom mt-5">
    <div class="footer-top">
        <div class="footer-section footer-left">
            <p><strong>Sede Legale:</strong> Via delle Innovazioni, 42 - 00100 Roma (RM)</p>
            <p><strong>Partita IVA:</strong> 12345678901</p>
        </div>

        <div class="footer-section footer-center">
            <p><strong>Sede:</strong> +39 06 1234567</p>
            <p><strong>Customer:</strong> +39 06 7654321</p>
            <p><strong>Amm.:</strong> +39 06 9876543</p>
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
        <p>&copy; <%= java.time.Year.now() %> Tutti i diritti riservati.</p>
        <p>Team: Roberto Corona, Francesco Di Iorio, Gianluca Fabiani, Luca Bassanetti, Daniel Schiano di Zenise</p>
    </div>
</div>

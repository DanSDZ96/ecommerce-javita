<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.azienda.progettoCatalogoProdotto.model.Utente" %>
<%
    Utente utente = (Utente) session.getAttribute("utente");
    if (utente == null) {
        response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cambia Password</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resource/img/logo-sito.png"  type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/changePassword.css">
</head>
<body>
<jsp:include page="/jsp/navbar.jsp" />

<div class="form-wrapper">
    <h2>Cambia Password</h2>
    <form action="<%=request.getContextPath()%>/CambiaPassword" method="post">
        <label>Vecchia Password:</label>
        <div class="field" style="position: relative;">
			  <input type="password" name="vecchiaPassword" placeholder="Password" id="vecchiaPassword" required>
		  	  <span class="toggle-password" onclick="togglePasswordRegister(this)">
				    <img class="show-icon" src="<%=request.getContextPath()%>/resource/img/show.png" alt="Mostra password">
				    <img class="hide-icon" src="<%=request.getContextPath()%>/resource/img/hide.png" alt="Nascondi password" style=" display: none;">
			  </span>
		</div>

        <label>Nuova Password:</label>
        <div class="field" style="position: relative;">
			  <input type="password" name="nuovaPassword" placeholder="Password" id="nuovaPassword" required>
		  	  <span class="toggle-password" onclick="togglePasswordRegister(this)">
				    <img class="show-icon" src="<%=request.getContextPath()%>/resource/img/show.png" alt="Mostra password">
				    <img class="hide-icon" src="<%=request.getContextPath()%>/resource/img/hide.png" alt="Nascondi password" style=" display: none;">
			  </span>
		</div>

        <label>Conferma Nuova Password:</label>
        <div class="field" style="position: relative;">
			  <input type="password" name="confermaPassword" placeholder="Password" id="confermaPassword" required>
		  	  <span class="toggle-password" onclick="togglePasswordRegister(this)">
				    <img class="show-icon" src="<%=request.getContextPath()%>/resource/img/show.png" alt="Mostra password">
				    <img class="hide-icon" src="<%=request.getContextPath()%>/resource/img/hide.png" alt="Nascondi password" style=" display: none;">
			  </span>
		</div>

        <input type="submit" value="Aggiorna">
        
        <% String errore = (String) request.getAttribute("erroreCambio");
	       if (errore != null) { %>
	       <p class="error"><%= errore %></p>
	    <% } %>
	
	    <% String success = (String) request.getAttribute("successCambio");
	       if (success != null) { %>
	       <p class="success"><%= success %></p>
	    <% } %>
    </form>

    
</div>

<jsp:include page="/jsp/footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/darkMode.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/showPassword.js"></script>
</body>
</html>


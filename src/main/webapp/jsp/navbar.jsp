<%
String baseUrl = "http://" + request.getServerName() + ":" +
request.getServerPort() +
request.getContextPath();
String home = baseUrl + "/jsp/private/home.jsp";
String profilo = baseUrl + "/jsp/private/profilo.jsp";
String carrello = baseUrl + "/CarrelloServlet";
String logout = baseUrl + "/Logout";

%>
<%@page import="com.azienda.progettoCatalogoProdotto.model.Utente"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/navbar.css">

<nav class="navbar-custom">
    <div class="nav-left">
        <a href="<%=request.getContextPath()%>/ProdottiServlet" class="nav-link">Home</a>
    </div>

    <div class="nav-center search-container">
        <form action="<%=request.getContextPath()%>/SearchServlet" method="get">
            <input type="search" name="keywords" placeholder="Cerca...">
            <button type="submit"><img src="<%=request.getContextPath()%>/resource/img/search.png" alt="Cerca"></button>
        </form>
    </div>

    <div class="nav-right">
        <% Utente user = (Utente)session.getAttribute("utente"); %>
        <a href="<%= profilo %>" class="nav-link">Ciao <%=user.getUsername() %> <img src="<%=request.getContextPath()%>/resource/img/user2.png" alt="Profilo"></a>
        <a href="<%= carrello %>" class="nav-link">Carrello <img src="<%=request.getContextPath()%>/resource/img/cart-white.png"></a>
        <a href="<%= logout %>" class="nav-link">Logout <img src="<%=request.getContextPath()%>/resource/img/logout.png"></a>
        <label class="theme-switch">
		  <input type="checkbox" id="toggleThemeCheckbox">
		  <span class="slider">
		    <span class="icon sun">&#x263C</span>
		    <span class="icon moon">&#x263E</span>
		  </span>
		</label>
    </div>
</nav>
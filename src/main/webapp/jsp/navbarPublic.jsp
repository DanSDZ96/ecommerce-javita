<%
String baseUrl = "http://" + request.getServerName() + ":" +
request.getServerPort() +
request.getContextPath();
String accedi_login = baseUrl + "/jsp/index.jsp";

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
        <a href="<%= accedi_login %>" class="nav-link">Accedi</a>
        <label class="theme-switch">
		  <input type="checkbox" id="toggleThemeCheckbox">
		  <span class="slider">
		    <span class="icon sun">&#x263C</span>
		    <span class="icon moon">&#x263E</span>
		  </span>
		</label>
    </div>
</nav>
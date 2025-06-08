<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.azienda.progettoCatalogoProdotto.model.Utente" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Utenti Registrati</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resource/img/logo-sito.png"  type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/listUsers.css">
</head>
<body>
<jsp:include page="/jsp/navbar.jsp"></jsp:include>
<div class="user-wrapper allineamento">
    <h1>Lista Utenti Registrati</h1>

    <%
        List<Utente> utenti = (List<Utente>) request.getAttribute("listaUtenti");
        if (utenti == null || utenti.isEmpty()) {
    %>
        <p class="text-center">Nessun utente registrato.</p>
    <%
        } else {
    %>
        <div class="table-responsive">
            <table class="table table-striped table-hover align-middle text-center">
                <thead class="table-primary">
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Ruolo</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Utente u : utenti) { %>
                    <tr>
                        <td><%= u.getUsername() %></td>
                        <td><%= u.getEmail() %></td>
                        <td><%= u.getRuolo() != null ? u.getRuolo().getNome() : "N/D" %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    <%
        }
    %>
</div>
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/darkMode.js"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Accedi o Registrati</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resource/img/logo-sito.png"  type="image/x-icon">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/index.css">
</head>
<body data-form-selezionato="<%= request.getAttribute("formSelezionato") %>">
	<div class="wrapper">
		<div class="title-text">
			<div class="title login">Login</div>
			<div class="title signup">Registrazione</div>
		</div>
		<div class="form-container">
			<div class="slide-controls">
				<input type="radio" name="slide" id="login" checked>
				<input type="radio" name="slide" id="signup">
				<label for="login" class="slide login">Login</label>
				<label for="signup" class="slide signup">Registrazione</label>
				<div class="slider-tab"></div>
			</div>
			<div class="form-inner">

				<!-- Form LOGIN -->
				<form action="<%=request.getContextPath()%>/Login" method="POST" class="login">
					
					<div class="field">
						<input type="email" name="emailFormInput" placeholder="E-mail" required>
					</div>
					<div class="field" style="position: relative;">
						  <input type="password" name="pswFormInput" placeholder="Password" id="myInput" required style="padding-right: 40px;">
					  	  <span class="toggle-password" onclick="togglePassword()">
							    <img class="show-icon" src="<%=request.getContextPath()%>/resource/img/show.png" alt="Mostra password">
							    <img class="hide-icon" src="<%=request.getContextPath()%>/resource/img/hide.png" alt="Nascondi password" style=" display: none;">
						  </span>
					</div>

					<% String erroreLogin = (String) request.getAttribute("erroreLogin");
					if (erroreLogin != null) { %>
						<p class="error"><%= erroreLogin %></p>
					<% } %>
					
					<% String registrazioneOK = (String) request.getAttribute("RegistrazioneOK");
					if (registrazioneOK != null) { %>
						<p class="success"><%= registrazioneOK %></p>
					<% } %>
					
					<div class="field btn">
						<div class="btn-layer"></div>
						<input type="submit" value="Accedi">
					</div>
				</form>

				<!-- Form REGISTRAZIONE -->
				<form action="<%=request.getContextPath()%>/Registrazione" method="POST" class="signup">
					<div class="field">
						<input type="text" name="usernameFormInput" placeholder="Username" required>
					</div>
					<div class="field">
						<input type="email" name="emailFormInput" placeholder="E-mail" required>
					</div>
					<div class="field" style="position: relative;">
					  <input type="password" id="registerPassword" name="pswFormInput" placeholder="Password" required style="padding-right: 40px;">
					  <span class="toggle-password" onclick="togglePasswordRegister(this)">
					    <img class="show-icon" src="<%=request.getContextPath()%>/resource/img/show.png" alt="Mostra password">
					    <img class="hide-icon" src="<%=request.getContextPath()%>/resource/img/hide.png" alt="Nascondi password" style="display: none;">
					  </span>
					</div>
					
					<div class="field" style="position: relative;">
					  <input type="password" id="registerConfirmPassword" name="confPswFormInput" placeholder="Conferma Password" required style="padding-right: 40px;">
					  <span class="toggle-password" onclick="togglePasswordRegister(this)">
					    <img class="show-icon" src="<%=request.getContextPath()%>/resource/img/show.png" alt="Mostra password">
					    <img class="hide-icon" src="<%=request.getContextPath()%>/resource/img/hide.png" alt="Nascondi password" style="display: none;">
					  </span>
					</div>


					<% String erroreRegistrazione = (String) request.getAttribute("erroreRegistrazione");
					if (erroreRegistrazione != null) { %>
						<p class="error"><%= erroreRegistrazione %></p>
					<% } %>
					
					<div class="field btn">
						<div class="btn-layer"></div>
						<input type="submit" value="Registrati">
					</div>
				</form>

			</div>
		</div>
	</div>
	
<jsp:include page="/jsp/footer.jsp" />
<script src="<%=request.getContextPath()%>/resource/js/accesso.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/showPassword.js"></script>
</body>
</html>

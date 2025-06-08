package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.azienda.progettoCatalogoProdotto.exception.AlreadyExistingUtenteException;
import com.azienda.progettoCatalogoProdotto.exception.PasswordErrataException;
import com.azienda.progettoCatalogoProdotto.exception.RequiredFieldException;
import com.azienda.progettoCatalogoProdotto.exception.UtenteNonTrovatoException;
import com.azienda.progettoCatalogoProdotto.model.Utente;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String check = request.getParameter("success");
		System.out.println(check);
		if(check != null) {
			request.setAttribute("RegistrazioneOK", "Registrato con successo!");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}
		response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("emailFormInput");
		String password = request.getParameter("pswFormInput");
		
		BusinessLogic bl = (BusinessLogic)getServletContext().getAttribute("chiaveBl");
		Utente user;
		try {
			user = bl.login(email,password);
			if(user!=null) {
				//SE VA TUTTO BENE
				HttpSession session = request.getSession();
			    session.setAttribute("utente", user);
			    response.sendRedirect(request.getContextPath() + "/RuoloServlet");
			    System.out.println("Session ID: " + session.getId());
			    System.out.println("Utente loggato: " + user.getEmail());
			    return;
			}
		}catch(UtenteNonTrovatoException | PasswordErrataException e) {
			//ERRORE UTENTE NON TROVATO
			request.setAttribute("erroreLogin", e.getMessage());
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			return;
		}catch(Exception e) {
			//ERRORE GENERICO
			request.setAttribute("erroreLogin", "Errore, contattare il supporto.");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			return;
		}
	}
}

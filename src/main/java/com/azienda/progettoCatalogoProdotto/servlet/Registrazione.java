package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.azienda.progettoCatalogoProdotto.exception.AlreadyExistingUtenteException;
import com.azienda.progettoCatalogoProdotto.exception.RequiredFieldException;
import com.azienda.progettoCatalogoProdotto.model.Utente;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;


@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("usernameFormInput");
		String email = request.getParameter("emailFormInput");
		String password = request.getParameter("pswFormInput");
		String password2 = request.getParameter("confPswFormInput");
		
		//Verifica che password non sia già stata inserita (o vuota di nuovo)
		if(!password.equals(password2) || password.isBlank()) {
			request.setAttribute("erroreRegistrazione", "✘ Le password devono essere uguali.");
			request.setAttribute("formSelezionato", "signup");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			return;
		}
		
		//Verifica per maiuscole, minuscole, numero e carattere speciale
		boolean haMaiuscole = false;
		boolean haMinuscole = false;
		boolean haNumero = false;
		boolean haCarSpeciale = false;
		for (char c : password.toCharArray()) {
	        if (Character.isUpperCase(c)) {
	        	haMaiuscole = true;
	        }
	        else if(Character.isLowerCase(c)) {
	        	haMinuscole = true;
	        }
	        else if(!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
	        	haCarSpeciale = true;
	        }
	        else if(Character.isDigit(c)) {
	        	haNumero = true;
	        }	        	        
	    }
		//Messaggio errore maiuscola mancante
		if(!haMaiuscole) {
			request.setAttribute("erroreRegistrazione", "✘ La password deve contenere almeno una lettera maiuscola");
			request.setAttribute("formSelezionato", "signup");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			return;
		}
		
		//Messaggio errore minuscola mancante       	
		if(!haMinuscole) {
			request.setAttribute("erroreRegistrazione", "✘ La password deve contenere almeno una lettera miniscola");
			request.setAttribute("formSelezionato", "signup");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			return;
		}
		
		//Messaggio errore numero mancante        	
		if(!haNumero) {
			request.setAttribute("erroreRegistrazione", "✘ La password deve contenere almeno un numero");
			request.setAttribute("formSelezionato", "signup");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			return;
		}
				
		//Messaggio errore carattere speciale mancante       	
		if(!haCarSpeciale) {
			request.setAttribute("erroreRegistrazione", "✘ La password deve contenere almeno un carattere speciale");
			request.setAttribute("formSelezionato", "signup");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			return;
		}
		
		//Messaggio errore almeno 8 caratteri      	
		if(password.length()<8) {
			request.setAttribute("erroreRegistrazione", "✘ La password deve essere lunga almeno 8 caratteri");
			request.setAttribute("formSelezionato", "signup");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			return;
		}	
				
				
		
		Utente user = new Utente(username,email,password);
		BusinessLogic bl = (BusinessLogic)getServletContext().getAttribute("chiaveBl");
		try {
			user = bl.insertUser(user);
		}catch(RequiredFieldException | AlreadyExistingUtenteException e) {
			request.setAttribute("formSelezionato", "signup");
			request.setAttribute("erroreRegistrazione", e.getMessage());
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			return;
		}
		
		if(user!=null) {
			request.setAttribute("RegistrazioneOK", "Registrato con successo!");
			response.sendRedirect(request.getContextPath() + "/Login?success=1");
			return;
		}
	}

}

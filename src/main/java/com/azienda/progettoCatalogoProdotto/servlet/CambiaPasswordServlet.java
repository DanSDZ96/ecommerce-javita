package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.azienda.progettoCatalogoProdotto.model.Utente;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;

@WebServlet("/CambiaPassword")
public class CambiaPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect("/jsp/index.jsp");
            return;
        }

        BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute("chiaveBl");
        
        String vecchia = request.getParameter("vecchiaPassword");
        String nuova = request.getParameter("nuovaPassword");
        String conferma = request.getParameter("confermaPassword");
        
        
        //verifica che pass vecchia sia giusta
        if (!utente.getPassword().equals(vecchia)) {
            request.setAttribute("erroreCambio", "✘ La vecchia password non è corretta.");
            request.getRequestDispatcher("/jsp/private/changePassword.jsp").forward(request, response);
            return;
            }
        

        
        //Verifica che password non sia già stata inserita (o vuota di nuovo)
  		if(!nuova.equals(conferma) || nuova.isBlank()) {
  			request.setAttribute("erroreCambio", "✘ Le password devono essere uguali.");
  			request.getRequestDispatcher("/jsp/private/changePassword.jsp").forward(request, response);
  			return;
  		}
      		
  		//Verifica per maiuscole, minuscole, numero e carattere speciale
  		boolean haMaiuscole = false;
  		boolean haMinuscole = false;
  		boolean haNumero = false;
  		boolean haCarSpeciale = false;
  		for (char c : nuova.toCharArray()) {
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
  			request.setAttribute("erroreCambio", "✘ La password deve contenere almeno una lettera maiuscola");
  			request.getRequestDispatcher("/jsp/private/changePassword.jsp").forward(request, response);
  			return;
  		}
  		
  		//Messaggio errore minuscola mancante       	
  		if(!haMinuscole) {
  			request.setAttribute("erroreCambio", "✘ La password deve contenere almeno una lettera miniscola");
  			request.getRequestDispatcher("/jsp/private/changePassword.jsp").forward(request, response);
  			return;
  		}
  		
  		//Messaggio errore numero mancante        	
  		if(!haNumero) {
  			request.setAttribute("erroreCambio", "✘ La password deve contenere almeno un numero");
  			request.getRequestDispatcher("/jsp/private/changePassword.jsp").forward(request, response);
  			return;
  		}
  				
  		//Messaggio errore carattere speciale mancante       	
  		if(!haCarSpeciale) {
  			request.setAttribute("erroreCambio", "✘ La password deve contenere almeno un carattere speciale");
  			request.getRequestDispatcher("/jsp/private/changePassword.jsp").forward(request, response);
  			return;
  		}
  		
  		//Messaggio errore almeno 8 caratteri      	
  		if(nuova.length()<8) {
  			request.setAttribute("erroreCambio", "✘ La password deve essere lunga almeno 8 caratteri");
  			request.getRequestDispatcher("/jsp/private/changePassword.jsp").forward(request, response);
  			return;
  		}
        
        

        bl.aggiornaPassword(utente.getId(), nuova);
        utente.setPassword(nuova);
        session.setAttribute("utente", utente);
        request.setAttribute("successCambio", "Password aggiornata con successo.");

        request.getRequestDispatcher("/jsp/private/changePassword.jsp").forward(request, response);
    }
}


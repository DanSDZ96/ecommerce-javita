package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.metamodel.SetAttribute;

import com.azienda.progettoCatalogoProdotto.model.Acquisto;
import com.azienda.progettoCatalogoProdotto.model.Ordine;
import com.azienda.progettoCatalogoProdotto.model.Utente;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;

/**
 * Servlet implementation class StoricoOrdiniServlet
 */
@WebServlet("/StoricoOrdiniServlet")
public class StoricoOrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
            return;
        }
        BusinessLogic bl = (BusinessLogic)getServletContext().getAttribute("chiaveBl");
        List<Ordine> ordiniUtente = new ArrayList<Ordine>();
        if(utente.getRuolo().getNome().equals("ADMIN")) {
        	ordiniUtente = bl.getAllOrdini();
        }
        else {
        	ordiniUtente = bl.getOrdiniByIdUtente(utente);
        }
		
		for(Ordine ordine: ordiniUtente) {
			List<Acquisto> acquisti = bl.getAcquistiByOrdine(ordine);
			ordine.setAcquisti(acquisti);
		}
		
		request.setAttribute("ordini",ordiniUtente);
		
		if(utente.getRuolo().getNome().equals("ADMIN")) {
			List<Utente> utenti = bl.getAllUsers();
			request.setAttribute("utenti", utenti);
			request.getRequestDispatcher("/jsp/private/admin/storicoOrdiniAdmin.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/jsp/private/storicoOrdini.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

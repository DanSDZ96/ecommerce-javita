package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.azienda.progettoCatalogoProdotto.model.Carrello;
import com.azienda.progettoCatalogoProdotto.model.ProdottoNelCarrello;
import com.azienda.progettoCatalogoProdotto.model.Utente;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;

@WebServlet("/ConfermaOrdineServlet")
public class ConfermaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("/CarrelloServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
            return;
        }
        
		BusinessLogic bl = (BusinessLogic)getServletContext().getAttribute("chiaveBl");
		Carrello carrello = utente.getCarrello();
		boolean errorQuantity = false;
		for(ProdottoNelCarrello pnc: carrello.getProdottiNelCarrello()) {
			if(pnc.getQuantita() > pnc.getProdotto().getDisponibilita()) {
				errorQuantity = true;
				bl.deleteProductByIdFromCarrello(pnc.getId());
			}
		}
		
		if(!errorQuantity) {
			bl.confermaOrdine(utente);
			request.setAttribute("messaggioSuccess", "Prodotto/i acquistato/i con successo!");
			request.getRequestDispatcher("/StoricoOrdiniServlet").forward(request, response);
		} else {			
			request.setAttribute("messaggioErrore", "Prodotto/i non più disponibile/i");
			request.getRequestDispatcher("/CarrelloServlet").forward(request, response);
		}
		
	}
}

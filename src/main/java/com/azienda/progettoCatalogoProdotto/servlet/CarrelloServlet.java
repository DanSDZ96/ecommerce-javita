package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import com.azienda.progettoCatalogoProdotto.exception.ErrorDisponibilitaException;
import com.azienda.progettoCatalogoProdotto.model.Prodotto;
import com.azienda.progettoCatalogoProdotto.model.Utente;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;

@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
            return;
        }

        try {
            BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute("chiaveBl");

            // Recupera i prodotti nel carrello con quantità reali
            Map<Prodotto, Integer> prodottiCarrello = bl.getProdottiEQuantitaPerView(utente.getId());

            // DEBUG log
            System.out.println("Carrello di " + utente.getEmail() + ":");
            prodottiCarrello.forEach((p, q) -> System.out.println(" - " + p.getNome() + " x" + q));

            request.setAttribute("carrelloProdotti", prodottiCarrello);
            request.getRequestDispatcher("/jsp/private/carrello.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("chiaveErrore", "Errore nel caricamento del carrello.");
            response.sendRedirect(request.getContextPath() + "/jsp/message.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Ottieni ID del prodotto dalla richiesta
            String prodottoIdParam = request.getParameter("prodottoId");
            String quantitaParam = request.getParameter("quantita");
            
            
            if(request.getAttribute("messaggioErrore") != null) {
            	doGet(request, response);
            	return;
            }
            
            if (prodottoIdParam == null || prodottoIdParam.isBlank()) {
                throw new IllegalArgumentException("Parametro prodottoId mancante o vuoto");
            }
            
            if (quantitaParam == null) {
            	throw new IllegalArgumentException("Parametro quantita mancante o vuoto");
            }
            
            int prodottoId = Integer.parseInt(prodottoIdParam);
            int quantita = Integer.parseInt(quantitaParam);
            
            // Verifica utente in sessione
            HttpSession session = request.getSession();
            Utente utente = (Utente) session.getAttribute("utente");
            
            if (utente == null) {
                response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
                return;
            }

            // Business logic per aggiunta prodotto
            BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute("chiaveBl");
            bl.gestisciAggiuntaProdottoCarrello(utente.getId(), prodottoId, quantita);

            // Redirect alla visualizzazione del carrello
            response.sendRedirect(request.getContextPath() + "/CarrelloServlet");

        }catch(ErrorDisponibilitaException e) {
        	request.setAttribute("erroreQuantita", e.getMessage());
			request.getRequestDispatcher("/ProdottiServlet").forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("chiaveErrore", "Errore nel caricamento del carrello.");
            response.sendRedirect(request.getContextPath() + "/jsp/message.jsp");
        }
    }
}

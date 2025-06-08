package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import com.azienda.progettoCatalogoProdotto.model.Carrello;
import com.azienda.progettoCatalogoProdotto.model.Prodotto;
import com.azienda.progettoCatalogoProdotto.model.Utente;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;

@WebServlet("/SvuotaCarrelloServlet")
public class SvuotaCarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
            return;
        }

        BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute("chiaveBl");

        try {

            Carrello nuovoCarrello = bl.svuotaCarrelloUtente(utente.getId());

            
            utente.setCarrello(nuovoCarrello);
            session.setAttribute("utente", utente);

            Map<Prodotto, Integer> carrelloVuoto = bl.getProdottiEQuantitaPerView(utente.getId());
            request.setAttribute("carrelloProdotti", carrelloVuoto);

            request.getRequestDispatcher("/jsp/private/carrello.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("chiaveErrore", "Errore nello svuotare il carrello.");
            response.sendRedirect(request.getContextPath() + "/jsp/message.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

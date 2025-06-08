package com.azienda.progettoCatalogoProdotto.servlet;

import com.azienda.progettoCatalogoProdotto.model.Prodotto;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ConfermaUpdateProductServlet")
public class ConfermaUpdateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute("chiaveBl");

            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            int disponibilita = Integer.parseInt(request.getParameter("disponibilita"));

            Prodotto prodotto = bl.selectProductById(id);
            if (prodotto != null) {
                prodotto.setNome(nome);
                prodotto.setPrezzo(prezzo);
                prodotto.setDisponibilita(disponibilita);

                bl.updateProduct(prodotto);
                request.setAttribute("messaggioSuccess", "Prodotto aggiornato con successo!");
            } else {
                request.setAttribute("errore", "Prodotto non trovato.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore durante l'aggiornamento del prodotto.");
        }

        request.getRequestDispatcher("/UpdateProductServlet").forward(request, response);
    }
}


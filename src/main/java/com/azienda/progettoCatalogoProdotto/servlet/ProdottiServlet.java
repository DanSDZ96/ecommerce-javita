package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.azienda.progettoCatalogoProdotto.model.Prodotto;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;
import com.azienda.progettoCatalogoProdotto.utils.AggiungiImmagini;

@WebServlet("/ProdottiServlet")
public class ProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BusinessLogic bl = (BusinessLogic)getServletContext().getAttribute("chiaveBl");
			List<Prodotto> prodotti = bl.selectAllProdotto();
			request.setAttribute("listaProdotti", prodotti);
			Map<Integer, String> immagini = AggiungiImmagini.generaMappaImmagini(prodotti, request, getServletContext());
			request.setAttribute("chiaveImmagini", immagini);

			String ordine = request.getParameter("ordine");
			if (ordine == null || "nome".equals(ordine)) {
				prodotti.sort((o1, o2) -> o1.getNome().compareToIgnoreCase(o2.getNome()));
			} else if ("nomedec".equals(ordine)) {
				prodotti.sort((o2, o1) -> o1.getNome().compareTo(o2.getNome()));
			} else if ("prezzo".equals(ordine)) {
				prodotti.sort((o1, o2) -> o1.getPrezzo().compareTo(o2.getPrezzo()));
			} else if ("prezzodec".equals(ordine)) {
				prodotti.sort((o2, o1) -> o1.getPrezzo().compareTo(o2.getPrezzo()));
			} else if ("dispon".equals(ordine)) {
				prodotti.sort((o1, o2) -> o1.getDisponibilita().compareTo(o2.getDisponibilita()));
			} else if ("dispondec".equals(ordine)) {
				prodotti.sort((o2, o1) -> o1.getDisponibilita().compareTo(o2.getDisponibilita()));
			}
			
			request.getRequestDispatcher("/jsp/private/home.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("chiaveErrore", "Errore imprevisto");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
}
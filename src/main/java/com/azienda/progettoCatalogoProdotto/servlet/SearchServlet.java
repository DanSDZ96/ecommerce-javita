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

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String keywords = request.getParameter("keywords");
			BusinessLogic bl = (BusinessLogic)getServletContext().getAttribute("chiaveBl");

			List<Prodotto> risultati;

			if (keywords == null || keywords.isBlank()) {
				risultati = bl.selectAllProdotto();
			} else {
				risultati = bl.cercaProdottiByKeyword(keywords);
				if (risultati == null || risultati.isEmpty()) {
					risultati = bl.selectAllProdotto();
				}
			}

			request.setAttribute("listaProdotti", risultati);

			Map<Integer, String> immagini = AggiungiImmagini.generaMappaImmagini(risultati, request, getServletContext());
			request.setAttribute("chiaveImmagini", immagini);

			request.getRequestDispatcher("/jsp/private/home.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("chiaveErrore", "Errore nella ricerca dei prodotti");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

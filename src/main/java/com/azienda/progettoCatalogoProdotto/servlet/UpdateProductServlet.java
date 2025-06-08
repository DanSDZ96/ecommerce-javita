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

@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute("chiaveBl");
			List<Prodotto> prodotti = bl.selectAllProdotto();
			request.setAttribute("listaProdotti", prodotti);
			Map<Integer, String> immagini = AggiungiImmagini.generaMappaImmagini(prodotti, request, getServletContext());
			request.setAttribute("chiaveImmagini", immagini);
			
			String idProdotti = request.getParameter("idProdotto");

			if (idProdotti != null) {
			    int id = Integer.parseInt(idProdotti);
			    Prodotto prodotto = bl.selectProductById(id);
			    request.setAttribute("prodottoSelezionato", prodotto);
			}
			
			// Sempre ricaricare la lista prodotti
			request.setAttribute("listaProdotti", bl.selectAllProdotto());
			
			request.getRequestDispatcher("/jsp/private/admin/updateProdotto.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore durante la modifica");
			request.getRequestDispatcher("/jsp/private/admin/updateProdotto.jsp").forward(request, response);
		}
	}
}

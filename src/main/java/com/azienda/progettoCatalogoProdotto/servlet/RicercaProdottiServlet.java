package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.azienda.progettoCatalogoProdotto.model.Prodotto;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;
import com.azienda.progettoCatalogoProdotto.utils.AggiungiImmagini;
import com.azienda.progettoCatalogoProdotto.utils.BlobConverter;

@WebServlet("/RicercaProdottiServlet")
public class RicercaProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String filtro = request.getParameter("nome");
			BusinessLogic bl = (BusinessLogic)getServletContext().getAttribute("chiaveBl");
			List<Prodotto> risultati = bl.selectByName("%" + filtro + "%");
			Map<Integer, String> immagini = AggiungiImmagini.generaMappaImmagini(risultati, request, getServletContext());
			request.setAttribute("chiaveImmagini", immagini);
			request.getRequestDispatcher("/jsp/private/home.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("chiaveErrore", "Errore nella ricerca dei prodotti (immagini)");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

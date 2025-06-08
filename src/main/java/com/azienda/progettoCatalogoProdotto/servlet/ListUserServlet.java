package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.azienda.progettoCatalogoProdotto.model.Utente;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;

@WebServlet("/ListUserServlet")
public class ListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute("chiaveBl");
			List<Utente> utenti = bl.getAllUsers();
			//if (utenti != null) {
	            request.setAttribute("listaUtenti", utenti);
	            request.getRequestDispatcher("/jsp/private/admin/listUsers.jsp").forward(request, response);
	        //}
		} catch (Exception e) {
			e.printStackTrace();
            request.setAttribute("chiaveErrore", "Errore generico utenti");
            request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.azienda.progettoCatalogoProdotto.servlet;

import java.io.IOException;
import java.util.List;

import com.azienda.progettoCatalogoProdotto.model.Categoria;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CategorieServlet")
public class CategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute("chiaveBl");
            List<Categoria> categorie = bl.selectAllCategorie();
            request.setAttribute("categorieDisponibili", categorie);
            request.getRequestDispatcher("/jsp/private/admin/inserimentoProdotto.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("chiaveErrore", "Errore nel caricamento delle categorie.");
            request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
        }
    }
    
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doGet(request, response);
    }
}

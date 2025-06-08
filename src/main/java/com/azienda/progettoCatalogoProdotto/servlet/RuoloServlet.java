package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.azienda.progettoCatalogoProdotto.model.Utente;

@WebServlet("/RuoloServlet")
public class RuoloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RuoloServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utenteLogin = (Utente) session.getAttribute("utente");

        if (utenteLogin != null) {
            switch (utenteLogin.getRuolo().getNome()) {
                case "CUSTOMER":
                    response.sendRedirect(request.getContextPath() + "/ProdottiServlet");
                    break;
                case "ADMIN":
                    response.sendRedirect(request.getContextPath() + "/jsp/private/profilo.jsp");
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/jsp/access-denied.jsp");
                    break;
            }
        } else {
            response.sendRedirect("/jsp/access-denied.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
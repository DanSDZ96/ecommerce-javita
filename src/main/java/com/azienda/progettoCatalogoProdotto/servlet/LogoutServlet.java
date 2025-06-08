package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false); // Non crea una nuova sessione se non esiste
        if (session != null && session.getAttribute("utente") != null) {
            session.invalidate(); // Invalida la sessione corrente
            System.out.println("LOGOUT");
        }

        // Redirect alla home o alla pagina di login
        response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

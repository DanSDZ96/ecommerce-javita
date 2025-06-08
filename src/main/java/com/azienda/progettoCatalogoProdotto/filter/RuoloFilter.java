//package com.azienda.progettoCatalogoProdotto.filter;
//
//import jakarta.servlet.*;
//
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.*;
//
//import java.io.IOException;
//
//import com.azienda.progettoCatalogoProdotto.model.Utente;
//
//
//@WebFilter("/*")
//public class RuoloFilter implements Filter {
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        HttpSession session = req.getSession(false);
//
//        String uri = req.getRequestURI();
//        String context = req.getContextPath();
//
//        Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;
//        String ruolo = (utente != null && utente.getRuolo() != null) ? utente.getRuolo().getNome() : null;
//
//        boolean isRoot = uri.equals(context + "/") || uri.equals(context);
//
//        // JSP pubbliche
//        boolean isPublicPage = uri.endsWith("home.jsp")
//                || uri.endsWith("index.jsp")
//                || uri.endsWith("footer.jsp")
//                || uri.endsWith("navbar.jsp")
//                || uri.endsWith("access-denied.jsp");
//
//        // Servlet pubbliche
//        boolean isPublicServlet = uri.contains("/Login") || uri.contains("/Registrazione");
//
//        // JSP private
//        boolean isAdminPage = uri.contains("/jsp/private/admin/");
//        boolean isPrivatePage = uri.contains("/jsp/private/") && !isAdminPage;
//
//        // Servlet private
//        boolean isCustomerServlet = uri.contains("/ProdottiServlet")
//                || uri.contains("/SearchServlet")
//                || uri.contains("/RicercaProdottiServlet");
//
//        // Servlet admin (aggiungi qui altre servlet admin-only se serve)
//        boolean isAdminServlet = uri.contains("/DeleteProductServlet")
//                || uri.contains("/InserisciProdottiServlet")
//                || uri.contains("/InitServlet");
//
//        if (isPublicPage || isPublicServlet || isRoot) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // JSP Private
//        if (isPrivatePage) {
//            if (ruolo != null && (ruolo.equalsIgnoreCase("CUSTOMER") || ruolo.equalsIgnoreCase("ADMIN"))) {
//                chain.doFilter(request, response);
//            } else {
//                res.sendRedirect(context + "/jsp/access-denied.jsp");
//            }
//            return;
//        }
//
//        // JSP Admin
//        if (isAdminPage) {
//            if (ruolo != null && ruolo.equalsIgnoreCase("ADMIN")) {
//                chain.doFilter(request, response);
//            } else {
//                res.sendRedirect(context + "/jsp/access-denied.jsp");
//            }
//            return;
//        }
//
//        // Servlet per CUSTOMER
//        if (isCustomerServlet) {
//            if (ruolo != null && (ruolo.equalsIgnoreCase("CUSTOMER") || ruolo.equalsIgnoreCase("ADMIN"))) {
//                chain.doFilter(request, response);
//            } else {
//                res.sendRedirect(context + "/jsp/access-denied.jsp");
//            }
//            return;
//        }
//
//        // Servlet per ADMIN
//        if (isAdminServlet) {
//            if (ruolo != null && ruolo.equalsIgnoreCase("ADMIN")) {
//                chain.doFilter(request, response);
//            } else {
//                res.sendRedirect(context + "/jsp/access-denied.jsp");
//            }
//            return;
//        }
//
//        // Default: blocca tutto il resto
//        res.sendRedirect(context + "/jsp/access-denied.jsp");
//    }
//}
//
//
//
//

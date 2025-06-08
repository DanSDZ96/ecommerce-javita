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

@WebServlet("/DeleteProductServlet") 
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doPost(request, response);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute("chiaveBl");
	        String idProdotti = request.getParameter("idProdotto");
	        
	        if (idProdotti != null) {
	        	int id=Integer.parseInt(idProdotti);
                bl.deleteProductFromAcquisto(id);
	        	bl.deleteProductFromCarrello(id);
	        	bl.deleteProductById(id);                
                request.setAttribute("messaggioSuccess", "Prodotto cancellato correttamente!");      
	        }
	        
	        //TODO CREAZIONE DI UNA SECONDA SERVLET PER CARICARE I PRODOTTI
	        // + REINDIRIZZAMENTO VERSO LA DELETE PRODOTTO JSP
	        
	        List<Prodotto> prodotti = bl.selectAllProdotto();
	        request.setAttribute("listaProdotti", prodotti);
	        Map<Integer, String> immagini = AggiungiImmagini.generaMappaImmagini(prodotti, request, getServletContext());
	        request.setAttribute("chiaveImmagini", immagini);
	        
	        request.getRequestDispatcher("/jsp/private/admin/deleteProdotto.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("chiaveErrore", "Errore durante l'eliminazione.");
	        request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
	    }
		
	}
}
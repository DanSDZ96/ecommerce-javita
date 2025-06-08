package com.azienda.progettoCatalogoProdotto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;

import com.azienda.progettoCatalogoProdotto.model.Prodotto;
import com.azienda.progettoCatalogoProdotto.model.Categoria;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;
import com.azienda.progettoCatalogoProdotto.utils.BlobConverter;

@WebServlet("/InserisciProdottiServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,maxFileSize = 1024 * 1024 * 10,maxRequestSize = 1024 * 1024 * 10 * 5)
public class InserisciProdottiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Recupero parametri
            String nome = request.getParameter("nome");
            Double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            Integer disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
            Integer categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
            String nomeImmagine = request.getParameter("nomeImmagine");

            // Percorso upload immagini CREARE CARTELLA
            String uploadPath = getServletContext().getRealPath("") + File.separator + "upload";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Elaborazione file immagine
            Blob immagine = null;
            for (Part p : request.getParts()) {
                String fileName = p.getSubmittedFileName();
                if (fileName != null && !fileName.isEmpty()) {
                    String filePath = uploadPath + File.separator + fileName;
                    p.write(filePath);
                    immagine = BlobConverter.generateBlob(filePath);
                    nomeImmagine = fileName; // Assicuriamoci che venga aggiornato
                    break;
                }
            }

            // Business Logic
            BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute("chiaveBl");
            Categoria categoria = bl.selectCategoriaById(categoriaId);

            // Costruzione prodotto
            Prodotto prodotto = new Prodotto(nome, disponibilita, prezzo);
            prodotto.setCategoria(categoria);
            prodotto.setFileImmagine(immagine);
            prodotto.setNomeImmagine(nomeImmagine);

            // Salvataggio nel DB
            bl.insertProdotto(prodotto);

            // Redirect con messaggio di successo DA STAMPARE IN JSP
            request.setAttribute("messaggioSuccess", "Prodotto inserito correttamente!");
            request.getRequestDispatcher("/CategorieServlet").forward(request, response);
            

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("chiaveErrore", "Prodotto gia' esistente");
            request.getRequestDispatcher("/CategorieServlet").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doPost(request, response);
    }
}

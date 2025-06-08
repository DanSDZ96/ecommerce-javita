package com.azienda.progettoCatalogoProdotto.utils;

import java.io.File;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.azienda.progettoCatalogoProdotto.model.Prodotto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletContext;

public class AggiungiImmagini {

    public static Map<Integer, String> generaMappaImmagini(List<Prodotto> lista, HttpServletRequest request, ServletContext context) throws Exception {
        Map<Integer, String> mappaImmagini = new HashMap<>();
        String uploadPath = context.getRealPath("") + File.separator + "upload";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String baseHttpUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        for (Prodotto p : lista) {
            Blob immagine = p.getFileImmagine();
            String fileName = p.getId() + "_" + p.getNomeImmagine();
            if (immagine != null) {
                String filePath = uploadPath + File.separator + fileName;
                BlobConverter.saveFile(immagine, filePath);
                mappaImmagini.put(p.getId(), baseHttpUrl + "/upload/" + fileName);
            } else {
                mappaImmagini.put(p.getId(), baseHttpUrl + "/assets/notFound.png");
            }
        }

        return mappaImmagini;
    }
}

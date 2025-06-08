package com.azienda.progettoCatalogoProdotto.servlet;

import javax.persistence.EntityManager;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.azienda.progettoCatalogoProdotto.dao.AcquistoDao;
import com.azienda.progettoCatalogoProdotto.dao.CarrelloDao;
import com.azienda.progettoCatalogoProdotto.dao.CategoriaDao;
import com.azienda.progettoCatalogoProdotto.dao.OrdineDao;
import com.azienda.progettoCatalogoProdotto.dao.ProdottoDao;
import com.azienda.progettoCatalogoProdotto.dao.ProdottoNelCarrelloDao;
import com.azienda.progettoCatalogoProdotto.dao.RuoloDao;
import com.azienda.progettoCatalogoProdotto.dao.UtenteDao;
import com.azienda.progettoCatalogoProdotto.service.BusinessLogic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(value = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
	private EntityManager manager;
	
	@Override
	public void init() throws ServletException {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Paperino");
			manager = factory.createEntityManager();
			AcquistoDao acquistoDao = new AcquistoDao(manager);
			CarrelloDao carrelloDao = new CarrelloDao(manager);
			CategoriaDao categoriaDao = new CategoriaDao(manager);
			OrdineDao ordineDao = new OrdineDao(manager);
			ProdottoDao prodottoDao = new ProdottoDao(manager);
			RuoloDao ruoloDao = new RuoloDao(manager);
			UtenteDao utenteDao = new UtenteDao(manager);
			ProdottoNelCarrelloDao pncDao = new ProdottoNelCarrelloDao(manager);
			
			BusinessLogic bl = new BusinessLogic(manager, acquistoDao, carrelloDao, categoriaDao, ordineDao, prodottoDao, ruoloDao, utenteDao, pncDao);
			getServletContext().setAttribute("chiaveBl", bl);
			
			bl.riempiDatabase();
			System.out.println("Setup terminato");
		}
		catch(Exception e) {
			e.printStackTrace();
			manager.close();
			System.exit(0);
		}
	}
}

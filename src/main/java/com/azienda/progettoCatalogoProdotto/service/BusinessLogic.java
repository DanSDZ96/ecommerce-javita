package com.azienda.progettoCatalogoProdotto.service;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.azienda.progettoCatalogoProdotto.dao.AcquistoDao;
import com.azienda.progettoCatalogoProdotto.dao.CarrelloDao;
import com.azienda.progettoCatalogoProdotto.dao.CategoriaDao;
import com.azienda.progettoCatalogoProdotto.dao.OrdineDao;
import com.azienda.progettoCatalogoProdotto.dao.ProdottoDao;
import com.azienda.progettoCatalogoProdotto.dao.ProdottoNelCarrelloDao;
import com.azienda.progettoCatalogoProdotto.dao.RuoloDao;
import com.azienda.progettoCatalogoProdotto.dao.UtenteDao;
import com.azienda.progettoCatalogoProdotto.exception.AlreadyExistingUtenteException;
import com.azienda.progettoCatalogoProdotto.exception.ErrorDisponibilitaException;
import com.azienda.progettoCatalogoProdotto.exception.PasswordErrataException;
import com.azienda.progettoCatalogoProdotto.exception.RequiredFieldException;
import com.azienda.progettoCatalogoProdotto.exception.UtenteNonTrovatoException;
import com.azienda.progettoCatalogoProdotto.exception.existProductException;
import com.azienda.progettoCatalogoProdotto.exception.insertErrorException;
import com.azienda.progettoCatalogoProdotto.model.Acquisto;
import com.azienda.progettoCatalogoProdotto.model.Carrello;
import com.azienda.progettoCatalogoProdotto.model.Categoria;
import com.azienda.progettoCatalogoProdotto.model.Ordine;
import com.azienda.progettoCatalogoProdotto.model.Prodotto;
import com.azienda.progettoCatalogoProdotto.model.ProdottoNelCarrello;
import com.azienda.progettoCatalogoProdotto.model.Ruolo;
import com.azienda.progettoCatalogoProdotto.model.Utente;

public class BusinessLogic {
	private EntityManager manager;
	private AcquistoDao acquistoDao;
	private CarrelloDao carrelloDao;
	private CategoriaDao categoriaDao;
	private OrdineDao ordineDao;
	private ProdottoDao prodottoDao;
	private RuoloDao ruoloDao;
	private UtenteDao utenteDao;
	private ProdottoNelCarrelloDao pncDao;
	
	public BusinessLogic(EntityManager manager, AcquistoDao acquistoDao, CarrelloDao carrelloDao,
			CategoriaDao categoriaDao, OrdineDao ordineDao, ProdottoDao prodottoDao, RuoloDao ruoloDao,
			UtenteDao utenteDao, ProdottoNelCarrelloDao pncDao) {
		super();
		this.manager = manager;
		this.acquistoDao = acquistoDao;
		this.carrelloDao = carrelloDao;
		this.categoriaDao = categoriaDao;
		this.ordineDao = ordineDao;
		this.prodottoDao = prodottoDao;
		this.ruoloDao = ruoloDao;
		this.utenteDao = utenteDao;
		this.pncDao = pncDao;
	}
	
	// INIZIALIZZAZIONE DI RUOLI - ADMIN - CATEGORIES
	public void riempiDatabase() {
		try {
			manager.getTransaction().begin();
			//Creazione dei ruoli
			ruoloDao.create(new Ruolo("ADMIN"));
			ruoloDao.create(new Ruolo("CUSTOMER"));
	
			//Creazione utente ADMIN
			Utente admin = new Utente("admin", "admin@admin.it", "admin");
			admin.setRuolo(ruoloDao.getRuoloByName("ADMIN"));
			utenteDao.create(admin);
			
			Carrello carrello = new Carrello();
	        carrello.setUtente(admin);
	        carrelloDao.create(carrello);
	
			//Creazione categorie
			Categoria cucina = new Categoria("Cucina");
			categoriaDao.create(cucina);
			Categoria salotto = new Categoria("Salotto");
			categoriaDao.create(salotto);
			Categoria svago = new Categoria("Svago");
			categoriaDao.create(svago);
			manager.getTransaction().commit();
			
		} catch (Exception e) {
		e.printStackTrace();
		manager.getTransaction().rollback();
		}
	}
	
	public Utente insertUser(Utente utente) throws RequiredFieldException, AlreadyExistingUtenteException {
	    try {
	        manager.getTransaction().begin();

	        if (utente.getEmail() == null || utente.getEmail().isEmpty()) {
	            throw new RequiredFieldException("Campo obbligatorio", null);
	        }

	        try {
	            utenteDao.selectByEmail(utente.getEmail());
	            throw new AlreadyExistingUtenteException("Email già esistente", null);
	        } catch (NoResultException e) {
	            utente.setRuolo(ruoloDao.getRuoloByName("CUSTOMER"));
	            utente = utenteDao.create(utente);

	            // CREAZIONE CARRELLO
	            Carrello carrello = new Carrello();
	            carrello.setUtente(utente);
	            carrelloDao.create(carrello);

	            utente.setCarrello(carrello);
	            utenteDao.update(utente);

	            manager.getTransaction().commit();
	            return utente;
	        }
	    } catch (RequiredFieldException | AlreadyExistingUtenteException e) {
	        manager.getTransaction().rollback();
	        throw e;
	    } catch (Exception e) {
	        manager.getTransaction().rollback();
	        throw e;
	    }
	}

	
	public Utente login(String mail, String password) throws UtenteNonTrovatoException,PasswordErrataException{
		try {
			Utente u = utenteDao.selectByEmail(mail);
			if(u.getPassword().equals(password)) {
				System.out.println("ok");
				return u;
			}
			else {
				throw new PasswordErrataException("Credenziali errate.",null);
			}
		}
		catch(NoResultException e) {
			throw new UtenteNonTrovatoException("Credenziali errate.",null);
		}catch(Exception e) {
			throw e;
		}
	}
	
	public List<Prodotto> selectAllProdotto(){
		try {
			manager.getTransaction().begin();
			List<Prodotto> prodotti = prodottoDao.read();		
			manager.getTransaction().commit();
			return prodotti;
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} return null;
	}
	
	public List<Prodotto> selectByName(String string){
		try {
			manager.getTransaction().begin();
			List<Prodotto> prodotti = prodottoDao.findByNameLike(string);		
			manager.getTransaction().commit();
			return prodotti;
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public Categoria selectCategoriaById(int id) {
		try {
			manager.getTransaction().begin();
			Categoria categoria = categoriaDao.getCategoriaById(id);		
			manager.getTransaction().commit();
			return categoria;
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} return null;
	}
	
	public Prodotto selectProductById(int id) {
		try {
			manager.getTransaction().begin();
			Prodotto prodotto = prodottoDao.getById(id);		
			manager.getTransaction().commit();
			return prodotto;
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} return null;
	}
	
	public void insertProdotto(Prodotto p) throws existProductException, insertErrorException{	
		try {
			manager.getTransaction().begin();
			if(p.getDisponibilita() < 0 || p.getNome().isBlank() || p.getCategoria() != null||
			   p.getFileImmagine() != null || p.getNomeImmagine().isBlank() || p.getPrezzo() <= 0) {
				List<Prodotto> prodotti = prodottoDao.read();
				for(Prodotto prodotto : prodotti) {
					if(prodotto.getNome().equals(p.getNome())) {
						throw new existProductException("Prodotto esistente.",null);
					}
				}
				manager.persist(p);
				manager.getTransaction().commit();
			} else {
				throw new insertErrorException("Errore inserimento prodotto", null);
			}
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public List<Categoria> selectAllCategorie(){
		try {
			manager.getTransaction().begin();
			List<Categoria> categorie = categoriaDao.read();		
			manager.getTransaction().commit();
			return categorie;
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} return null;
	}
	
	public List<Prodotto> cercaProdottiByKeyword(String keywords) {
		try {
			manager.getTransaction().begin();
			List<Prodotto> prodotti = prodottoDao.cerca(keywords);
			manager.getTransaction().commit();
			return prodotti;
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} return null;
	}
	
	public void deleteProductById(int id) {
	    try {
	        manager.getTransaction().begin();
			prodottoDao.deleteById(id);		
			
			manager.getTransaction().commit();

	    } catch (Exception e) {
	        manager.getTransaction().rollback();
	        throw e;
	    }
	}
	
	public void deleteProductByIdFromCarrello(int id) {
	    try {
	        manager.getTransaction().begin();
			pncDao.deleteByIdFromCarrello(id);	
			
			manager.getTransaction().commit();

	    } catch (Exception e) {
	        manager.getTransaction().rollback();
	        throw e;
	    }
	}
	
	public void deleteProductFromCarrello(int idProdotto) {
		try {
			manager.getTransaction().begin();
			List<ProdottoNelCarrello> pnc = pncDao.getCarrelloByIdProdotto(idProdotto);
			for(ProdottoNelCarrello prodotti : pnc) {
				pncDao.delete(prodotti);
				prodotti.setProdotto(null);
			}
			manager.getTransaction().commit();
		}catch(Exception e) {
	        manager.getTransaction().rollback();
	        throw e;
	    }
	}
	
	public void deleteProductFromAcquisto(int idProdotto) {
		try {
			manager.getTransaction().begin();
			List<Acquisto> listaAcquisti = acquistoDao.getAcquistiByIdProdotto(idProdotto);
			for(Acquisto acquisto : listaAcquisti) {
				acquisto.setProdotto(null);
			}
			manager.getTransaction().commit();
		}catch (Exception e) {
	        manager.getTransaction().rollback();
	        throw e;
	    }
	}
	
	public List<Utente> getAllUsers() {
	    try {
	        manager.getTransaction().begin();
			List<Utente> listaUtenti = utenteDao.read();
			manager.getTransaction().commit();
			return listaUtenti;
	    }  catch (Exception e) {
			manager.getTransaction().rollback();
		} return null;
	}
	
	public void updateProduct(Prodotto p) {
	    try {
	        manager.getTransaction().begin();

	        prodottoDao.update(p);

	        manager.getTransaction().commit();
	    } catch (Exception e) {
	        manager.getTransaction().rollback();
	        throw e;
	    }
	}
	
	public Carrello svuotaCarrelloUtente(int utenteId) {
	    try {
	        manager.getTransaction().begin();
	        Carrello carrello = carrelloDao.getByUtenteId(utenteId);
	        ProdottoNelCarrelloDao pncDao = new ProdottoNelCarrelloDao(manager);
	        pncDao.deleteByCarrelloId(carrello.getId());
	        manager.getTransaction().commit();
	        return carrello;
	    } catch (Exception e) {
	        manager.getTransaction().rollback();
	        throw e;
	    }
	}
	
	public Carrello getCarrelloByUtenteId(int utenteId) {
	    return carrelloDao.getByUtenteId(utenteId);
	}
	
	public void aggiungiProdottoAlCarrelloConQuantita(Integer utenteId, Integer prodottoId, int quantita) {
		
	    try {
	        manager.getTransaction().begin();

	        Carrello carrello = carrelloDao.getByUtenteId(utenteId);
	        Prodotto prodotto = prodottoDao.getById(prodottoId);
	        ProdottoNelCarrelloDao pncDao = new ProdottoNelCarrelloDao(manager);

	        ProdottoNelCarrello pnc = pncDao.findByCarrelloAndProdotto(carrello.getId(), prodottoId);
	        if (pnc != null) {
	            pnc.setQuantita(pnc.getQuantita() + quantita);
	            pncDao.update(pnc);
	        } else {
	            pnc = new ProdottoNelCarrello();
	            pnc.setCarrello(carrello);
	            pnc.setProdotto(prodotto);
	            pnc.setQuantita(quantita);
	            pncDao.create(pnc);
	        }

	        manager.getTransaction().commit();
	    } catch (Exception e) {
	        manager.getTransaction().rollback();
	        throw e;
	    }
	}
	
	public void gestisciAggiuntaProdottoCarrello(Integer utenteId, int prodottoId, int quantita) throws ErrorDisponibilitaException{
		try {
			int quantitaCarrello =0;
			Prodotto prodotto = new Prodotto();
			Carrello carrello = carrelloDao.getByUtenteId(utenteId);

			List<ProdottoNelCarrello> prodotti = pncDao.getByCarrelloId(carrello.getId());
			for(ProdottoNelCarrello pnc : prodotti) {
				if(pnc.getProdotto().getId() == prodottoId) {
					prodotto = pnc.getProdotto();
					quantitaCarrello = pnc.getQuantita();
				}
			}
			if(prodotto.getDisponibilita()==null) {
				aggiungiProdottoAlCarrelloConQuantita(utenteId, prodottoId, quantita);				
			}
			else if(prodotto.getDisponibilita()!=null) {
				if((quantita + quantitaCarrello)>prodotto.getDisponibilita()) {
					throw new ErrorDisponibilitaException("Errore quantita' non disponibile", null);
				}
				else {
					aggiungiProdottoAlCarrelloConQuantita(utenteId, prodottoId, quantita);
				}
			}
		}catch(Exception e) {
			throw e;
		}
		
	   // int sommaQuantita = quantita + utente.getCarrello().getProdottiNelCarrello().
		

//	    // Aggiorna il carrello dell'utente
//	    Carrello carrelloAggiornato = getCarrelloByUtenteId(utente.getId());
//	    utente.setCarrello(carrelloAggiornato);
	}

	public Map<Prodotto, Integer> getProdottiEQuantitaPerView(int utenteId) {
	    try {
	        manager.getTransaction().begin();

	        Carrello carrello = carrelloDao.getByUtenteId(utenteId);

	        List<ProdottoNelCarrello> elenco = pncDao.getByCarrelloId(carrello.getId());
	        Map<Prodotto, Integer> prodottiQuantita = new HashMap<>();
	        for (ProdottoNelCarrello pnc : elenco) {
	            prodottiQuantita.put(pnc.getProdotto(), pnc.getQuantita());
	        }

	        manager.getTransaction().commit();
	        return prodottiQuantita;
	    } catch (Exception e) {
	        manager.getTransaction().rollback();
	        throw e;
	    }
	}
	
	
		
	
	public void confermaOrdine(Utente utente) {
		try {
			manager.getTransaction().begin();
			LocalDate data = LocalDate.now(ZoneId.of("Europe/Rome"));
			Ordine ordine = new Ordine(data);
			ordine.setUtente(utente);
			
			ordineDao.create(ordine);
			List<Acquisto> prodottiAcquistati = acquistaProdotti(utente);
			for(Acquisto a: prodottiAcquistati) {
				a.setOrdine(ordine);
				Prodotto p = prodottoDao.getById(a.getProdotto().getId());
				p.setDisponibilita(p.getDisponibilita() - a.getQuantita());
			}
			acquistoDao.createList(prodottiAcquistati);
			manager.getTransaction().commit();
			
			Carrello nuovoCarrello = svuotaCarrelloUtente(utente.getId());
			utente.setCarrello(nuovoCarrello);
			
		} catch(Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

	
	public List<Acquisto> acquistaProdotti(Utente utente) {
		List<ProdottoNelCarrello> prodotti = pncDao.getByCarrelloId(utente.getCarrello().getId());
		
		List<Acquisto> prodottiAcquistati = new ArrayList<Acquisto>();
		int i=0;
		for(ProdottoNelCarrello pnc : prodotti) {
			String nomeProdotto = pnc.getProdotto().getNome();
			Double prezzoProdotto = pnc.getProdotto().getPrezzo();
			int quantitaProdotto = pnc.getQuantita();
			
			prodottiAcquistati.add(new Acquisto(nomeProdotto, prezzoProdotto,quantitaProdotto));
			prodottiAcquistati.get(i).setProdotto(pnc.getProdotto());
			i++;
		}
		
		return prodottiAcquistati;
	}
	
	public List<Ordine> getOrdiniByIdUtente(Utente utente){
		return ordineDao.getAllByUserId(utente.getId());
	}
	
	public List<Acquisto> getAcquistiByOrdine(Ordine ordine){
		return acquistoDao.getAllByOrdineId(ordine.getId());
	}

	public List<Ordine> getAllOrdini() {
		return ordineDao.read();
	}
	
	public void aggiornaPassword(Integer utenteId, String nuovaPassword) {
		try {
			manager.getTransaction().begin();
			Utente u = manager.find(Utente.class, utenteId);
			u.setPassword(nuovaPassword);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}

	}
}
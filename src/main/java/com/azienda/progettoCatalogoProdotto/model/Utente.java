package com.azienda.progettoCatalogoProdotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String username;
	private String email;
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "utente_ruolo")
	private Ruolo ruolo;
	
	@OneToOne(mappedBy = "utente")
	private Carrello carrello;
	
	@OneToMany(mappedBy = "utente")
	private List<Ordine> ordini = new ArrayList<Ordine>();
	
	public Utente() {};
	
	public Utente(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Carrello getCarrello() {
		return carrello;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
		
	@Override
	public int hashCode() {
		return Objects.hash(carrello, email, id, ordini, password, ruolo, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(carrello, other.carrello) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(ordini, other.ordini)
				&& Objects.equals(password, other.password) && Objects.equals(ruolo, other.ruolo)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Utente [username=" + username + ", email=" + email + ", password=" + password + ", ruolo=" + ruolo + "]";
	}
}

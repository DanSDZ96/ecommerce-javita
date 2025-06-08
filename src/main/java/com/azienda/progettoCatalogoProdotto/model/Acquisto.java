package com.azienda.progettoCatalogoProdotto.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Acquisto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nomeProdotto;
	private Double prezzoProdotto;
	private Integer quantita;
	
	@ManyToOne
	private Ordine ordine;
	
	@ManyToOne
	private Prodotto prodotto;
	
	public Acquisto(){};
	public Acquisto(String nomeProdotto, Double prezzoProdotto, Integer quantita){
		this.nomeProdotto = nomeProdotto;
		this.prezzoProdotto = prezzoProdotto;
		this.quantita = quantita;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeProdotto() {
		return nomeProdotto;
	}
	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}
	public Double getPrezzoProdotto() {
		return prezzoProdotto;
	}
	public void setPrezzoProdotto(Double prezzoProdotto) {
		this.prezzoProdotto = prezzoProdotto;
	}
	
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	public Ordine getOrdine() {
		return ordine;
	}
	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}
	public Prodotto getProdotto() {
		return prodotto;
	}
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, nomeProdotto, ordine, prezzoProdotto, prodotto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acquisto other = (Acquisto) obj;
		return Objects.equals(id, other.id) && Objects.equals(nomeProdotto, other.nomeProdotto)
				&& Objects.equals(ordine, other.ordine) && Objects.equals(prezzoProdotto, other.prezzoProdotto)
				&& Objects.equals(prodotto, other.prodotto);
	}
	@Override
	public String toString() {
		return "Acquisto [id=" + id + ", nomeProdotto=" + nomeProdotto + ", prezzoProdotto=" + prezzoProdotto + "]";
	};
}

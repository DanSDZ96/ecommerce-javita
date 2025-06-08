package com.azienda.progettoCatalogoProdotto.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ordine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate data_ordine;
	
	@ManyToOne
	private Utente utente;
	
	@OneToMany(mappedBy = "ordine")
	private List<Acquisto> acquisti = new ArrayList<Acquisto>();
	
	public Ordine() {};
	public Ordine(LocalDate data_ordine) {
		this.data_ordine = data_ordine;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getData_ordine() {
		return data_ordine;
	}
	public void setData_ordine(LocalDate data_ordine) {
		this.data_ordine = data_ordine;
	}
	
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public List<Acquisto> getAcquisti() {
		return acquisti;
	}
	public void setAcquisti(List<Acquisto> acquisti) {
		this.acquisti = acquisti;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(acquisti, data_ordine, id, utente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordine other = (Ordine) obj;
		return Objects.equals(acquisti, other.acquisti) && Objects.equals(data_ordine, other.data_ordine)
				&& Objects.equals(id, other.id) && Objects.equals(utente, other.utente);
	}
	@Override
	public String toString() {
		return "Ordine [id=" + id + ", data_ordine=" + data_ordine + "]";
	}
	
	
}

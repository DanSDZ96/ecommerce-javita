package com.azienda.progettoCatalogoProdotto.model;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private Integer disponibilita;
    private Double prezzo;
    private Blob fileImmagine;
    private String nomeImmagine;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "prodotto", orphanRemoval = true)
    private List<ProdottoNelCarrello> nelCarrello = new ArrayList<>();

    @OneToMany(mappedBy = "prodotto")
    private List<Acquisto> acquistati = new ArrayList<>();

    public Prodotto() {}

    public Prodotto(String nome, Integer disponibilita, Double prezzo) {
        this.nome = nome;
        this.disponibilita = disponibilita;
        this.prezzo = prezzo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(Integer disponibilita) {
        this.disponibilita = disponibilita;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Blob getFileImmagine() {
        return fileImmagine;
    }

    public void setFileImmagine(Blob fileImmagine) {
        this.fileImmagine = fileImmagine;
    }

    public String getNomeImmagine() {
        return nomeImmagine;
    }

    public void setNomeImmagine(String nomeImmagine) {
        this.nomeImmagine = nomeImmagine;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<ProdottoNelCarrello> getNelCarrello() {
        return nelCarrello;
    }

    public void setNelCarrello(List<ProdottoNelCarrello> nelCarrello) {
        this.nelCarrello = nelCarrello;
    }

    public List<Acquisto> getAcquistati() {
        return acquistati;
    }

    public void setAcquistati(List<Acquisto> acquistati) {
        this.acquistati = acquistati;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Prodotto other = (Prodotto) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Prodotto [id=" + id + ", nome=" + nome + ", disponibilita=" + disponibilita +
                ", prezzo=" + prezzo + ", nomeImmagine=" + nomeImmagine + "]";
    }
}

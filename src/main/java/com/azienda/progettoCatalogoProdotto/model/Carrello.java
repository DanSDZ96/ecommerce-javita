package com.azienda.progettoCatalogoProdotto.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProdottoNelCarrello> prodottiNelCarrello = new ArrayList<>();

    public Carrello() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public List<ProdottoNelCarrello> getProdottiNelCarrello() {
        return prodottiNelCarrello;
    }

    public void setProdottiNelCarrello(List<ProdottoNelCarrello> prodottiNelCarrello) {
        this.prodottiNelCarrello = prodottiNelCarrello;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, utente);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Carrello other = (Carrello) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Carrello [id=" + id + ", utente=" + utente + "]";
    }
}

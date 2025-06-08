package com.azienda.progettoCatalogoProdotto.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProdottoNelCarrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "carrello_id", nullable = false)
    private Carrello carrello;

    @ManyToOne
    @JoinColumn(name = "prodotto_id", nullable = false)
    private Prodotto prodotto;

    private int quantita;

    public Integer getId() {
        return id;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carrello, prodotto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProdottoNelCarrello other = (ProdottoNelCarrello) obj;
        return Objects.equals(carrello, other.carrello) &&
               Objects.equals(prodotto, other.prodotto);
    }

    @Override
    public String toString() {
        return "ProdottoNelCarrello [prodotto=" + prodotto.getNome() + ", quantita=" + quantita + "]";
    }
}

package controller;

import model.domain.Artikel;
import model.domain.ObserverArtikelenInShop;
import model.domain.Verkoop;
import view.panels.ArtikelPane;

import java.util.List;

public class ArtikelController implements ObserverArtikelenInShop {
    private ArtikelPane artikelPane;
    private Verkoop verkoop;

    public void setView(ArtikelPane artikelPane){
        this.artikelPane = artikelPane;
    }

    public ArtikelPane getArtikelPane(){
        return artikelPane;
    }

    public ArtikelController(Verkoop verkoop) {
        verkoop.registerObserver(this);
    }

    public void setArtikelenInView(List<Artikel> artikels){
        artikelPane.setObservableList(artikels);
    }


    @Override
    public void update(List<Artikel> artikels) {
        setArtikelenInView(artikels);
    }
}

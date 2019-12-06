package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.Artikel;
import model.domain.ArtikelContainer;
import model.domain.Verkoop;
import model.domain.Observer;
import view.panels.KassaKassierPane;

import java.util.List;
import java.util.Map;

public class KassaKassierController implements Observer {
    private KassaKassierPane kassaKassierPane;
    private Verkoop verkoop;

    public KassaKassierController(Verkoop verkoop) {
        this.verkoop = verkoop;
        verkoop.registerObserver(this);
    }

    public void setView(KassaKassierPane kassaKassierPane){
        this.kassaKassierPane = kassaKassierPane;
        this.kassaKassierPane.setObservableList(verkoop.getArtikelenInKassaKassier());
    }

    public void setNieuwArtikel(String artikelId){
        verkoop.addArtikel(artikelId);
    }

    public void removeArtikelen(List<Integer> indeces){
        verkoop.removeArtikelen(indeces);
    }

    public KassaKassierPane getKassaKassierPane() {
        return kassaKassierPane;
    }



    @Override
        public void update(String totaal) {
            this.kassaKassierPane.setTotaal(totaal);
        }




}

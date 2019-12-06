package controller;

import javafx.collections.FXCollections;
import model.domain.Artikel;
import model.domain.ArtikelContainer;
import model.domain.Verkoop;
import model.domain.Observer;
import view.panels.KassaKassierPane;

import java.util.List;
import java.util.Map;

public class KassaKassierController implements Observer {
    private List<ArtikelContainer> artikelenInKassa;
    private KassaKassierPane kassaKassierPane;
    private Verkoop verkoop;

    public KassaKassierController(Verkoop verkoop) {
        this.verkoop = verkoop;
        verkoop.registerObserver(this);
        artikelenInKassa = verkoop.getArtikelenInKassaKassier();
    }

    public void setView(KassaKassierPane kassaKassierPane){
        this.kassaKassierPane = kassaKassierPane;
        this.kassaKassierPane.setObservableList(this.artikelenInKassa);
    }

    public void setNieuwArtikel(String artikelId){
        verkoop.addArtikel(artikelId);
    }

    public KassaKassierPane getKassaKassierPane() {
        return kassaKassierPane;
    }



    @Override
        public void update(double totaal) {
            this.kassaKassierPane.setTotaal(totaal);
        }




}

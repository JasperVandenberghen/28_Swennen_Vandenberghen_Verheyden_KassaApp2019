package controller;

import javafx.collections.FXCollections;
import model.domain.Artikel;
import model.domain.ArtikelContainer;
import model.domain.Verkoop;
import model.domain.Observer;
import view.panels.KassaKlantPane;

import java.util.List;
import java.util.Map;

public class KassaKlantController implements Observer {
    private Map<String, Artikel> artikelMap;
    private List<ArtikelContainer> artikelenInKassa;
    private KassaKlantPane kassaKlantPane;
    private double totaal;

    public KassaKlantController(Map<String, Artikel> artikelMap, Verkoop verkoop) {
        this.artikelMap = artikelMap;
        artikelenInKassa = FXCollections.observableArrayList();
        verkoop.registerObserver(this);
    }

    public void setView(KassaKlantPane kassaKlantPane) {
        this.kassaKlantPane = kassaKlantPane;
        this.kassaKlantPane.setObservableList(artikelenInKassa);
    }

    @Override
    public void update(String artikelId) {

    }

    public void remove(String artikelId){
        if(artikelenInKassa.contains(artikelId)){
            artikelenInKassa.removeIf(a -> artikelId.equals(a.getArtikelId()));
        } else {
            throw new IllegalArgumentException("Dit artikel zit niet in de kassa");
        }
        }


    public KassaKlantPane getKassaKlantPane() {
        return kassaKlantPane;
    }
}

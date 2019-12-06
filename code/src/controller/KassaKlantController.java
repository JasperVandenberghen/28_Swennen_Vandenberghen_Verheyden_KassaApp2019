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
    private List<ArtikelContainer> artikelenInKassa;
    private KassaKlantPane kassaKlantPane;
    private double totaal;

    public KassaKlantController(Verkoop verkoop) {
        artikelenInKassa = verkoop.getArtikelenInKassaKlant();
        verkoop.registerObserver(this);
    }

    public void setView(KassaKlantPane kassaKlantPane) {
        this.kassaKlantPane = kassaKlantPane;
        this.kassaKlantPane.setObservableList(artikelenInKassa);
    }

    @Override
    public void update(String totaal) {
        this.kassaKlantPane.setTotaal(totaal);
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

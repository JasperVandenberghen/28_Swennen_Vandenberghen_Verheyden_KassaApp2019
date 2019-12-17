package controller;

import javafx.scene.control.Button;
import model.domain.Verkoop;
import model.domain.Observer;
import view.panels.KassaKassierPane;

import java.util.List;

public class KassaKassierController implements Observer {
    private KassaKassierPane kassaKassierPane;
    private Verkoop verkoop;

    public KassaKassierController(Verkoop verkoop) {
        this.verkoop = verkoop;
        verkoop.registerObserver(this);
        verkoop.setKassaKassierController(this);

    }

    public void setView(KassaKassierPane kassaKassierPane){
        this.kassaKassierPane = kassaKassierPane;
        this.kassaKassierPane.setObservableList(verkoop.getArtikelenInKassaKassier());
    }

    public void setNieuwArtikel(String artikelId){
        verkoop.addArtikelStateFunction(artikelId);
    }

    public void removeArtikelenStateFunction(List<Integer> indeces){
        verkoop.removeArtikelenStateFunction(indeces);
    }

    public KassaKassierPane getKassaKassierPane() {
        return kassaKassierPane;
    }

    @Override
    public void update(String totaal, String korting, String eindTotaal) {
        this.kassaKassierPane.setTotaal(totaal);
        kassaKassierPane.setKorting(korting);
        kassaKassierPane.setEindtotaal(eindTotaal);
    }

    public void setVerkoopOnHold(Button button){
        verkoop.onHoldFunction(button);
    }

    public void beeindigen(Button button){
        verkoop.beeindigenStateFunction(button);
    }


}

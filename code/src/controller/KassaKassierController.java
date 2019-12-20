package controller;

import javafx.scene.control.Button;
import model.domain.KassaBon.KassaBon;
import model.domain.KassaBon.KassaBonBasis;
import model.domain.KassaBon.KassaBonFooter;
import model.domain.KassaBon.KassaBonHeader;
import model.domain.Observer;
import model.domain.Verkoop;
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


    public void printKassaBon() {
        KassaBon kassaBon = new KassaBonBasis();
        kassaBon = new KassaBonHeader(kassaBon);
        kassaBon.setDescription("dit is de header \n");
        kassaBon = new KassaBonFooter(kassaBon);
        kassaBon.setDescription("dit is de footer \n");
        System.out.println(kassaBon.getDescription());
    }
}

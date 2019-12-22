package controller;

import model.domain.Observer;
import model.domain.ObserverButtonText;
import model.domain.Verkoop;
import view.panels.KassaKassierPane;

import java.util.List;

public class KassaKassierController implements Observer, ObserverButtonText
{
    private KassaKassierPane kassaKassierPane;
    private Verkoop verkoop;

    public KassaKassierController(Verkoop verkoop) {
        this.verkoop = verkoop;
        verkoop.registerObserver(this);
        verkoop.registerObserverButtonText(this);
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

    public void setVerkoopOnHold(){
        verkoop.onHoldFunction();
    }

    public void beeindigen(){
        verkoop.beeindigenStateFunction();
    }



    @Override
    public void update(String onHoldText, String afrekenenText) {
        kassaKassierPane.setOnHoldKnopTekst(onHoldText);
        kassaKassierPane.setAfrekenKnop(afrekenenText);
    }

    public Verkoop getVerkoop() {
        return verkoop;
    }

    public void annuleerVerkoop() {
        verkoop.annuleerAfrekenenStateFunction();
    }
}

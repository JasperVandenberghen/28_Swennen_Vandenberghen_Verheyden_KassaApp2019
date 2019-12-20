package model.domain.states;

import javafx.scene.control.Button;
import model.domain.MessageHandler;
import model.domain.Verkoop;

import java.util.List;

public class LegeMandState implements VerkoopState{
    private Verkoop verkoop;

    public LegeMandState(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void addArtikel(String artikelId) {
        verkoop.addArtikel(artikelId);
        verkoop.setVerkoopState(verkoop.getScanState());
    }

    @Override
    public void removeArtikel(List<Integer> indeces) {

    }

    @Override
    public void beeindigen() {

    }

    @Override
    public void onHoldFunction() {
        MessageHandler.showAlert("Uw mand is leeg en er is niets om on hold te zetten");
    }

    @Override
    public void annuleren() {

    }
}

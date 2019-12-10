package model.domain.states;

import javafx.scene.control.Button;
import model.domain.Verkoop;

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
    public void afrekenen() {

    }

    @Override
    public void onHoldFunction(Button button) {

    }

    @Override
    public void annuleren() {

    }

    @Override
    public void betalen() {

    }
}
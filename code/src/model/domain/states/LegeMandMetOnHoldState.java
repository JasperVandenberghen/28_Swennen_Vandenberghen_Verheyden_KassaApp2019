package model.domain.states;

import javafx.scene.control.Button;
import model.domain.Verkoop;

public class LegeMandMetOnHoldState implements VerkoopState{
    private Verkoop verkoop;

    public LegeMandMetOnHoldState(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void addArtikel(String artikelId) {
        verkoop.addArtikel(artikelId);
        verkoop.setVerkoopState(verkoop.getScanMetOnHoldState());
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

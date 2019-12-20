package model.domain.states;

import javafx.scene.control.Button;
import model.domain.Verkoop;

import java.util.List;

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
    public void removeArtikel(List<Integer> indeces) {

    }

    @Override
    public void beeindigen() {

    }

    @Override
    public void onHoldFunction() {
        verkoop.haalVanOnHoldAf();
        verkoop.setVerkoopState(verkoop.getScanState());
    }

    @Override
    public void annuleren() {

    }

}

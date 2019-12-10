package model.domain.states;

import javafx.scene.control.Button;
import model.domain.Verkoop;

public class ScanMetOnHoldState implements VerkoopState{
    private Verkoop verkoop;

    public ScanMetOnHoldState(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void addArtikel(String artikelId) {
        verkoop.addArtikel(artikelId);
    }

    @Override
    public void afrekenen() {
        verkoop.setVerkoopState(verkoop.getAfrekenMetOnHoldState());
    }

    @Override
    public void onHoldFunction(Button button) {
        verkoop.haalVanOnHoldAf(button);
    }

    @Override
    public void annuleren() {
        verkoop.clearArtikelen();
        verkoop.setVerkoopState(verkoop.getLegeMandMetOnHoldState());
    }

    @Override
    public void betalen() {

    }
}

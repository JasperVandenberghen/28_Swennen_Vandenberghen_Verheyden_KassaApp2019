package model.domain.states;

import javafx.scene.control.Button;
import model.domain.ArtikelContainer;
import model.domain.Verkoop;

public class ScanState implements VerkoopState{
    private Verkoop verkoop;

    public ScanState(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void addArtikel(String artikelId) {
        verkoop.addArtikel(artikelId);
    }

    @Override
    public void afrekenen() {
        verkoop.afrekenen();
        verkoop.setVerkoopState(verkoop.getAfrekenState());
    }

    @Override
    public void onHoldFunction(Button button) {
        verkoop.plaatsOnHold(button);
    }

    @Override
    public void annuleren() {
        verkoop.clearArtikelen();
    }

    @Override
    public void betalen() {

    }
}

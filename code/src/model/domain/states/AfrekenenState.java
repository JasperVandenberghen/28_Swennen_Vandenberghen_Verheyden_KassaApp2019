package model.domain.states;

import javafx.scene.control.Button;
import model.domain.ArtikelContainer;
import model.domain.Verkoop;

public class AfrekenenState implements VerkoopState{
    private Verkoop verkoop;

    public AfrekenenState(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void addArtikel(String artikelId) {

    }

    @Override
    public void afrekenen() {
        verkoop.afrekenen();
    }

    @Override
    public void onHoldFunction(Button button) {

    }

    @Override
    public void annuleren() {
        verkoop.annuleerAfrekenen();
    }

    @Override
    public void betalen() {
        verkoop.betalen();
    }
}

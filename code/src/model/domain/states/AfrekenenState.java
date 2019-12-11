package model.domain.states;

import javafx.scene.control.Button;
import model.domain.ArtikelContainer;
import model.domain.Verkoop;

import java.util.List;

public class AfrekenenState implements VerkoopState{
    private Verkoop verkoop;

    public AfrekenenState(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void addArtikel(String artikelId) {

    }

    @Override
    public void removeArtikel(List<Integer> indeces) {
        verkoop.removeArtikelen(indeces);
        verkoop.calculateKorting();
    }

    @Override
    public void afrekenen() {

    }

    @Override
    public void onHoldFunction(Button button) {

    }

    @Override
    public void annuleren() {
        verkoop.annuleerAfrekenen();
        verkoop.setVerkoopState(verkoop.getLegeMandState());
    }

    @Override
    public void betalen() {
        verkoop.betalen();
        verkoop.setVerkoopState(verkoop.getLegeMandState());

    }
}

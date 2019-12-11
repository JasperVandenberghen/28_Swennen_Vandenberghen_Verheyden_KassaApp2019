package model.domain.states;

import javafx.scene.control.Button;
import model.domain.ArtikelContainer;
import model.domain.Verkoop;

import java.util.List;

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
    public void removeArtikel(List<Integer> indeces) {
        verkoop.removeArtikelen(indeces);
        if(verkoop.getArtikelenInKassaKassier().size()==0){
            verkoop.setVerkoopState(verkoop.getLegeMandState());
        }
    }

    @Override
    public void afrekenen() {
        verkoop.afrekenen();
        verkoop.setVerkoopState(verkoop.getAfrekenState());
    }

    @Override
    public void onHoldFunction(Button button) {
        verkoop.plaatsOnHold(button);
        verkoop.setVerkoopState(verkoop.getLegeMandMetOnHoldState());
    }

    @Override
    public void annuleren() {
        verkoop.clearArtikelen();
        verkoop.setVerkoopState(verkoop.getLegeMandState());
    }

    @Override
    public void betalen() {

    }
}

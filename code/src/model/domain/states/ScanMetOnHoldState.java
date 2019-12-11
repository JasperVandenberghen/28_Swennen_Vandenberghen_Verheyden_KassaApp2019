package model.domain.states;

import javafx.scene.control.Button;
import model.domain.MessageHandler;
import model.domain.Verkoop;

import java.util.List;

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
    public void removeArtikel(List<Integer> indeces) {
        verkoop.removeArtikelen(indeces);
        if(verkoop.getArtikelenInKassaKassier().size()==0){
            verkoop.setVerkoopState(verkoop.getLegeMandMetOnHoldState());
        }
    }

    @Override
    public void beeindigen(Button button) {
        verkoop.afrekenen(button);
        verkoop.setVerkoopState(verkoop.getAfrekenMetOnHoldState());
    }

    @Override
    public void onHoldFunction(Button button) {
        MessageHandler.showAlert("Uw mand moet leeg zijn voor u iets uit on Hold haalt");
    }

    @Override
    public void annuleren() {
        verkoop.clearArtikelen();
        verkoop.setVerkoopState(verkoop.getLegeMandMetOnHoldState());
    }

}

package model.domain.states;

import javafx.scene.control.Button;
import model.domain.Verkoop;

import java.util.List;

public class AfrekenenMetOnHoldState implements VerkoopState{
    private Verkoop verkoop;

    public AfrekenenMetOnHoldState(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void addArtikel(String artikelId) {

    }

    @Override
    public void removeArtikel(List<Integer> indeces) {
        verkoop.removeArtikelen(indeces);
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
        verkoop.setVerkoopState(verkoop.getLegeMandMetOnHoldState());
    }

    @Override
    public void betalen() {
        verkoop.betalen();
        verkoop.getOnHoldHandler().increaseAantalVerkopenSindsOnHold();
        if(verkoop.getOnHoldHandler().getAantalVerkopenSindsOnHold() == 4){
            verkoop.getOnHoldHandler().clearAantalSindsVerkoop();
            verkoop.setVerkoopState(verkoop.getLegeMandState());
        } else{
            verkoop.setVerkoopState(verkoop.getLegeMandMetOnHoldState());
        }
    }
}

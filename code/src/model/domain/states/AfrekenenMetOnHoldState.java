package model.domain.states;

import javafx.scene.control.Button;
import model.domain.Verkoop;

public class AfrekenenMetOnHoldState implements VerkoopState{
    private Verkoop verkoop;

    public AfrekenenMetOnHoldState(Verkoop verkoop) {
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
        verkoop.getOnHoldHandler().increaseAantalVerkopenSindsOnHold();
        if(verkoop.getOnHoldHandler().getAantalVerkopenSindsOnHold() == 4){
            verkoop.getOnHoldHandler().clearAantalSindsVerkoop();
            verkoop.setVerkoopState(verkoop.getLegeMandState());
        } else{
            verkoop.setVerkoopState(verkoop.getLegeMandMetOnHoldState());
        }
    }
}

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
    public void beeindigen() {
        verkoop.betalen();
        verkoop.getOnHoldHandler().increaseAantalVerkopenSindsOnHold();
        if(verkoop.getOnHoldHandler().getAantalVerkopenSindsOnHold() == 3){
            verkoop.getOnHoldHandler().clearAantalSindsVerkoop();
            verkoop.getKassaKassierController().getKassaKassierPane().setOnHoldKnopTekst("Plaats on Hold");
            verkoop.setVerkoopState(verkoop.getLegeMandState());
        } else{
            verkoop.setVerkoopState(verkoop.getLegeMandMetOnHoldState());
        }
    }

    @Override
    public void onHoldFunction() {

    }

    @Override
    public void annuleren() {
        verkoop.annuleerAfrekenen();
        verkoop.setVerkoopState(verkoop.getLegeMandMetOnHoldState());
    }

}

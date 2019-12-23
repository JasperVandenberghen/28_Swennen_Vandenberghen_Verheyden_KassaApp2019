package model.domain.states;

import javafx.scene.control.Button;
import model.domain.ArtikelContainer;
import model.domain.MessageHandler;
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
        if(verkoop.getArtikelenInKassaKassier().size()==0){
            verkoop.setVerkoopState(verkoop.getLegeMandState());
            verkoop.setTextTotaal();
            MessageHandler.showAlert("Verkoop geannuleerd");
        } else{
            verkoop.afrekenen();
        }
    }

    @Override
    public void beeindigen() {
        verkoop.betalen();
        verkoop.setVerkoopState(verkoop.getLegeMandState());
    }

    @Override
    public void onHoldFunction() {

    }

    @Override
    public void annuleren() {
        verkoop.annuleerAfrekenen();
        verkoop.setVerkoopState(verkoop.getLegeMandState());
    }

}

package model.db;

import javafx.collections.FXCollections;
import model.domain.ArtikelContainer;

import java.util.List;

public class OnHoldHandler {
    private List<ArtikelContainer> artikelenInKassaKassier;
    private List<ArtikelContainer> artikelenInKassaKlant;
    private int aantalVerkopenSindsOnHold;
    private double totaal;

    public OnHoldHandler() {
    }

    public List<ArtikelContainer> getArtikelenInKassaKassier() {
        return this.artikelenInKassaKassier;
    }

    public List<ArtikelContainer> getArtikelenInKassaKlant() {
        return this.artikelenInKassaKlant;
    }

    public void setArtikelen(List<ArtikelContainer> artikelenInKassaKlant, List<ArtikelContainer> artikelenInKassaKassier, double totaal) {
        this.aantalVerkopenSindsOnHold = 0;
        this.artikelenInKassaKlant = cloneArtikelContainer(artikelenInKassaKlant);
        this.artikelenInKassaKassier = cloneArtikelContainer(artikelenInKassaKassier);
        this.totaal=totaal;
    }

    private List<ArtikelContainer> cloneArtikelContainer(List<ArtikelContainer> listToClone){
        List<ArtikelContainer> clonedList = FXCollections.observableArrayList();
        for(ArtikelContainer artikelContainer: listToClone){
            clonedList.add(artikelContainer.clone());
        }
        return clonedList;
    }

    public void increaseAantalVerkopenSindsOnHold(){
        this.aantalVerkopenSindsOnHold++;
    }
    public void clearAantalSindsVerkoop(){
        this.aantalVerkopenSindsOnHold = 0;
    }

    public int getAantalVerkopenSindsOnHold() {
        return aantalVerkopenSindsOnHold;
    }

    public double getTotaal() {
        return totaal;
    }
}

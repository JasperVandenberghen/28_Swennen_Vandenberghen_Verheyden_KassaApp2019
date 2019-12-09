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
        List<ArtikelContainer> temp = this.artikelenInKassaKassier;
        this.artikelenInKassaKassier = null;
        return temp;
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

    public boolean isOnHold(){
        return this.artikelenInKassaKassier != null;
    }

    public void increaseAantalVerkopenSindsOnHold(){
        this.aantalVerkopenSindsOnHold++;
        if(aantalVerkopenSindsOnHold == 4) {
            artikelenInKassaKassier = null;
        }
    }

    public int getAantalVerkopenSindsOnHold() {
        return aantalVerkopenSindsOnHold;
    }

    public double getTotaal() {
        return totaal;
    }
}

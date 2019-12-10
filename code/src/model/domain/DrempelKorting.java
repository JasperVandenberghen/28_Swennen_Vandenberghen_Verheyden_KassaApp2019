package model.domain;

import java.util.List;

public class DrempelKorting implements KortingsStrategy {
    private Verkoop verkoop;
    private int kortingsAantal;
    private int drempel;
    private static final String omschrijving = "Drempelkorting";

    public DrempelKorting() {

    }

    public Verkoop getVerkoop() {
        return verkoop;
    }

    public void setVerkoop(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void setTotaalMetKortingKassier() {
        //verkoop opvragen
        Verkoop verkoop = getVerkoop();
        //artikelen in kassa klant ophalen
        List<ArtikelContainer> artikelenInKassaKassier = verkoop.getArtikelenInKassaKassier();
        double totaal = 0;
        //loopen door artikelen
        for(ArtikelContainer ac: artikelenInKassaKassier){
            totaal += ac.getPrijs();
        }

        if(totaal >= drempel){
            verkoop.setTotaal(verkoop.getTotaal() * convertKorting(kortingsAantal));
        }
    }


    @Override
    public double convertKorting(int kortingsAantal) {
        if(kortingsAantal != 0){
            return (100 - kortingsAantal) / 100 ;}
        else{
            return 0;
        }
    }

    @Override
    public void setKorting(String kortingsAantal) {
        this.kortingsAantal = Integer.parseInt(kortingsAantal);
    }

    @Override
    public String getOmschrijving() {
        return omschrijving;
    }

    @Override
    public void setCategorie(String categorie) {

    }

    @Override
    public void setDrempel(String drempel) {
        this.drempel = Integer.parseInt(drempel);
    }


    public int getKortingsAantal() {
        return kortingsAantal;
    }

    public int getDrempel() {
        return drempel;
    }
}

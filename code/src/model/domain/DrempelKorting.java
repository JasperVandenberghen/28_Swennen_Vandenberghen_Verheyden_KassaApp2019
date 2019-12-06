package model.domain;

import java.util.List;

public class DrempelKorting extends Korting {
    private Korting korting;
    private int kortingsAantal;



    public DrempelKorting(Korting korting) {
        this.korting = korting;
    }

    @Override
    public void setTotaalMetKortingKassier() {
        //verkoop opvragen
        Verkoop verkoop = korting.getVerkoop();
        //artikelen in kassa kklant ophalen
        List<ArtikelContainer> artikelenInKassaKassier = verkoop.getArtikelenInKassaKassier();
        double totaal = 0;
        //loopen door artikelen
        for(ArtikelContainer ac: artikelenInKassaKassier){
            totaal += ac.getPrijs();
        }

        if(totaal >= 100){
            verkoop.setTotaal(verkoop.getTotaal() * convertKorting(kortingsAantal));
        }
    }

    @Override
    public void setTotaalMetKortingKlant() {
        //verkoop opvragen
        Verkoop verkoop = korting.getVerkoop();
        //artikelen in kassa kklant ophalen
        List<ArtikelContainer> artikelenInKassaKlant = verkoop.getArtikelenInKassaKlant();
        double totaal = 0;
        //loopen door artikelen
        for(ArtikelContainer ac: artikelenInKassaKlant){
            totaal += ac.getPrijs();
        }

        if(totaal >= 100){
            verkoop.setTotaal(verkoop.getTotaal() * convertKorting(kortingsAantal));
        }
    }


    public void setKortingsAantal(String kortingsAantal){
        this.kortingsAantal = Integer.parseInt(kortingsAantal);
    }



}

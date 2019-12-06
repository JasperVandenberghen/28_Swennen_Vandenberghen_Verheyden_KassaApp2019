package model.domain;

import java.util.List;

public class DuursteKorting extends Korting  {
    private Korting korting;
    private int kortingsAantal;

    public DuursteKorting(Korting korting) {
        this.korting = korting;
    }

    @Override
    public void setTotaalMetKortingKassier() {
        //verkoop opvragen
        Verkoop verkoop = korting.getVerkoop();
        //artikelen in kassa kkassier ophalen
        List<ArtikelContainer> artikelenInKassaKassier = verkoop.getArtikelenInKassaKassier();
        double duurst = 0;
        String artikelIdDuurst = "";
        //loopen over artikelen
        for(ArtikelContainer ac: artikelenInKassaKassier){
            if(ac.getPrijs() > duurst){
                duurst = ac.getPrijs();
                artikelIdDuurst = ac.getArtikelId();
            }
            if(ac.getArtikelId().equals(artikelIdDuurst)){
                ac.setPrijs(ac.getPrijs() * convertKorting(kortingsAantal));
            }
        }
    }

    @Override
    public void setTotaalMetKortingKlant() {
        //verkoop opvragen
        Verkoop verkoop = korting.getVerkoop();
        //artikelen in kassa kklant ophalen
        List<ArtikelContainer> artikelenInKassaKlant = verkoop.getArtikelenInKassaKlant();
        double duurst = 0;
        String artikelIdDuurst = "";
        //loopen over artikelen
        for(ArtikelContainer ac: artikelenInKassaKlant){
            if(ac.getPrijs() > duurst){
                duurst = ac.getPrijs();
                artikelIdDuurst = ac.getArtikelId();
            }
            if(ac.getArtikelId().equals(artikelIdDuurst)){
                ac.setPrijs(ac.getPrijs() * convertKorting(kortingsAantal));
            }
        }
    }

    public void setKortingsAantal(String kortingsAantal){
        this.kortingsAantal = Integer.parseInt(kortingsAantal);
    }




}

package model.domain;

import java.util.List;

public class DuursteKorting extends Korting  {
    private static final String omschrijving = "Duurstekorting";


    public DuursteKorting() {
        
    }



    public void setVerkoop(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void setTotaalMetKortingKassier() {
        //verkoop opvragen
        Verkoop verkoop = getVerkoop();
        //artikelen in kassa kkassier ophalen
        List<ArtikelContainer> artikelenInKassaKassier = verkoop.getArtikelenInKassaKassier();
        double duurst = 0;
        String artikelIdDuurst = "";
        //loopen over artikelen
        int index = 0;
        for(ArtikelContainer ac: artikelenInKassaKassier){
            if(ac.getPrijs() > duurst){
                duurst = ac.getPrijs();
                artikelIdDuurst = ac.getArtikelId();
            }
            if(ac.getArtikelId().equals(artikelIdDuurst)){
                ac.setPrijs(ac.getPrijs() * convertKorting(kortingsAantal));
            }
            index++;
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

    }

    public void setKortingsAantal(String kortingsAantal){
        this.kortingsAantal = Integer.parseInt(kortingsAantal);
    }


    public Verkoop getVerkoop() {
        return verkoop;
    }
}

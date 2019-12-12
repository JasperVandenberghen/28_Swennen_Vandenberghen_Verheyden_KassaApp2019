package model.domain;

import java.util.List;

public class DrempelKorting extends Korting {
    private double drempel;
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


        if(verkoop.getTotaal() >= drempel){
            verkoop.setTotaal(verkoop.getTotaal() * convertKorting(kortingsAantal));

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
        if(drempel == null){throw new DomainException("Drempel is leeg.");}
        this.drempel = Double.parseDouble(drempel);
    }


    public int getKortingsAantal() {
        return kortingsAantal;
    }

    public double getDrempel() {
        return drempel;
    }
}

package model.domain.Korting;

import model.domain.ArtikelContainer;
import model.domain.DomainException;

public class DrempelKorting extends Korting {
    private double drempel;
    private static final String omschrijving = "Drempelkorting";

    public DrempelKorting() {

    }


    @Override
    public void setTotaalMetKortingKassier() {
        //artikelen in kassa klant ophalen

        if(KortingHandler.calculateTotaal(artikelContainers) >= drempel){
            for(int i= 0; i != artikelContainers.size(); i++){
                ArtikelContainer ac = artikelContainers.get(i);
                ac.setPrijs(ac.getPrijs() * convertKorting(kortingsAantal));
                artikelContainers.set(i, ac);
            }
        }
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


    public double getDrempel() {
        return drempel;
    }
}

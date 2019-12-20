package model.domain.KassaBon;

import model.domain.ArtikelContainer;
import model.domain.Verkoop;

public class KassaBonBasis extends KassaBon{
    private String description;
    private Verkoop verkoop;

    public KassaBonBasis(String description) {
        this.description = description;
    }

    public KassaBonBasis() {
    }

    public void setVerkoop(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    public String printArtikelen(){
        String artikelen = "";
        for(ArtikelContainer ac: verkoop.getArtikelenInKassaKlant()){
            artikelen += "\n" +  ac.getArtikelNaam() + "\t\t\t\t" + ac.getAantal() + "\t\t" + ac.getPrijs() + "\n";
        }
        return artikelen;
    }


    @Override
    public String getDescription() {
        String headerbasis = "Omschrijving\t\tAantal\t  Prijs\n" +
                "**********************************\n";
        String footerbasis = "\n**********************************\n" +
                "Betaald (inclusief korting) : €"+ verkoop.getEindTotaal() + "\n";

        return headerbasis + printArtikelen() + footerbasis;

    }

    @Override
    public void setDescription(String string) {
        this.description = string;
    }
}

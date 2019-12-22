package model.domain.KassaBon;

import model.domain.ArtikelContainer;

public class KassaBonBasis extends KassaBon{
    private String description;
    private KassaBonHandler kassaBonHandler;

    public KassaBonBasis(KassaBonHandler kassaBonHandler) {
        this.kassaBonHandler = kassaBonHandler;
    }

    public String printArtikelen(){
        String artikelen = "";
        for(ArtikelContainer ac: kassaBonHandler.getVerkoop().getArtikelenInKassaKlant()){
            artikelen += String.format("%-20s %-10s %-10s\n", ac.getArtikelNaam() , ac.getAantal() , ac.getPrijs());
        }
        return artikelen;
    }


    @Override
    public String getDescription() {
        String headerbasis = "Omschrijving\tAantal\t\t\tPrijs\n" +
                "************************************ \n";
        String footerbasis = "\n************************************\n" +
                "Betaald (inclusief korting) : €"+ kassaBonHandler.getVerkoop().getEindTotaal() + "\n";

        return headerbasis + printArtikelen() + footerbasis;

    }

}

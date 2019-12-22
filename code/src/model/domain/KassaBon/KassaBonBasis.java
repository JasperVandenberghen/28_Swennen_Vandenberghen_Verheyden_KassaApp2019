package model.domain.KassaBon;

import model.domain.ArtikelContainer;
import model.domain.KassaBonHandler;
import model.domain.Verkoop;

public class KassaBonBasis extends KassaBon{
    private String description;
    private KassaBonHandler kassaBonHandler;

    public KassaBonBasis(String description) {
        this.description = description;
    }

    public KassaBonBasis(Verkoop verkoop) {
        verkoop = kassaBonHandler.getVerkoop();
    }

    public void setVerkoop(Verkoop verkoop) {
        kassaBonHandler.setVerkoop(verkoop);
    }

    public String printArtikelen(){
        String artikelen = "";
        for(ArtikelContainer ac: kassaBonHandler.getVerkoop().getArtikelenInKassaKassier()){
            artikelen += "\n" +  ac.getArtikelNaam() + "\t\t\t\t" + ac.getAantal() + "\t\t" + ac.getPrijs() + "\n";
        }
        return artikelen;
    }


    @Override
    public String getDescription() {
        String headerbasis = "Omschrijving\t\tAantal\t  Prijs\n" +
                "********************************** \n";
        String footerbasis = "\n**********************************\n" +
                "Betaald (inclusief korting) : €"+ kassaBonHandler.getVerkoop().getEindTotaal() + "\n";

        return headerbasis + printArtikelen() + footerbasis;

    }

    @Override
    public void setDescription(String string) {
        this.description = string;
    }
}

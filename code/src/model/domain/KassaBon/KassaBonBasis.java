package model.domain.KassaBon;

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

    @Override
    public String getDescription() {
        return "Omschrijving\t\tAantal\t  Prijs\n" +
                "*****************************\n" +
                "test" +
                "*****************************\n" +
                "Betaald (inclusief korting) : 24.50 €         \n";
    }

    @Override
    public void setDescription(String string) {
        this.description = string;
    }
}

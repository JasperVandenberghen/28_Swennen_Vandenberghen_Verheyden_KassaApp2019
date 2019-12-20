package model.domain.KassaBon;

import model.domain.Verkoop;

public class KassaBonFooter extends KassaBon{

    private KassaBon kassaBon;
    private String description;


    public KassaBonFooter(KassaBon kassaBon) {
        this.kassaBon = kassaBon;
    }

    public void setKassaBon(KassaBon kassaBon) {
        this.kassaBon = kassaBon;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setVerkoop(Verkoop verkoop) {

    }


    @Override
    public String getDescription() {
        return kassaBon.getDescription() + description;
    }
}




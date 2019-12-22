package model.domain.KassaBon;

import model.domain.Verkoop;

public class KassaBonFooter extends KassaBon{

    private KassaBon kassaBon;

    public KassaBonFooter(KassaBon kassaBon) {
        this.kassaBon = kassaBon;
    }

    public void setKassaBon(KassaBon kassaBon) {
        this.kassaBon = kassaBon;
    }

    @Override
    public String getDescription() {
        return kassaBon.getDescription() + description;
    }
}




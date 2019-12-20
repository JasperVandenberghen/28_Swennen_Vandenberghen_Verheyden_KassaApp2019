package model.domain.KassaBon;

import model.domain.Verkoop;

public class KassaBonHeader extends KassaBon{
    private KassaBon kassaBon;
    private String description;


    public KassaBonHeader(KassaBon kassaBon) {
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
        return description + kassaBon.getDescription();
    }
}

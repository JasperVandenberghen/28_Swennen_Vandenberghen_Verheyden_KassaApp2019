package model.domain.KassaBon;

import model.domain.Verkoop;

public class KassaBonHeader extends KassaBon{
    private KassaBon kassaBon;

    public KassaBonHeader(KassaBon kassaBon) {
        this.kassaBon = kassaBon;
    }

    @Override
    public String getDescription() {
        return description + kassaBon.getDescription();
    }
}

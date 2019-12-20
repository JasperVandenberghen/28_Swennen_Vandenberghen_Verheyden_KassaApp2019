package model.domain.KassaBon;

import model.domain.Verkoop;

public abstract class KassaBon {
    private String description;


    public abstract String getDescription();

    public abstract void setDescription(String string);

    public abstract void setVerkoop(Verkoop verkoop);
}

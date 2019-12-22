package model.domain.KassaBon;

import model.domain.Verkoop;

public abstract class KassaBon {
    protected String description;

    public abstract String getDescription();

    public void setDescription(String string) {
        this.description = string;
    }
}

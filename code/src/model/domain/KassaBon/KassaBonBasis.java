package model.domain.KassaBon;

public class KassaBonBasis extends KassaBon{
    private String description;

    public KassaBonBasis(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}

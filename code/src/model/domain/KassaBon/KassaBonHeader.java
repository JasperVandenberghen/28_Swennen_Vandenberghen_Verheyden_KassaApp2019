package model.domain.KassaBon;

public class KassaBonHeader extends KassaBon{
    private KassaBon kassaBon;


    public KassaBonHeader(KassaBon kassaBon) {
        this.kassaBon = kassaBon;
    }

    @Override
    public String getDescription() {
        return kassaBon.getDescription() + "Zondag 31 maart open van 9.00 tot 12.00";
    }
}

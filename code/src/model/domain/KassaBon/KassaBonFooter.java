package model.domain.KassaBon;

public class KassaBonFooter extends KassaBon{

    private KassaBon kassaBon;


    public KassaBonFooter(KassaBon kassaBon) {
        this.kassaBon = kassaBon;
    }

    @Override
    public String getDescription() {
        return kassaBon.getDescription() + "Bedankt voor je aankopen!";
    }
}




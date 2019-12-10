package model.domain;

import java.util.List;

public class GroepsKorting implements KortingsStrategy {
    private Verkoop verkoop;
    private int kortingsAantal;
    private String categorie;
    private static final String omschrijving = "Groepskorting";

    public GroepsKorting() {


    }

    public Verkoop getVerkoop() {
        return verkoop;
    }

    public void setTotaalMetKortingKassier() {
        //verkoop opvragen
        Verkoop verkoop = getVerkoop();
        //artikelen in kassa kassier ophalen
        List<ArtikelContainer> artikelenInKassaKassier = verkoop.getArtikelenInKassaKassier();
        //loopen door artikelen
        for(ArtikelContainer ac: artikelenInKassaKassier){
            //als categorie gelijk pas de prijs aan
            if(ac.getArtikelCategorie().equals(getCategorie())){
                double prijs = ac.getPrijs();
                //opgehaalde prijs veranderen (bv bij 70% korting, prijs * 0.7)
                ac.setPrijs(prijs * convertKorting(getKortingsAantal()));

            }

        }

    }


    @Override
    public double convertKorting(int kortingsAantal) {
        if(kortingsAantal != 0){
            return (100 - kortingsAantal) / 100 ;}
        else{
            return 0;
        }
    }

    @Override
    public void setKorting(String kortingsAantal) {
        this.kortingsAantal = Integer.parseInt(kortingsAantal);
    }

    @Override
    public String getOmschrijving() {
        return omschrijving;
    }




    public void setDrempel() {

    }


    public void setKortingsAantal(String kortingsAantal){
        this.kortingsAantal = Integer.parseInt(kortingsAantal);
    }

    public String getCategorie() {
        return categorie;
    }

    public int getKortingsAantal() {
        return kortingsAantal;
    }

    //setter voor instellen op welke categorie er korting is
    @Override
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public void setDrempel(String drempel) {

    }
}

package model.domain;

import java.util.List;

public class GroepsKorting extends Korting {
    private Korting korting;
    private int kortingsAantal;
    private String categorie;

    public GroepsKorting(Korting korting) {
        this.korting = korting;


    }



    public Korting getKorting() {
        return korting;
    }



    public void setTotaalMetKortingKassier() {
        //verkoop opvragen
        Verkoop verkoop = korting.getVerkoop();
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

    public void setTotaalMetKortingKlant() {
        //verkoop opvragen
        Verkoop verkoop = korting.getVerkoop();
        //artikelen in kassa kklant ophalen
        List<ArtikelContainer> artikelenInKassaKlant = verkoop.getArtikelenInKassaKlant();
        //loopen door artikelen
        for(ArtikelContainer ac: artikelenInKassaKlant){
            //als categorie gelijk pas de prijs aan
            if(ac.getArtikelCategorie().equals(getCategorie())){
                double prijs = ac.getPrijs();
                //opgehaalde prijs veranderen (bv bij 70% korting, prijs * 0.7)
                ac.setPrijs(prijs * convertKorting(getKortingsAantal()));
            }
        }
    }


    public void setKortingsAantal(String kortingsAantal){
        this.kortingsAantal = Integer.parseInt(kortingsAantal);
    }

    public String getCategorie() {
        return categorie;
    }

    //setter voor instellen op welke categorie er korting is
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
